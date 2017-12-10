package org.throwable.json.component;

import javax.swing.*;
import java.awt.*;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/9 23:00
 */
public class AboutDialogManager {

	private final JFrame frame;

	public AboutDialogManager(JFrame frame) {
		this.frame = frame;
	}

	public void show() {
		JDialog dialog = new JDialog(frame);
		dialog.setTitle("关于");
		dialog.setModal(Boolean.FALSE);
		dialog.setSize(260, 400);
		dialog.setResizable(Boolean.FALSE);
		dialog.setLocationRelativeTo(frame);
		Container pane = dialog.getContentPane();
		BoxLayout layout = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(layout);
		pane.add(new JLabel("   "));
		pane.add(new JLabel("   application：json-doge"));
		pane.add(new JLabel("   author：throwable"));
		pane.add(new JLabel("   github：https://github.com/zjcscut"));
		pane.add(new JLabel("   "));
		pane.add(new JLabel("   control description："));
		pane.add(new JLabel("   Ctrl F：format json"));
		pane.add(new JLabel("   Ctrl M：minify json"));
		dialog.setVisible(Boolean.TRUE);
	}
}
