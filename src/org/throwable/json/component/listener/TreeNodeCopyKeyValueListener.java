package org.throwable.json.component.listener;

import org.throwable.json.common.TreeNodeType;
import org.throwable.json.model.TreeNodePair;
import org.throwable.json.utils.ClipboardUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/13 22:25
 */
public class TreeNodeCopyKeyValueListener implements ActionListener {

	private final JTree delegate;

	public TreeNodeCopyKeyValueListener(JTree delegate) {
		this.delegate = delegate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) delegate.getSelectionPath().getLastPathComponent();
		if (null != node) {
			TreeNodePair pair = (TreeNodePair) node.getUserObject();
			if (null != pair && TreeNodeType.LEAF.equals(pair.getType())) {
				ClipboardUtils.resetClipboardContent(String.format("%s:%s", pair.getKey(), String.valueOf(pair.getValue())));
			}
		}
	}
}
