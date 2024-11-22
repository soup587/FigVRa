package soup587.figvra.mixin;

import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.RendererAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.client_vr.ClientDataHolderVR;

@Mixin(value = RendererAPI.class, remap = false)
public class RendererAPIMixin {
    @Unique
    @LuaWhitelist
    @LuaMethodDoc("renderer.get_current_eye")
    public String getCurrentEye() {
        return ClientDataHolderVR.getInstance().currentPass.name();
    }
}
