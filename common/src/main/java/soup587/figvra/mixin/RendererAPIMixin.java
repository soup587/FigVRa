package soup587.figvra.mixin;

import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.RendererAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.vivecraft.api.client.VRRenderingAPI;
import org.vivecraft.api.client.data.RenderPass;

@Mixin(value = RendererAPI.class, remap = false)
public abstract class RendererAPIMixin {

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("renderer.vr.get_current_pass")
    public String vrGetCurrentPass() {
        return VRRenderingAPI.instance().getCurrentRenderPass().name();
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("renderer.vr.is_first_pass")
    public boolean vrIsFirstPass() {
        return VRRenderingAPI.instance().isFirstRenderPass();
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("renderer.vr.get_pass_matrix")
    public FiguraMat4 vrGetPassMatrix(String pass) {
        Matrix4f mat = VRRenderingAPI.instance().getRenderPassMatrix(RenderPass.valueOf(pass.toUpperCase()));
        return FiguraMat4.of(
                mat.m00(), mat.m01(), mat.m02(), mat.m03(),
                mat.m10(), mat.m11(), mat.m12(), mat.m13(),
                mat.m20(), mat.m21(), mat.m22(), mat.m23(),
                mat.m30(), mat.m31(), mat.m32(), mat.m33()
        );
    }
}
