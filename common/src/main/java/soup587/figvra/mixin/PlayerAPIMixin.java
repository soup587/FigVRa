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
import org.vivecraft.client.VRPlayersClient;
import org.vivecraft.client.VRPlayersClient.RotInfo;
import org.vivecraft.client_vr.VRState;
import soup587.figvra.MathUtils;

import java.util.HashMap;

import static org.vivecraft.client.VRPlayersClient.getMainPlayerRotInfo;

@Mixin(value = PlayerAPI.class, remap = false)
public abstract class PlayerAPIMixin  extends LivingEntityAPIMixin<Player>{

    @Unique private VRPlayersClient VRPlayer;

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.get_vr_rot")
    public HashMap<String,Object> getVRRots() {
        checkEntity();
        VRPlayersClient vrInst = VRPlayersClient.getInstance();
        if (vrInst.isVRPlayer(entity)) {
            HashMap<String,Object> RotTable = new HashMap<>();
            RotInfo VRRot;
            VRRot = vrInst.getRotationsForPlayer(entity.getUUID());

            RotTable.put("seated", LuaValue.valueOf(VRRot.seated));
            RotTable.put("leftHanded", VRRot.reverse);
            RotTable.put("donorHmd", VRRot.hmd);

            RotTable.put("headRot", MathUtils.quatToVec(VRRot.headQuat));
            RotTable.put("headDir", FiguraVec3.fromVec3(VRRot.headRot));
            RotTable.put("headPos", FiguraVec3.fromVec3(VRRot.Headpos));

            RotTable.put("leftArmRot", MathUtils.quatToVec(VRRot.leftArmQuat));
            RotTable.put("leftArmDir", FiguraVec3.fromVec3(VRRot.leftArmRot));
            RotTable.put("leftArmPos", FiguraVec3.fromVec3(VRRot.leftArmPos));

            RotTable.put("rightArmRot", MathUtils.quatToVec(VRRot.rightArmQuat));
            RotTable.put("rightArmDir", FiguraVec3.fromVec3(VRRot.rightArmRot));
            RotTable.put("rightArmPos", FiguraVec3.fromVec3(VRRot.rightArmPos));

            RotTable.put("worldScale", VRRot.worldScale);
            RotTable.put("heightScale", VRRot.heightScale);

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
        return VRPlayersClient.getInstance().isVRPlayer(entity);
    }

}
