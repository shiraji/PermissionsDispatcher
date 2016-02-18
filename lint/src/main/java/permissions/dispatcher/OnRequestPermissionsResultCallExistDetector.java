package permissions.dispatcher;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import lombok.ast.Annotation;
import lombok.ast.AstVisitor;
import lombok.ast.ClassDeclaration;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.MethodInvocation;
import lombok.ast.VariableReference;

public class OnRequestPermissionsResultCallExistDetector extends Detector implements Detector.JavaScanner {
    public static final Issue ISSUE = Issue.create("NeedOnRequestPermissionsResult",
            "Call onRequestPermissionsResult",
            "Call onRequestPermissionsResult",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            new Implementation(OnRequestPermissionsResultCallExistDetector.class, EnumSet.of(Scope.JAVA_FILE)));

    @Override
    public AstVisitor createJavaVisitor(JavaContext context) {
        return new OnRequestPermissionsResultChecker(context);
    }


    private static class OnRequestPermissionsResultChecker extends ForwardingAstVisitor {
        private final JavaContext context;
        private Set<String> matchingAnnotationTypeNames;
        private boolean hasRuntimePermissionAnnotation;
        private boolean hasOnRequestPermissionResultCall;
        private String generatedClassName;
        private ClassDeclaration classDeclaration;

        private OnRequestPermissionsResultChecker(JavaContext context) {
            this.context = context;

            matchingAnnotationTypeNames = new HashSet<>();
            matchingAnnotationTypeNames.add("RuntimePermissions");
            matchingAnnotationTypeNames.add("permissions.dispatcher.RuntimePermissions");
        }

        @Override
        public boolean visitClassDeclaration(ClassDeclaration node) {
            classDeclaration = node;
            generatedClassName = node.astName() + "PermissionsDispatcher";
            return super.visitClassDeclaration(node);
        }

        @Override
        public boolean visitAnnotation(Annotation node) {
            if (!context.isEnabled(ISSUE)) {
                return super.visitAnnotation(node);
            }

            String type = node.astAnnotationTypeReference().getTypeName();
            if (!matchingAnnotationTypeNames.contains(type)) {
                return super.visitAnnotation(node);
            }

            hasRuntimePermissionAnnotation = true;
            return super.visitAnnotation(node);
        }


        @Override
        public boolean visitMethodInvocation(MethodInvocation node) {
            if (!hasRuntimePermissionAnnotation) {
                return super.visitMethodInvocation(node);
            }

            if (!"onRequestPermissionsResult".equals(node.astName().astValue())) {
                return super.visitMethodInvocation(node);
            }

            if (node.astOperand() instanceof VariableReference) {
                VariableReference ref = (VariableReference) node.astOperand();
                if (generatedClassName.equals(ref.astIdentifier().astValue())) {
                    hasOnRequestPermissionResultCall = true;
                }
            }

            return super.visitMethodInvocation(node);
        }

        @Override
        public void afterVisitClassDeclaration(ClassDeclaration node) {
            if (hasRuntimePermissionAnnotation && !hasOnRequestPermissionResultCall) {
                context.report(ISSUE, context.getLocation(classDeclaration), "No onRequestPermissionsResult call");
            }
            super.afterVisitClassDeclaration(node);
        }
    }

}
