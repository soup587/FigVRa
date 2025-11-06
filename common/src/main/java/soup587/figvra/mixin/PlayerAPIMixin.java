package soup587.figvra.mixin;

import net.minecraft.world.entity.player.Player;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.PlayerAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.api.VRAPI;
import org.vivecraft.api.data.VRPose;
import soup587.figvra.FigVRa;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = PlayerAPI.class, remap = false)
public abstract class PlayerAPIMixin  extends LivingEntityAPIMixin<Player>{

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr.get_pose")
    public Map<String,Object> vrGetPose() {
        checkEntity();
        VRAPI vrInst = VRAPI.instance();
        if (!vrInst.isVRPlayer(entity)) { return new HashMap<>(); }
        VRPose pose = vrInst.getVRPose(entity);
        return FigVRa.parseVRPose(pose);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("player.vr.is_in_vr")
    public boolean vrIsInVR() {
        checkEntity();
        return VRAPI.instance().isVRPlayer(entity);
    }

}
