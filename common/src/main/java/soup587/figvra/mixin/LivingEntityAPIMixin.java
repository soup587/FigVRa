package soup587.figvra.mixin;


import net.minecraft.world.entity.LivingEntity;
import org.figuramc.figura.lua.api.entity.LivingEntityAPI;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = LivingEntityAPI.class, remap = false)
public abstract class LivingEntityAPIMixin<T extends LivingEntity> extends EntityAPIMixin<T> {
}
