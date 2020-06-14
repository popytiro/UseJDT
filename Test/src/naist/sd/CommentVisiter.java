package naist.sd;


import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.LineComment;


class CommentVisitor extends ASTVisitor {
	CompilationUnit compilationUnit;
	private String[] source;
	public CommentVisitor(CompilationUnit compilationUnit, String[] source) {
		super();
		this.compilationUnit = compilationUnit;
		this.source = source;
	}
	public boolean visit(CompilationUnit node) {
		List<Comment> comments = ((List<Comment>) node.getCommentList());
		for (Comment comment : comments) {
			comment.accept(new CommentVisitor(node, source));
		}
		return super.visit(node);
	}
	public boolean visit(LineComment node) {
		int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
		String lineComment = source[startLineNumber].trim();
		System.out.println(lineComment);
		return super.visit(node);
	}
	public boolean visit(BlockComment node) {
		int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
		int endLineNumber = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength()) - 1;
		StringBuffer blockComment = new StringBuffer();
		for (int lineCount = startLineNumber; lineCount <= endLineNumber; lineCount++) {
			String blockCommentLine = source[lineCount].trim();
			blockComment.append(blockCommentLine);
			if (lineCount != endLineNumber) {
				blockComment.append("\n");
			}
		}
		System.out.println(blockComment.toString());
		return super.visit(node);
	}
}
