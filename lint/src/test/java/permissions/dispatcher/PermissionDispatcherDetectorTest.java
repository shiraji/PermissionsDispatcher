package permissions.dispatcher;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.client.api.LintClient;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Project;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionDispatcherDetectorTest extends LintDetectorTest {

    private Set<Issue> mEnabled = new HashSet<>();

    @Override
    protected Detector getDetector() {
        return new PermissionDispatcherDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(PermissionDispatcherDetector.ISSUE);
    }

    @Override
    protected TestConfiguration getConfiguration(LintClient client, Project project) {
        return new TestConfiguration(client, project, null) {
            @Override
            public boolean isEnabled(Issue issue) {
                return super.isEnabled(issue) && mEnabled.contains(issue);
            }
        };
    }

    @Override
    protected boolean allowCompilationErrors() {
        return true;
    }

    @Override
    protected InputStream getTestResource(String relativePath, boolean expectExists) {
        String path = "lint/src/test/java/permissions/dispatcher/data/".replace("/", File.separator) + relativePath;
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            File file = new File(path);
            try {
                return new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
            }
        }
        return stream;
    }

    @Test
    public void testFoo() throws Exception {
        mEnabled = Collections.singleton(PermissionDispatcherDetector.ISSUE);

        assertEquals(
                "src/pkg/MainActivity.java:16: Error: Trying to access permission-protected method directly. [CallNeedsPermission]\n" +
                        "        showCamera();\n" +
                        "        ~~~~~~~~~~~~\n" +
                        "1 errors, 0 warnings\n",

                lintProject(
                        "bytecode/.classpath=>.classpath",
                        "bytecode/AndroidManifest.xml=>AndroidManifest.xml",
                        "pkg/MainActivity.java.txt=>src/pkg/MainActivity.java",
                        "pkg/MainActivityPermissionsDispatcher.java.txt=>src/pkg/MainActivityPermissionsDispatcher.java",
                        "bytecode/MainActivity.class.data=>bin/classes/pkg/MainActivity.class",
                        "bytecode/MainActivityPermissionsDispatcher.class.data=>bin/classes/pkg/MainActivityPermissionsDispatcher.class"
                ));
    }

}