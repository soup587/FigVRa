package soup587.figvra;

import org.figuramc.figura.math.vector.FiguraVec3;
import org.figuramc.figura.math.vector.FiguraVec4;
import org.joml.Quaternionfc;
import org.vivecraft.api.data.VRBodyPart;
import org.vivecraft.api.data.VRBodyPartData;
import org.vivecraft.api.data.VRPose;

import java.util.HashMap;
import java.util.Map;

public class FigVRa {
    public static final String PLUGIN_ID = "figvra";

    public FigVRa() {
    }

    public static void init() {
    }

    public static Map<String,Object> parseVRPose(VRPose pose) {
        Map<String,Object> data = new HashMap<>();

        data.put("fbtMode", pose.getFBTMode().name());
        data.put("leftHanded", pose.isLeftHanded());
        data.put("seated", pose.isSeated());

        for (VRBodyPart part : VRBodyPart.values()) {
            if (part.availableInMode(pose.getFBTMode())) {
                Map<String,Object> pdata = new HashMap<>();
                VRBodyPartData partData = pose.getBodyPartData(part);
                pdata.put("pos", FiguraVec3.fromVec3(partData.getPos()));
                pdata.put("rot", FiguraVec3.of(-partData.getPitch(), -partData.getYaw(), -partData.getRoll()).toDeg());
                pdata.put("dir", FiguraVec3.fromVec3(partData.getDir()));
                Quaternionfc pquat = partData.getRotation();
                pdata.put("rot4", FiguraVec4.of(
                        pquat.x(),
                        pquat.y(),
                        pquat.z(),
                        pquat.w())
                );
                data.put(part.name().toLowerCase(),pdata);
            }
        }

        return data;
    }
}
