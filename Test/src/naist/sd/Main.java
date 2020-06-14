package naist.sd;


import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Main {
	public static void main(String[] args) {
		String source = "public class Main {\n\tpublic static void main(String[] args) {\n\t\t// WRITE CODE HERE\n\t\tSystem.out.println(1);\n\t}\n}"; // âêÕÇ∑ÇÈJavaÉ\Å[ÉXÇÃï∂éöóÒ
		ASTParser parser = ASTParser.newParser(AST.JLS11);
//		Map<?, ?> options = JavaCore.getOptions();
//		JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
//		parser.setCompilerOptions(options);
		parser.setSource(source.toCharArray());
		CompilationUnit unit = (CompilationUnit) parser.createAST(new NullProgressMonitor());
		unit.accept(new CommentVisitor(unit, source.split("\n")));
	}
}