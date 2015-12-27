package permissions.dispatcher.sample;

import android.Manifest;
import android.support.v4.app.Fragment;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainFragment extends Fragment {
    @NeedsPermission(Manifest.permission.INTERNET)
    void internet() {
    }

    @OnShowRationale(Manifest.permission.INTERNET)
    void internetOnShow(PermissionRequest request) {
    }

    @NeedsPermission(Manifest.permission.WRITE_SETTINGS)
    void writeSettings() {
    }

    @OnShowRationale(Manifest.permission.WRITE_SETTINGS)
    void writeSettingOnShowRationale(PermissionRequest request) {
    }

    @NeedsPermission(Manifest.permission.SYSTEM_ALERT_WINDOW)
    void showSystemAlertWindowPermission() {
    }

    @OnShowRationale(Manifest.permission.SYSTEM_ALERT_WINDOW)
    void showSystemAlertWindowRationale(PermissionRequest request) {
    }

}
