package permissions.dispatcher.test;


import android.app.Activity;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PermissionUtils.class)
public class MainActivityPermissionsDispatcherTest {

    MainActivity activity;

    @Before
    public void setUp() {
        activity = Mockito.mock(MainActivity.class);
        PowerMockito.mockStatic(PermissionUtils.class);
    }

    @Test
    public void testAlreadyGrantedCallTheMethod() {
        when(PermissionUtils.hasSelfPermissions(any(Context.class), anyString())).thenReturn(true);

        MainActivityPermissionsDispatcher.showCameraWithCheck(activity);

        Mockito.verify(activity, Mockito.times(1)).showCamera();
    }

    @Test
    public void testNotGrantedDoesNotCallTheMethod() {
        when(PermissionUtils.hasSelfPermissions(any(Context.class), anyString())).thenReturn(false);
        when(PermissionUtils.shouldShowRequestPermissionRationale(any(Activity.class), anyString())).thenReturn(true);

        MainActivityPermissionsDispatcher.showCameraWithCheck(activity);

        Mockito.verify(activity, Mockito.times(0)).showCamera();
    }

    @Test
    public void testShowRequestPermissionRationaleCallRationaleMethod() {
        when(PermissionUtils.hasSelfPermissions(any(Context.class), anyString())).thenReturn(false);
        when(PermissionUtils.shouldShowRequestPermissionRationale(any(Activity.class), anyString())).thenReturn(true);

        MainActivityPermissionsDispatcher.showCameraWithCheck(activity);

        Mockito.verify(activity, Mockito.times(1)).showRationaleForCamera(any(PermissionRequest.class));
    }

    @Test
    public void testOnRequestShowTheMethod() {
        when(PermissionUtils.verifyPermissions(any(int[].class))).thenReturn(true);

        MainActivityPermissionsDispatcher.onRequestPermissionsResult(activity, 0, any(int[].class));

        Mockito.verify(activity, Mockito.times(1)).showCamera();
    }

}
