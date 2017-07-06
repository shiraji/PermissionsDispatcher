package permissions.dispatcher.test;

import android.content.Context;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;

import permissions.dispatcher.PermissionUtils;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changeText_sameActivity() throws InterruptedException, UiObjectNotFoundException {
        onView(withId(R.id.button)).perform(click());
//        onView(withId(R.id.camera_display_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        Thread.sleep(3000);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject allowPermissions = device.findObject(new UiSelector()
                .clickable(true)
                .checkable(false)
                .index(1));
        allowPermissions.click();
        PowerMockito.mockStatic(PermissionUtils.class);
        when(PermissionUtils.hasSelfPermissions(any(Context.class), anyString())).thenReturn(true);
        onView(withId(R.id.camera_display_text)).check(matches(isDisplayed()));
    }
}