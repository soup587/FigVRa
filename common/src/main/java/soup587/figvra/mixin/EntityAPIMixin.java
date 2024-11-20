package soup587.figvra.mixin;

import net.minecraft.world.entity.Entity;
import org.figuramc.figura.lua.api.entity.EntityAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityAPI.class, remap = false)
public abstract class EntityAPIMixin<T extends Entity> {
    @Shadow protected abstract boolean checkEntity();
    @Shadow protected T entity;
}
