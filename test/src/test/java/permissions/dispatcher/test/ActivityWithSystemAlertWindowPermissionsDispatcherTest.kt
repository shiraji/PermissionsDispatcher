package permissions.dispatcher.test

import android.os.Process
import android.support.v4.app.ActivityCompat
import android.support.v4.app.AppOpsManagerCompat
import android.support.v4.content.PermissionChecker
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Matchers.any
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import permissions.dispatcher.PermissionRequest


@Suppress("IllegalIdentifier")
@RunWith(PowerMockRunner::class)
@PrepareForTest(ActivityCompat::class, PermissionChecker::class, AppOpsManagerCompat::class, Process::class)
class ActivityWithSystemAlertWindowPermissionsDispatcherTest {

    private lateinit var activity: ActivityWithSystemAlertWindow

    companion object {
        private var requestCode = 0

        @BeforeClass
        @JvmStatic
        fun setUpForClass() {
            requestCode = getRequestCameraConstant(ActivityWithSystemAlertWindowPermissionsDispatcher::class.java, "REQUEST_SYSTEMALERTWINDOW")
        }
    }

    @Before
    fun setUp() {
        activity = Mockito.mock(ActivityWithSystemAlertWindow::class.java)
        PowerMockito.mockStatic(ActivityCompat::class.java)
        PowerMockito.mockStatic(PermissionChecker::class.java)
        PowerMockito.mockStatic(Process::class.java)
        PowerMockito.mockStatic(AppOpsManagerCompat::class.java)
    }

    @Test
    fun `already granted call the method`() {
        mockCheckSelfPermission(true)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindow()
    }

    @Test
    fun `not granted does not call the method`() {
        mockCheckSelfPermission(false)
        mockActivityCompatShouldShowRequestPermissionRationale(true)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindow()
    }

    @Test
    fun `not granted permission and show rationale is true then call the rationale method`() {
        mockCheckSelfPermission(false)
        mockActivityCompatShouldShowRequestPermissionRationale(true)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindowOnShowRationale(any(PermissionRequest::class.java))
    }

    @Test
    fun `not granted permission and show rationale is false then does not call the rationale method`() {
        mockCheckSelfPermission(false)
        mockActivityCompatShouldShowRequestPermissionRationale(false)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindowOnShowRationale(Matchers.any(PermissionRequest::class.java))
    }

    @Test
    fun `the method is called if verifyPermission is true`() {
//        ActivityWithAllAnnotationsPermissionsDispatcher.onRequestPermissionsResult(activity, requestCode, intArrayOf(PackageManager.PERMISSION_GRANTED))
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindow()
    }

    @Test
    fun `the method is not called if verifyPermission is false`() {
//        ActivityWithAllAnnotationsPermissionsDispatcher.onRequestPermissionsResult(activity, requestCode, intArrayOf(PackageManager.PERMISSION_DENIED))
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindow()
    }

    @Test
    fun `show never ask method is call if verifyPermission is false and shouldShowRequestPermissionRationale is false`() {
        mockActivityCompatShouldShowRequestPermissionRationale(false)

//        ActivityWithAllAnnotationsPermissionsDispatcher.onRequestPermissionsResult(activity, requestCode, intArrayOf(PackageManager.PERMISSION_DENIED))
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindowOnNeverAskAgain()
    }

    @Test
    fun `show deny method is call if verifyPermission is false and shouldShowRequestPermissionRationale is true`() {
        mockActivityCompatShouldShowRequestPermissionRationale(true)

//        ActivityWithAllAnnotationsPermissionsDispatcher.onRequestPermissionsResult(activity, requestCode, intArrayOf(PackageManager.PERMISSION_DENIED))
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindowOnPermissionDenied()
    }

    @Test
    fun `no the method call if request code is not related to the library`() {
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode + 1000)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindow()
    }

    @Test
    fun `no denied method call if request code is not related to the library`() {
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode + 1000)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindowOnPermissionDenied()
    }

    @Test
    fun `no never ask method call if request code is not related to the library`() {
        ActivityWithSystemAlertWindowPermissionsDispatcher.onActivityResult(activity, requestCode + 1000)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindowOnNeverAskAgain()
    }

    @Test
    fun `xiaomi device permissionToOp returns null grant permission`() {
        testForXiaomi()
        mockPermissionToOp(null)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindow()
    }

    @Test
    fun `xiaomi device grant permission`() {
        testForXiaomi()
        mockPermissionToOp("")
        mockNoteOp(AppOpsManagerCompat.MODE_ALLOWED)
        mockCheckSelfPermission(true)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindow()
    }

    @Test
    fun `xiaomi noteOp returns not allowed value should not call the method`() {
        testForXiaomi()
        mockPermissionToOp("")
        mockNoteOp(AppOpsManagerCompat.MODE_IGNORED)
        mockCheckSelfPermission(true)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindow()
    }

    @Test
    fun `xiaomi noteOp returns allowed but checkSelfPermission not allowed value should not call the method`() {
        testForXiaomi()
        mockPermissionToOp("")
        mockNoteOp(AppOpsManagerCompat.MODE_ALLOWED)
        mockCheckSelfPermission(false)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindow()
    }

    @Test
    fun `blow M follows checkSelfPermissions result false`() {
        overwriteCustomSdkInt(22)
        mockCheckSelfPermission(false)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(0)).systemAlertWindow()
    }

    @Test
    fun `blow M follows checkSelfPermissions result true`() {
        overwriteCustomSdkInt(22)
        mockCheckSelfPermission(true)

        ActivityWithSystemAlertWindowPermissionsDispatcher.systemAlertWindowWithCheck(activity)

        Mockito.verify(activity, Mockito.times(1)).systemAlertWindow()
    }
}