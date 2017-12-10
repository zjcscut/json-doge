package org.throwable.json.component;


import org.throwable.json.common.Constants;
import org.throwable.json.support.ImageIconLoader;
import org.throwable.json.support.ResourceBundleManager;

import javax.swing.*;

import java.awt.*;

import static org.throwable.json.common.IconImageConstants.LOGO_NAME;
import static org.throwable.json.common.IconImageConstants.getResourcePathByName;
import static org.throwable.json.common.MessageBundleKey.APPLICATION_TITLE;

/**
 * @author throwable
 * @version v1.0
 * @description 加载页
 * @since 2017/12/9 22:57
 */
public abstract class LoadingFrameManager extends BaseComponent {

	public static void show() throws Exception {
		JFrame frame = new JFrame();
		frame.setSize(Constants.LOADING_PAGE_WIDTH, Constants.LOADING_PAGE_HEIGHT);
		frame.setLocationRelativeTo(frame.getOwner());
		frame.setIconImage(ImageIconLoader.loadImageByName(getResourcePathByName(LOGO_NAME)));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle(ResourceBundleManager.getTextByKey(APPLICATION_TITLE));
		//设置背景图片
		ImageIcon img = new ImageIcon(ImageIconLoader.loadImageByName(getResourcePathByName(LOGO_NAME)));
		JLabel label = new JLabel(img);
		frame.getLayeredPane().add(label);
		label.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		Container container =frame.getContentPane();
		container.setLayout(null);
		((JPanel)container).setOpaque(Boolean.FALSE);
		frame.setVisible(Boolean.TRUE);
		Thread.sleep(Constants.LOADING_SLEEP_MILLION_SECONDS);
		frame.setVisible(Boolean.FALSE);
	}
}
