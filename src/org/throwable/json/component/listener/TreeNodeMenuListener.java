package org.throwable.json.component.listener;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/13 21:43
 */
public class TreeNodeMenuListener implements MouseListener {

	private final JTree delegate;
	private final JPopupMenu menu;

	public TreeNodeMenuListener(JTree delegate, JPopupMenu menu) {
		this.delegate = delegate;
		this.menu = menu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		TreePath path = delegate.getPathForLocation(e.getX(), e.getY());
		if (null == path) {
			return;
		}
		//设置当前选择路径
		delegate.setSelectionPath(path);
		if (e.isPopupTrigger()) {
			//显示菜单
			menu.show(delegate, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}


}
