package soup587.figvra.mixin;

import net.minecraft.world.entity.player.Player;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.PlayerAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.client.ClientVRPlayers;
import org.vivecraft.client.ClientVRPlayers.RotInfo;
import org.vivecraft.client_vr.VRState;
import org.vivecraft.common.network.FBTMode;
import soup587.figvra.MathUtils;

import java.util.HashMap;

import static org.vivecraft.client.ClientVRPlayers.getMainPlayerRotInfo;

@Mixin(value = PlayerAPI.class, remap = false)
public abstract class PlayerAPIMixin  extends LivingEntityAPIMixin<Player>{

    @Unique private ClientVRPlayers VRPlayer;

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.get_vr_rots")
    public HashMap<String,Object> getVRRots() {
        checkEntity();
        ClientVRPlayers vrInst = ClientVRPlayers.getInstance();
        if (vrInst.isVRPlayer(entity)) {
            HashMap<String,Object> RotTable = new HashMap<>();
            RotInfo VRRot;
            VRRot = vrInst.getRotationsForPlayer(entity.getUUID());

            RotTable.put("seated", LuaValue.valueOf(VRRot.seated));
            RotTable.put("leftHanded", VRRot.leftHanded);
            RotTable.put("donorHmd", VRRot.hmd);

            RotTable.put("headRot", MathUtils.quatfcToVec(VRRot.headQuat));
            RotTable.put("headDir", MathUtils.vec3fcToVec(VRRot.headRot));
            RotTable.put("headPos", MathUtils.vec3fcToVec(VRRot.headPos));

            RotTable.put("offHandRot", MathUtils.quatfcToVec(VRRot.offHandQuat));
            RotTable.put("offHandDir", MathUtils.vec3fcToVec(VRRot.offHandRot));
            RotTable.put("offHandPos", MathUtils.vec3fcToVec(VRRot.offHandPos));

            RotTable.put("mainHandRot", MathUtils.quatfcToVec(VRRot.mainHandQuat));
            RotTable.put("mainHandDir", MathUtils.vec3fcToVec(VRRot.mainHandRot));
            RotTable.put("mainHandPos", MathUtils.vec3fcToVec(VRRot.mainHandPos));

            RotTable.put("worldScale", VRRot.worldScale);
            RotTable.put("heightScale", VRRot.heightScale);

            RotTable.put("fbtMode", VRRot.fbtMode.toString());

            if (VRRot.fbtMode != FBTMode.ARMS_ONLY) {
                RotTable.put("waistPos", MathUtils.vec3fcToVec(VRRot.waistPos));
                RotTable.put("waistRot", MathUtils.quatfcToVec(VRRot.waistQuat));

                RotTable.put("rightFootPos", MathUtils.vec3fcToVec(VRRot.rightFootPos));
                RotTable.put("rightFootRot", MathUtils.quatfcToVec(VRRot.rightFootQuat));

                RotTable.put("leftFootPos", MathUtils.vec3fcToVec(VRRot.leftFootPos));
                RotTable.put("leftFootRot", MathUtils.quatfcToVec(VRRot.leftFootQuat));
            }

            if (VRRot.fbtMode == FBTMode.WITH_JOINTS) {
                RotTable.put("rightKneePos", MathUtils.vec3fcToVec(VRRot.rightKneePos));
                RotTable.put("rightKneeRot", MathUtils.quatfcToVec(VRRot.rightKneeQuat));

                RotTable.put("leftKneePos", MathUtils.vec3fcToVec(VRRot.leftKneePos));
                RotTable.put("leftKneeRot", MathUtils.quatfcToVec(VRRot.leftKneeQuat));

                RotTable.put("rightElbowPos", MathUtils.vec3fcToVec(VRRot.rightElbowPos));
                RotTable.put("rightElbowRot", MathUtils.quatfcToVec(VRRot.rightElbowQuat));

                RotTable.put("leftElbowPos", MathUtils.vec3fcToVec(VRRot.leftElbowPos));
                RotTable.put("leftElbowRot", MathUtils.quatfcToVec(VRRot.leftElbowQuat));
            }

            return RotTable;
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.is_in_vr")
    public boolean isInVR() {
        checkEntity();
        return ClientVRPlayers.getInstance().isVRPlayer(entity);
    }

}
