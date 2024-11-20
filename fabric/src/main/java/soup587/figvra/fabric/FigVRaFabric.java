package soup587.figvra.fabric;

import net.fabricmc.api.ModInitializer;
import soup587.figvra.FigVRa;

/**
 * A mod class is not technically needed for Fabric to load the Plugin, but it's still nice to have.
 */
public class FigVRaFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FigVRa.init();
    }
}
