package soup587.figvra.mixin;

//import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.EntityAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.vivecraft.client.ClientVRPlayers;

@Mixin(value = EntityAPI.class, remap = false)
public abstract class EntityAPIMixin<T extends Entity> {
    @Shadow protected abstract boolean checkEntity();
    @Shadow protected T entity;

    @LuaWhitelist
    @LuaMethodDoc("entity.get_look_dir")
    @Overwrite
    public FiguraVec3 getLookDir() {
        checkEntity();
        if (entity instanceof Player) {
            ClientVRPlayers vrInst = ClientVRPlayers.getInstance();
            if (vrInst.isVRPlayer(entity)) {
                return FiguraVec3.fromVec3f((Vector3f) vrInst.getRotationsForPlayer(entity.getUUID()).headRot);
                }
            else {
                return FiguraVec3.fromVec3(entity.getLookAngle());
            }
        } else {
            return FiguraVec3.fromVec3(entity.getLookAngle());
        }
    }
}
