package permissions.dispatcher;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.EnumSet;

import lombok.ast.AstVisitor;
import lombok.ast.ForwardingAstVisitor;

public class NoWithCheckCallInOnResumeDetector extends Detector implements Detector.JavaScanner {

    public static final Issue ISSUE = Issue.create("NoWithCheckCallInOnResume",
            "TBD", // TODO: think these
            "TBD",
            Category.CORRECTNESS,
            1,
            Severity.WARNING,
            new Implementation(NoWithCheckCallInOnResumeDetector.class, EnumSet.of(Scope.ALL_JAVA_FILES)));

    @Override
    public AstVisitor createJavaVisitor(JavaContext context) {
        if (context.getPhase() == 1) {
            return new AnnotationChecker(context);
        } else if (context.getPhase() == 2) {
            return new MethodCallChecker(context);
        }
        return null;
    }


    private static class AnnotationChecker extends ForwardingAstVisitor {
        public AnnotationChecker(JavaContext context) {
        }
    }

    private class MethodCallChecker extends ForwardingAstVisitor {
        public MethodCallChecker(JavaContext context) {
        }
    }
}
