package permissions.dispatcher;

import android.app.Activity;

public class PermissionUtils {
    public static boolean hasSelfPermissions(Activity activity, String[] permissions) {
        return false;
    }

    public static int getTargetSdkVersion(Activity activity) {
        return 0;
    }

    public static boolean verifyPermissions(int... grantResults) {
        return false;
    }

    private static boolean permissionExists(String permission) {
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        return false;
    }
}
