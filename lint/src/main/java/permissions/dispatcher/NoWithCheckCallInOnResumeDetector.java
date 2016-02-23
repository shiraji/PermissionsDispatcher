package permissions.dispatcher;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;

import lombok.ast.AstVisitor;
import lombok.ast.ForwardingAstVisitor;

public class NoWithCheckCallInOnResumeDetector extends Detector implements Detector.JavaScanner {

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
