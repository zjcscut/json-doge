package org.throwable.json.component;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.netbeans.swing.tabcontrol.TabData;
import org.netbeans.swing.tabcontrol.TabDataModel;
import org.netbeans.swing.tabcontrol.TabbedContainer;
import org.throwable.json.utils.AssertUtils;

import javax.swing.*;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/9 23:12
 */
public abstract class ComponentSelector extends BaseComponent {

	public static int getSelectedTabIndex(TabbedContainer tabbedContainer) {
		return tabbedContainer.getSelectionModel().getSelectedIndex();
	}

	public static RSyntaxTextArea getRSyntaxTextArea(TabbedContainer tabbedContainer) {
		int index = getSelectedTabIndex(tabbedContainer);
		TabDataModel model = tabbedContainer.getModel();
		AssertUtils.assertTrue(index >= 0, "Fetch textArea error!");
		AssertUtils.assertNotNull(model, "Fetch tabDataModel error!");
		TabData tabData = model.getTab(index);
		JSplitPane jSplitPane = (JSplitPane) tabData.getComponent();
		JScrollPane jScrollPane = (JScrollPane) jSplitPane.getLeftComponent();
		JViewport jViewport = (JViewport) jScrollPane.getComponent(0);
		return (RSyntaxTextArea) jViewport.getComponent(0);
	}

	public static JTree getTree(TabbedContainer tabbedContainer) {
		int index = getSelectedTabIndex(tabbedContainer);
		TabDataModel model = tabbedContainer.getModel();
		AssertUtils.assertTrue(index >= 0, "Fetch textArea error!");
		AssertUtils.assertNotNull(model, "Fetch tabDataModel error!");
		TabData tabData = model.getTab(index);
		JSplitPane jSplitPane = (JSplitPane) tabData.getComponent();
		JScrollPane jScrollPane = (JScrollPane) jSplitPane.getRightComponent();
		JViewport jViewport = (JViewport) jScrollPane.getComponent(0);
		return (JTree) jViewport.getComponent(0);
	}

	public static JTable getTable(TabbedContainer tabbedContainer) {
		int index = getSelectedTabIndex(tabbedContainer);
		TabDataModel model = tabbedContainer.getModel();
		AssertUtils.assertTrue(index >= 0, "Fetch textArea error!");
		AssertUtils.assertNotNull(model, "Fetch tabDataModel error!");
		TabData tabData = model.getTab(index);
		JSplitPane jSplitPane = (JSplitPane) tabData.getComponent();
		JSplitPane rightSplitPane = (JSplitPane) jSplitPane.getRightComponent();
		JScrollPane jScrollPane = (JScrollPane) rightSplitPane.getRightComponent();
		JViewport jViewport = (JViewport) jScrollPane.getComponent(0);
		return (JTable) jViewport.getComponent(0);
	}
}
