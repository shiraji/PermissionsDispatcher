package permissions.dispatcher.sample;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class NativeFragment extends Fragment {

	@NeedsPermission(Manifest.permission.CAMERA)
	void checkCamera() {

	}

	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		NativeFragmentPermissionsDispatcher.onActivityResult(this, requestCode, resultCode, data);
	}
}
