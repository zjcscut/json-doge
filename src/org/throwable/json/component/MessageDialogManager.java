package org.throwable.json.component;

import org.throwable.json.support.ImageIconLoader;

import javax.swing.*;
import java.awt.*;

import static org.throwable.json.common.IconImageConstants.SURPRISE_IMAGE;
import static org.throwable.json.common.IconImageConstants.getResourcePathByName;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 11:28
 */
public class MessageDialogManager {

	private final JFrame jFrame;

	public MessageDialogManager(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	public void showMessageDialog(String title, String message) {
		JDialog dialog = new JDialog(jFrame);
		dialog.setTitle(title);
		dialog.setMinimumSize(new Dimension(500, 300));
		BorderLayout layout = new BorderLayout();
		dialog.getContentPane().setLayout(layout);
		ImageIcon img = new ImageIcon(ImageIconLoader.loadImageByName(getResourcePathByName(SURPRISE_IMAGE)));
		JLabel label = new JLabel(img);
		dialog.getContentPane().add(label, BorderLayout.NORTH);
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(Boolean.TRUE);
		textArea.setText(message);
		textArea.setWrapStyleWord(Boolean.TRUE);
		dialog.setLocationRelativeTo(jFrame);
		dialog.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		dialog.setVisible(Boolean.TRUE);
	}
}
