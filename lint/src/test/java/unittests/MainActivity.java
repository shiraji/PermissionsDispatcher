package unittests;

import android.Manifest;
import android.app.Activity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends Activity {
    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
    }

    void foo() {
        showCamera();
    }
}
