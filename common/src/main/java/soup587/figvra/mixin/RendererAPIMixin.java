package soup587.figvra.mixin;

import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.RendererAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.api.client.VRRenderingAPI;

@Mixin(value = RendererAPI.class, remap = false)
public class RendererAPIMixin {

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("renderer.vr.get_current_pass")
    public String vrGetCurrentPass() {
        return VRRenderingAPI.instance().getCurrentRenderPass().name();
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("renderer.vr.is_first_render_pass")
    public boolean vrIsFirstRenderPass() {
        return VRRenderingAPI.instance().isFirstRenderPass();
    }
}
