package soup587.figvra.mixin;

import net.minecraft.world.entity.player.Player;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.PlayerAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.api.VRAPI;
import org.vivecraft.api.data.VRBodyPart;
import org.vivecraft.api.data.VRBodyPartData;
import org.vivecraft.api.data.VRPose;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = PlayerAPI.class, remap = false)
public abstract class PlayerAPIMixin  extends LivingEntityAPIMixin<Player>{

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr.get_vr_data")
    public Map<String,Object> vrGetData() {
        checkEntity();
        VRAPI vrInst = VRAPI.instance();
        if (!vrInst.isVRPlayer(entity)) { return new HashMap<>(); }
        VRPose pose = vrInst.getVRPose(entity);
        Map<String,Object> data = new HashMap<>();

        data.put("fbtMode", pose.getFBTMode().name());
        data.put("leftHanded", pose.isLeftHanded());
        data.put("seated", pose.isSeated());

        for (VRBodyPart part : VRBodyPart.values()) {
            if (part.availableInMode(pose.getFBTMode())) {
                Map<String,Object> pdata = new HashMap<>();
                VRBodyPartData partData = pose.getBodyPartData(part);
                pdata.put("pos", FiguraVec3.fromVec3(partData.getPos()));
                // remember the rest, idiot
                data.put(part.name(),pdata);
            }
        }

        return data;
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr.is_in_vr")
    public boolean vrIsInVR() {
        checkEntity();
        return VRAPI.instance().isVRPlayer(entity);
    }

}
