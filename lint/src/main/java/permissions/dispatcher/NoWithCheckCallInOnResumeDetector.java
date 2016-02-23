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
            System.out.println("------------------------");
        }

        @Override
        public boolean visitNode(Node node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitNode(node);
        }

        @Override
        public void endVisit(Node node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.endVisit(node);
        }

        @Override
        public boolean visitTypeReference(TypeReference node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitTypeReference(node);
        }

        @Override
        public boolean visitTypeReferencePart(TypeReferencePart node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitTypeReferencePart(node);
        }

        @Override
        public boolean visitVariableReference(VariableReference node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitVariableReference(node);
        }

        @Override
        public boolean visitIdentifier(Identifier node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitIdentifier(node);
        }

        @Override
        public boolean visitIntegralLiteral(IntegralLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitIntegralLiteral(node);
        }

        @Override
        public boolean visitFloatingPointLiteral(FloatingPointLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitFloatingPointLiteral(node);
        }

        @Override
        public boolean visitBooleanLiteral(BooleanLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitBooleanLiteral(node);
        }

        @Override
        public boolean visitCharLiteral(CharLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitCharLiteral(node);
        }

        @Override
        public boolean visitStringLiteral(StringLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitStringLiteral(node);
        }

        @Override
        public boolean visitNullLiteral(NullLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitNullLiteral(node);
        }

        @Override
        public boolean visitBinaryExpression(BinaryExpression node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitBinaryExpression(node);
        }

        @Override
        public boolean visitUnaryExpression(UnaryExpression node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitUnaryExpression(node);
        }

        @Override
        public boolean visitInlineIfExpression(InlineIfExpression node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitInlineIfExpression(node);
        }

        @Override
        public boolean visitCast(Cast node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitCast(node);
        }

        @Override
        public boolean visitInstanceOf(InstanceOf node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitInstanceOf(node);
        }

        @Override
        public boolean visitConstructorInvocation(ConstructorInvocation node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitConstructorInvocation(node);
        }

        @Override
        public boolean visitMethodInvocation(MethodInvocation node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitMethodInvocation(node);
        }

        @Override
        public boolean visitSelect(Select node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitSelect(node);
        }

        @Override
        public boolean visitArrayAccess(ArrayAccess node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitArrayAccess(node);
        }

        @Override
        public boolean visitArrayCreation(ArrayCreation node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitArrayCreation(node);
        }

        @Override
        public boolean visitAnnotationValueArray(AnnotationValueArray node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAnnotationValueArray(node);
        }

        @Override
        public boolean visitArrayInitializer(ArrayInitializer node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitArrayInitializer(node);
        }

        @Override
        public boolean visitArrayDimension(ArrayDimension node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitArrayDimension(node);
        }

        @Override
        public boolean visitClassLiteral(ClassLiteral node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitClassLiteral(node);
        }

        @Override
        public boolean visitSuper(Super node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitSuper(node);
        }

        @Override
        public boolean visitThis(This node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitThis(node);
        }

        @Override
        public boolean visitLabelledStatement(LabelledStatement node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitLabelledStatement(node);
        }

        @Override
        public boolean visitExpressionStatement(ExpressionStatement node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitExpressionStatement(node);
        }

        @Override
        public boolean visitIf(If node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitIf(node);
        }

        @Override
        public boolean visitFor(For node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitFor(node);
        }

        @Override
        public boolean visitForEach(ForEach node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitForEach(node);
        }

        @Override
        public boolean visitTry(Try node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitTry(node);
        }

        @Override
        public boolean visitCatch(Catch node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitCatch(node);
        }

        @Override
        public boolean visitWhile(While node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitWhile(node);
        }

        @Override
        public boolean visitDoWhile(DoWhile node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitDoWhile(node);
        }

        @Override
        public boolean visitSynchronized(Synchronized node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitSynchronized(node);
        }

        @Override
        public boolean visitBlock(Block node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitBlock(node);
        }

        @Override
        public boolean visitAssert(Assert node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAssert(node);
        }

        @Override
        public boolean visitEmptyStatement(EmptyStatement node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitEmptyStatement(node);
        }

        @Override
        public boolean visitSwitch(Switch node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitSwitch(node);
        }

        @Override
        public boolean visitCase(Case node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitCase(node);
        }

        @Override
        public boolean visitDefault(Default node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitDefault(node);
        }

        @Override
        public boolean visitBreak(Break node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitBreak(node);
        }

        @Override
        public boolean visitContinue(Continue node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitContinue(node);
        }

        @Override
        public boolean visitReturn(Return node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitReturn(node);
        }

        @Override
        public boolean visitThrow(Throw node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitThrow(node);
        }

        @Override
        public boolean visitVariableDeclaration(VariableDeclaration node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitVariableDeclaration(node);
        }

        @Override
        public boolean visitVariableDefinition(VariableDefinition node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitVariableDefinition(node);
        }

        @Override
        public boolean visitVariableDefinitionEntry(VariableDefinitionEntry node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitVariableDefinitionEntry(node);
        }

        @Override
        public boolean visitTypeVariable(TypeVariable node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitTypeVariable(node);
        }

        @Override
        public boolean visitKeywordModifier(KeywordModifier node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitKeywordModifier(node);
        }

        @Override
        public boolean visitModifiers(Modifiers node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitModifiers(node);
        }

        @Override
        public boolean visitAnnotation(Annotation node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAnnotation(node);
        }

        @Override
        public boolean visitAnnotationElement(AnnotationElement node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAnnotationElement(node);
        }

        @Override
        public boolean visitNormalTypeBody(NormalTypeBody node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitNormalTypeBody(node);
        }

        @Override
        public boolean visitEnumTypeBody(EnumTypeBody node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitEnumTypeBody(node);
        }

        @Override
        public boolean visitEmptyDeclaration(EmptyDeclaration node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitEmptyDeclaration(node);
        }

        @Override
        public boolean visitMethodDeclaration(MethodDeclaration node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitMethodDeclaration(node);
        }

        @Override
        public boolean visitConstructorDeclaration(ConstructorDeclaration node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitConstructorDeclaration(node);
        }

        @Override
        public boolean visitSuperConstructorInvocation(SuperConstructorInvocation node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitSuperConstructorInvocation(node);
        }

        @Override
        public boolean visitAlternateConstructorInvocation(AlternateConstructorInvocation node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAlternateConstructorInvocation(node);
        }

        @Override
        public boolean visitInstanceInitializer(InstanceInitializer node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitInstanceInitializer(node);
        }

        @Override
        public boolean visitStaticInitializer(StaticInitializer node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitStaticInitializer(node);
        }

        @Override
        public boolean visitClassDeclaration(ClassDeclaration node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitClassDeclaration(node);
        }

        @Override
        public boolean visitInterfaceDeclaration(InterfaceDeclaration node) {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitInterfaceDeclaration(node);
        }

        @Override
        public boolean visitEnumDeclaration(EnumDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitEnumDeclaration(node);
        }

        @Override
        public boolean visitEnumConstant(EnumConstant node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitEnumConstant(node);
        }

        @Override
        public boolean visitAnnotationDeclaration(AnnotationDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAnnotationDeclaration(node);
        }

        @Override
        public boolean visitAnnotationMethodDeclaration(AnnotationMethodDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitAnnotationMethodDeclaration(node);
        }

        @Override
        public boolean visitCompilationUnit(CompilationUnit node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitCompilationUnit(node);
        }

        @Override
        public boolean visitPackageDeclaration(PackageDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitPackageDeclaration(node);
        }

        @Override
        public boolean visitImportDeclaration(ImportDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitImportDeclaration(node);
        }

        @Override
        public boolean visitParseArtefact(Node node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitParseArtefact(node);
        }

        @Override
        public boolean visitComment(Comment node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            return super.visitComment(node);
        }

        @Override
        public void afterVisitTypeReference(TypeReference node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitTypeReference(node);
        }

        @Override
        public void afterVisitTypeReferencePart(TypeReferencePart node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitTypeReferencePart(node);
        }

        @Override
        public void afterVisitVariableReference(VariableReference node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitVariableReference(node);
        }

        @Override
        public void afterVisitIdentifier(Identifier node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitIdentifier(node);
        }

        @Override
        public void afterVisitIntegralLiteral(IntegralLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitIntegralLiteral(node);
        }

        @Override
        public void afterVisitFloatingPointLiteral(FloatingPointLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitFloatingPointLiteral(node);
        }

        @Override
        public void afterVisitBooleanLiteral(BooleanLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitBooleanLiteral(node);
        }

        @Override
        public void afterVisitCharLiteral(CharLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitCharLiteral(node);
        }

        @Override
        public void afterVisitStringLiteral(StringLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitStringLiteral(node);
        }

        @Override
        public void afterVisitNullLiteral(NullLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitNullLiteral(node);
        }

        @Override
        public void afterVisitBinaryExpression(BinaryExpression node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitBinaryExpression(node);
        }

        @Override
        public void afterVisitUnaryExpression(UnaryExpression node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitUnaryExpression(node);
        }

        @Override
        public void afterVisitInlineIfExpression(InlineIfExpression node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitInlineIfExpression(node);
        }

        @Override
        public void afterVisitCast(Cast node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitCast(node);
        }

        @Override
        public void afterVisitInstanceOf(InstanceOf node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitInstanceOf(node);
        }

        @Override
        public void afterVisitConstructorInvocation(ConstructorInvocation node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitConstructorInvocation(node);
        }

        @Override
        public void afterVisitMethodInvocation(MethodInvocation node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitMethodInvocation(node);
        }

        @Override
        public void afterVisitSelect(Select node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitSelect(node);
        }

        @Override
        public void afterVisitArrayAccess(ArrayAccess node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitArrayAccess(node);
        }

        @Override
        public void afterVisitArrayCreation(ArrayCreation node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitArrayCreation(node);
        }

        @Override
        public void afterVisitAnnotationValueArray(AnnotationValueArray node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAnnotationValueArray(node);
        }

        @Override
        public void afterVisitArrayInitializer(ArrayInitializer node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitArrayInitializer(node);
        }

        @Override
        public void afterVisitArrayDimension(ArrayDimension node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitArrayDimension(node);
        }

        @Override
        public void afterVisitClassLiteral(ClassLiteral node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitClassLiteral(node);
        }

        @Override
        public void afterVisitSuper(Super node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitSuper(node);
        }

        @Override
        public void afterVisitThis(This node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitThis(node);
        }

        @Override
        public void afterVisitLabelledStatement(LabelledStatement node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitLabelledStatement(node);
        }

        @Override
        public void afterVisitExpressionStatement(ExpressionStatement node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitExpressionStatement(node);
        }

        @Override
        public void afterVisitIf(If node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitIf(node);
        }

        @Override
        public void afterVisitFor(For node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitFor(node);
        }

        @Override
        public void afterVisitForEach(ForEach node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitForEach(node);
        }

        @Override
        public void afterVisitTry(Try node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitTry(node);
        }

        @Override
        public void afterVisitCatch(Catch node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitCatch(node);
        }

        @Override
        public void afterVisitWhile(While node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitWhile(node);
        }

        @Override
        public void afterVisitDoWhile(DoWhile node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitDoWhile(node);
        }

        @Override
        public void afterVisitSynchronized(Synchronized node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitSynchronized(node);
        }

        @Override
        public void afterVisitBlock(Block node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitBlock(node);
        }

        @Override
        public void afterVisitAssert(Assert node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAssert(node);
        }

        @Override
        public void afterVisitEmptyStatement(EmptyStatement node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitEmptyStatement(node);
        }

        @Override
        public void afterVisitSwitch(Switch node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitSwitch(node);
        }

        @Override
        public void afterVisitCase(Case node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitCase(node);
        }

        @Override
        public void afterVisitDefault(Default node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitDefault(node);
        }

        @Override
        public void afterVisitBreak(Break node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitBreak(node);
        }

        @Override
        public void afterVisitContinue(Continue node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitContinue(node);
        }

        @Override
        public void afterVisitReturn(Return node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitReturn(node);
        }

        @Override
        public void afterVisitThrow(Throw node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitThrow(node);
        }

        @Override
        public void afterVisitVariableDeclaration(VariableDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitVariableDeclaration(node);
        }

        @Override
        public void afterVisitVariableDefinition(VariableDefinition node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitVariableDefinition(node);
        }

        @Override
        public void afterVisitVariableDefinitionEntry(VariableDefinitionEntry node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitVariableDefinitionEntry(node);
        }

        @Override
        public void afterVisitTypeVariable(TypeVariable node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitTypeVariable(node);
        }

        @Override
        public void afterVisitKeywordModifier(KeywordModifier node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitKeywordModifier(node);
        }

        @Override
        public void afterVisitModifiers(Modifiers node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitModifiers(node);
        }

        @Override
        public void afterVisitAnnotation(Annotation node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAnnotation(node);
        }

        @Override
        public void afterVisitAnnotationElement(AnnotationElement node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAnnotationElement(node);
        }

        @Override
        public void afterVisitNormalTypeBody(NormalTypeBody node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitNormalTypeBody(node);
        }

        @Override
        public void afterVisitEnumTypeBody(EnumTypeBody node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitEnumTypeBody(node);
        }

        @Override
        public void afterVisitEmptyDeclaration(EmptyDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitEmptyDeclaration(node);
        }

        @Override
        public void afterVisitMethodDeclaration(MethodDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitMethodDeclaration(node);
        }

        @Override
        public void afterVisitConstructorDeclaration(ConstructorDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitConstructorDeclaration(node);
        }

        @Override
        public void afterVisitSuperConstructorInvocation(SuperConstructorInvocation node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitSuperConstructorInvocation(node);
        }

        @Override
        public void afterVisitAlternateConstructorInvocation(AlternateConstructorInvocation node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAlternateConstructorInvocation(node);
        }

        @Override
        public void afterVisitInstanceInitializer(InstanceInitializer node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitInstanceInitializer(node);
        }

        @Override
        public void afterVisitStaticInitializer(StaticInitializer node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitStaticInitializer(node);
        }

        @Override
        public void afterVisitClassDeclaration(ClassDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitClassDeclaration(node);
        }

        @Override
        public void afterVisitInterfaceDeclaration(InterfaceDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitInterfaceDeclaration(node);
        }

        @Override
        public void afterVisitEnumDeclaration(EnumDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitEnumDeclaration(node);
        }

        @Override
        public void afterVisitEnumConstant(EnumConstant node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitEnumConstant(node);
        }

        @Override
        public void afterVisitAnnotationDeclaration(AnnotationDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAnnotationDeclaration(node);
        }

        @Override
        public void afterVisitAnnotationMethodDeclaration(AnnotationMethodDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitAnnotationMethodDeclaration(node);
        }

        @Override
        public void afterVisitCompilationUnit(CompilationUnit node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitCompilationUnit(node);
        }

        @Override
        public void afterVisitPackageDeclaration(PackageDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitPackageDeclaration(node);
        }

        @Override
        public void afterVisitImportDeclaration(ImportDeclaration node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitImportDeclaration(node);
        }

        @Override
        public void afterVisitParseArtefact(Node node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitParseArtefact(node);
        }

        @Override
        public void afterVisitComment(Comment node) {System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
            super.afterVisitComment(node);
        }
    }

    private class MethodCallChecker extends ForwardingAstVisitor {
        public MethodCallChecker(JavaContext context) {
        }
    }
}
