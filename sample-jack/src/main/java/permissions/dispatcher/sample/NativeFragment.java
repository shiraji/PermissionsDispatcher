package permissions.dispatcher.sample;

import android.Manifest;
import android.app.Fragment;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class NativeFragment extends Fragment {

    @NeedsPermission(Manifest.permission.CAMERA)
    void checkCamera() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        NativeFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
