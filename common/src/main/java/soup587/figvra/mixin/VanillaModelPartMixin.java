package soup587.figvra.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.vanilla_model.VanillaModelPart;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Function;

@Mixin(value = VanillaModelPart.class, remap = false)
public class VanillaModelPartMixin {


    @Shadow private boolean saved;

    @LuaWhitelist
    @LuaMethodDoc("vanilla_model_part.get_saved")
    public Boolean getSaved() {
        return saved;
    }

}
