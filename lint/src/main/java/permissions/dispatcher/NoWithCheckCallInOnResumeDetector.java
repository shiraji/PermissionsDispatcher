package permissions.dispatcher;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.EnumSet;

import lombok.ast.AlternateConstructorInvocation;
import lombok.ast.Annotation;
import lombok.ast.AnnotationDeclaration;
import lombok.ast.AnnotationElement;
import lombok.ast.AnnotationMethodDeclaration;
import lombok.ast.AnnotationValueArray;
import lombok.ast.ArrayAccess;
import lombok.ast.ArrayCreation;
import lombok.ast.ArrayDimension;
import lombok.ast.ArrayInitializer;
import lombok.ast.Assert;
import lombok.ast.AstVisitor;
import lombok.ast.BinaryExpression;
import lombok.ast.Block;
import lombok.ast.BooleanLiteral;
import lombok.ast.Break;
import lombok.ast.Case;
import lombok.ast.Cast;
import lombok.ast.Catch;
import lombok.ast.CharLiteral;
import lombok.ast.ClassDeclaration;
import lombok.ast.ClassLiteral;
import lombok.ast.Comment;
import lombok.ast.CompilationUnit;
import lombok.ast.ConstructorDeclaration;
import lombok.ast.ConstructorInvocation;
import lombok.ast.Continue;
import lombok.ast.Default;
import lombok.ast.DoWhile;
import lombok.ast.EmptyDeclaration;
import lombok.ast.EmptyStatement;
import lombok.ast.EnumConstant;
import lombok.ast.EnumDeclaration;
import lombok.ast.EnumTypeBody;
import lombok.ast.ExpressionStatement;
import lombok.ast.FloatingPointLiteral;
import lombok.ast.For;
import lombok.ast.ForEach;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Identifier;
import lombok.ast.If;
import lombok.ast.ImportDeclaration;
import lombok.ast.InlineIfExpression;
import lombok.ast.InstanceInitializer;
import lombok.ast.InstanceOf;
import lombok.ast.IntegralLiteral;
import lombok.ast.InterfaceDeclaration;
import lombok.ast.KeywordModifier;
import lombok.ast.LabelledStatement;
import lombok.ast.MethodDeclaration;
import lombok.ast.MethodInvocation;
import lombok.ast.Modifiers;
import lombok.ast.Node;
import lombok.ast.NormalTypeBody;
import lombok.ast.NullLiteral;
import lombok.ast.PackageDeclaration;
import lombok.ast.Return;
import lombok.ast.Select;
import lombok.ast.StaticInitializer;
import lombok.ast.StringLiteral;
import lombok.ast.Super;
import lombok.ast.SuperConstructorInvocation;
import lombok.ast.Switch;
import lombok.ast.Synchronized;
import lombok.ast.This;
import lombok.ast.Throw;
import lombok.ast.Try;
import lombok.ast.TypeReference;
import lombok.ast.TypeReferencePart;
import lombok.ast.TypeVariable;
import lombok.ast.UnaryExpression;
import lombok.ast.VariableDeclaration;
import lombok.ast.VariableDefinition;
import lombok.ast.VariableDefinitionEntry;
import lombok.ast.VariableReference;
import lombok.ast.While;

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

        private boolean hasRuntimePermission;

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



            return super.visitMethodDeclaration(node);
        }
    }
}
