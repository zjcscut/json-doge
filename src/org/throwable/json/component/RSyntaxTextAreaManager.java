package org.throwable.json.component;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;

import java.awt.*;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 11:53
 */
public abstract class RSyntaxTextAreaManager extends BaseComponent {

	public static RSyntaxTextArea crateRSyntaxTextArea(){
		RSyntaxTextArea textArea = new RSyntaxTextArea();
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		textArea.setCodeFoldingEnabled(Boolean.TRUE);
		textArea.setAntiAliasingEnabled(Boolean.TRUE);
		textArea.setAutoscrolls(Boolean.TRUE);
		textArea.setMarkOccurrences(Boolean.TRUE);
		//前面空出两个tab
		textArea.setTabSize(2);
		textArea.setAnimateBracketMatching(Boolean.TRUE);
		textArea.setPaintTabLines(Boolean.TRUE);
		SyntaxScheme scheme = textArea.getSyntaxScheme();
		scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.BLUE;
		scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = new Color(164, 0, 0);
		scheme.getStyle(Token.LITERAL_NUMBER_FLOAT).foreground = new Color(164, 0, 0);
		scheme.getStyle(Token.LITERAL_BOOLEAN).foreground = Color.RED;
		scheme.getStyle(Token.OPERATOR).foreground = Color.BLACK;
		textArea.revalidate();
		return textArea;
	}
}
