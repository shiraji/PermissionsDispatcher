package unittests;

import com.ad.android.tools.lint.Lint;
import com.android.tools.lint.Warning;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import permissions.dispatcher.PermissionDispatcherDetector;

import static com.ad.android.tools.lint.assertj.WarningAssert.assertThat;


@RunWith(JUnit4.class)
public class LintTestCase extends TestCase {

    @Rule
    public Lint lint = new Lint(new PermissionDispatcherDetector(), PermissionDispatcherDetector.ISSUE);

    @Test
    public void testFoo() throws Exception {
        List<Warning> lintResult = lint.files("src/unittests/MainActivity.java");
        assertThat(lintResult).hasWarnings(1).in("MainActivity.java");
    }
}
