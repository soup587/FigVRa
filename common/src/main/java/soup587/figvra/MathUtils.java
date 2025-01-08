package soup587.figvra;

import org.figuramc.figura.math.vector.FiguraVec3;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class MathUtils {


    // fixed getEulerAngles, stolen from vivecraft which stoled it from the updated joml
    public static Vector3f getEulerAnglesZYX(Quaternionfc rot, Vector3f eulerAngles) {
        eulerAngles.x = org.joml.Math.atan2(rot.y() * rot.z() + rot.w() * rot.x(),
                0.5f - rot.x() * rot.x() - rot.y() * rot.y());
        eulerAngles.y = org.joml.Math.safeAsin(-2.0f * (rot.x() * rot.z() - rot.w() * rot.y()));
        eulerAngles.z = org.joml.Math.atan2(rot.x() * rot.y() + rot.w() * rot.z(),
                0.5f - rot.y() * rot.y() - rot.z() * rot.z());
        return eulerAngles;
    }

    public static FiguraVec3 quatfcToVec(Quaternionfc quaternion) {
        Vector3f angles = getEulerAnglesZYX(quaternion, new Vector3f());
        return FiguraVec3.of(
                -angles.x,
                angles.y-Math.PI,
                angles.z
        ).toDeg();
    }

    public static FiguraVec3 vec3fcToVec(Vector3fc vec) {
        return FiguraVec3.of(vec.x(), vec.y(), vec.z());
    }
}
