package soup587.figvra.mixin;

import org.figuramc.figura.lua.LuaNotNil;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.HostAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.api.client.VRClientAPI;
import org.vivecraft.api.client.data.CloseKeyboardContext;
import org.vivecraft.api.client.data.OpenKeyboardContext;
import org.vivecraft.api.data.VRBodyPart;
import org.vivecraft.api.data.VRPose;
import soup587.figvra.FigVRa;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = HostAPI.class, remap = false)
public abstract class HostAPIMixin {

    @Shadow
    public abstract boolean isHost();

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.is_active")
    public boolean vrIsActive() {
        return VRClientAPI.instance().isVRActive();
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.get_room_pose")
    public Map<String,Object> vrGetRoomPose() {
        VRClientAPI vrInst = VRClientAPI.instance();
        if (!vrInst.isVRActive()) { return new HashMap<>(); }
        VRPose pose = vrInst.getLatestRoomPose();
        return FigVRa.parseVRPose(pose);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.get_world_pose")
    public Map<String,Object> vrGetWorldPose() {
        VRClientAPI vrInst = VRClientAPI.instance();
        if (!vrInst.isVRActive()) { return new HashMap<>(); }
        VRPose pose = vrInst.getWorldRenderPose();
        return FigVRa.parseVRPose(pose);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.get_world_scale")
    public float vrGetWorldScale() {
        return VRClientAPI.instance().getWorldScale();
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.trigger_haptic_pulse")
    public HostAPI vrTriggerHapticPulse(@LuaNotNil String bodyPart, float duration, Float frequency, float amplitude, float delay) {
        if (!isHost()) return ((HostAPI) (Object) this);
        bodyPart = bodyPart.toUpperCase();
        VRClientAPI vrInst = VRClientAPI.instance();
        if (frequency == null) {
            vrInst.triggerHapticPulse(VRBodyPart.valueOf(bodyPart), duration);
            return ((HostAPI) (Object) this);
        }
        vrInst.triggerHapticPulse(VRBodyPart.valueOf(bodyPart), duration, frequency, amplitude, delay);
        return ((HostAPI) (Object) this);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.open_keyboard")
    public HostAPI vrOpenKeyboard() {
        if (!isHost()) return ((HostAPI) (Object) this);
        VRClientAPI.instance().openKeyboard(OpenKeyboardContext.FORCE);
        return ((HostAPI) (Object) this);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("host.vr.close_keyboard")
    public HostAPI vrCloseKeyboard() {
        if (!isHost()) return ((HostAPI) (Object) this);
        VRClientAPI.instance().closeKeyboard(CloseKeyboardContext.FORCE);
        return ((HostAPI) (Object) this);
    }
}
