package soup587.figvra;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import org.figuramc.figura.entries.FiguraVanillaPart;
import org.figuramc.figura.entries.annotations.FiguraVanillaPartPlugin;
import org.vivecraft.client.render.VRPlayerModel_WithArms;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@FiguraVanillaPartPlugin
public class FigVRaVanillaParts implements FiguraVanillaPart {
    @Override
    public String getID() {
        return "VIVECRAFT";
    }

    /**
     *  Available so that other mods can add in Model Parts to figura, you'd obviously do something different here.
     */
    @Override
    public Collection<Pair<String, Function<EntityModel<?>, ModelPart>>> getParts() {
        return List.of(
                new Pair<>("LEFT_HAND", model -> model instanceof VRPlayerModel_WithArms<?> m ? m.leftHand : null),
                new Pair<>("RIGHT_HAND", model -> model instanceof VRPlayerModel_WithArms<?> m ? m.rightHand : null),
                new Pair<>("LEFT_SHOULDER", model -> model instanceof VRPlayerModel_WithArms<?> m ? m.leftShoulder : null),
                new Pair<>("RIGHT_SHOULDER", model -> model instanceof VRPlayerModel_WithArms<?> m ? m.rightShoulder : null),
                new Pair<>("LEFT_SHOULDER_LAYER", model -> model instanceof VRPlayerModel_WithArms<?> m ? m.leftShoulder_sleeve : null),
                new Pair<>("RIGHT_SHOULDER_LAYER", model -> model instanceof VRPlayerModel_WithArms<?> m ? m.rightShoulder_sleeve : null)
        );
    }
}
