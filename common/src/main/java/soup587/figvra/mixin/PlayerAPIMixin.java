package soup587.figvra.mixin;

import net.minecraft.world.entity.player.Player;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.PlayerAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.client.VRPlayersClient;
import org.vivecraft.client.VRPlayersClient.RotInfo;

@Mixin(value = PlayerAPI.class, remap = false)
public abstract class PlayerAPIMixin  extends LivingEntityAPIMixin<Player>{

    @Unique private VRPlayersClient VRPlayer;

    @Unique
    private RotInfo getRotInfo(Player player) {
        VRPlayersClient vrInst = VRPlayersClient.getInstance();
        return vrInst.getRotationsForPlayer(player.getUUID());
    }


    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.is_in_vr")
    public boolean isInVR() {
        checkEntity();
        return VRPlayersClient.getInstance().isVRPlayer(entity);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_is_seated")
    public boolean isVRSeated() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return getRotInfo(entity).seated;
        } else {
            return false;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_is_left_handed")
    public boolean isVRLeftHanded() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return getRotInfo(entity).reverse;
        } else {
            return false;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_donor_headset")
    public int getDonorHmd() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return getRotInfo(entity).hmd;
        } else {
            return 0;
        }
    }

//    @Unique
//    @LuaWhitelist
//    @LuaMethodDoc("player.vr_get_head_rot")
//    public FiguraVec3 getVRHeadRot() {
//        checkEntity();
//        RotInfo rinfo = getRotInfo(entity);
//        if (rinfo != null) {
//            var quaternion = getRotInfo(entity).headQuat;
//            return FiguraVec3.fromVec3(quaternion.toEuler().forward().toVector3d());
//        } else {
//            return null;
//        }
//    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_get_head_dir")
    public FiguraVec3 getVRHeadDir() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return FiguraVec3.fromVec3(getRotInfo(entity).headRot);
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_get_head_pos")
    public FiguraVec3 getVRHeadPos() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return FiguraVec3.fromVec3(getRotInfo(entity).Headpos);
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_get_left_arm_dir")
    public FiguraVec3 getVRLeftHandDir() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return FiguraVec3.fromVec3(getRotInfo(entity).leftArmRot);
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_get_left_arm_pos")
    public FiguraVec3 getVRLeftHandPos() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return FiguraVec3.fromVec3(getRotInfo(entity).leftArmPos);
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_get_right_arm_dir")
    public FiguraVec3 getVRRightHandDir() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return FiguraVec3.fromVec3(getRotInfo(entity).rightArmRot);
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_get_right_arm_pos")
    public FiguraVec3 getVRRightHandPos() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return FiguraVec3.fromVec3(getRotInfo(entity).rightArmPos);
        } else {
            return null;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_world_scale")
    public float getVRWorldScale() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return getRotInfo(entity).worldScale;
        } else {
            return 1;
        }
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr_height_scale")
    public float getVRHeightScale() {
        checkEntity();
        RotInfo rinfo = getRotInfo(entity);
        if (rinfo != null) {
            return getRotInfo(entity).heightScale;
        } else {
            return 1;
        }
    }

}
