package permissions.dispatcher.test;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ActivityWithSystemAlertWindow extends AppCompatActivity {

    @NeedsPermission(Manifest.permission.SYSTEM_ALERT_WINDOW)
    void systemAlertWindow() {
    }

    @OnShowRationale(Manifest.permission.SYSTEM_ALERT_WINDOW)
    void systemAlertWindowOnShowRationale(final PermissionRequest request) {
    }

    @OnPermissionDenied(Manifest.permission.SYSTEM_ALERT_WINDOW)
    void systemAlertWindowOnPermissionDenied() {
    }

    @OnNeverAskAgain(Manifest.permission.SYSTEM_ALERT_WINDOW)
    void systemAlertWindowOnNeverAskAgain() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(this, requestCode);
    }
}
