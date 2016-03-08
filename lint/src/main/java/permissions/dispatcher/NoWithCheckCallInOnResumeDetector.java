package permissions.dispatcher;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.EnumSet;

import lombok.ast.Annotation;
import lombok.ast.AstVisitor;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.MethodDeclaration;

public class NoWithCheckCallInOnResumeDetector extends Detector implements Detector.JavaScanner {

    public static final Issue ISSUE = Issue.create("NoWithCheckCallInOnResume",
            "TBD", // TODO: think these
            "TBD",
            Category.CORRECTNESS,
            1,
            Severity.WARNING,
            new Implementation(NoWithCheckCallInOnResumeDetector.class, EnumSet.of(Scope.JAVA_FILE)));

    @Override
    public AstVisitor createJavaVisitor(JavaContext context) {
        if (context.getPhase() == 1) {
            return new AnnotationChecker(context);
        }
        return null;
    }


    private class AnnotationChecker extends ForwardingAstVisitor {
        JavaContext javaContext;

        private boolean hasRuntimePermissionAnnotation;

        public AnnotationChecker(JavaContext context) {
            javaContext = context;
        }

        @Override
        public boolean visitAnnotation(Annotation node) {
            if (!javaContext.isEnabled(ISSUE)) {
                // stop executing lint for this ISSUE
                return true;
            }

            return super.visitAnnotation(node);
        }

        @Override
        public boolean visitMethodDeclaration(MethodDeclaration node) {
            if (hasRuntimePermissionAnnotation && "public void onResume(int, java.lang.String[], int[])".equals(context.resolve(node).getSignature().trim())) {
                return super.visitMethodDeclaration(node);
            }
        }
    }
}
