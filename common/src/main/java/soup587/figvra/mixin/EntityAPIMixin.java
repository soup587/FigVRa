package soup587.figvra.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.EntityAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.vivecraft.api.VRAPI;

@Mixin(value = EntityAPI.class, remap = false)
public abstract class EntityAPIMixin<T extends Entity> {
    @Shadow protected abstract boolean checkEntity();
    @Shadow protected T entity;

    @ModifyReturnValue(method = "getLookDir", at = @At("RETURN"))
    public FiguraVec3 vrLookDir(FiguraVec3 original) {
        if (entity instanceof Player) {
            VRAPI vrInst = VRAPI.instance();
            if (vrInst.isVRPlayer((Player) entity)) {
                return FiguraVec3.fromVec3(vrInst.getVRPose((Player) entity).getHead().getDir());
                }
            else {
                return original;
            }
        } else {
            return original;
        }
    }
}
