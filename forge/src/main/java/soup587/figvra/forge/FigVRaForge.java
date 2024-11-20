package soup587.figvra.forge;

import net.minecraftforge.fml.common.Mod;
import soup587.figvra.FigVRa;

/**
 * A mod class is needed for Forge to load the Plugin
 */
@Mod(FigVRa.PLUGIN_ID)
public class FigVRaForge {
    public FigVRaForge() {
        FigVRa.init();
    }
}
