package soup587.figvra;

import org.figuramc.figura.math.vector.FiguraVec3;
import org.joml.Vector3f;
import org.vivecraft.common.utils.math.Angle;
import org.vivecraft.common.utils.math.Quaternion;

public class MathUtils {

    public static FiguraVec3 quatToVec(Quaternion quaternion) {
        Angle angle = quaternion.toEuler();
        return FiguraVec3.of(
                -angle.getPitch(),
                angle.getYaw()-180,
                angle.getRoll()
        );
    }

}
