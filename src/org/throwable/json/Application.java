package org.throwable.json;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.netbeans.swing.tabcontrol.DefaultTabDataModel;
import org.netbeans.swing.tabcontrol.TabData;
import org.netbeans.swing.tabcontrol.TabDataModel;
import org.netbeans.swing.tabcontrol.TabbedContainer;
import org.throwable.json.common.Constants;
import org.throwable.json.common.MessageBundleKey;
import org.throwable.json.common.TreeNodeType;
import org.throwable.json.component.*;
import org.throwable.json.component.listener.TreeNodeCopyKeyListener;
import org.throwable.json.component.listener.TreeNodeCopyKeyValueListener;
import org.throwable.json.component.listener.TreeNodeCopyValueListener;
import org.throwable.json.component.listener.TreeNodeMenuListener;
import org.throwable.json.model.TreeNodePair;
import org.throwable.json.support.ImageIconLoader;
import org.throwable.json.support.JsonTreeViewParser;
import org.throwable.json.support.ResourceBundleManager;
import org.throwable.json.utils.FastjsonUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import static org.throwable.json.common.IconImageConstants.*;
import static org.throwable.json.common.MessageBundleKey.APPLICATION_TITLE;

/**
 * @author throwable
 * @version v1.0
 * @description 启动入口
 * @since 2017/12/9 22:53
 */
public class Application extends JFrame {

	private final JFrame jFrame;
	private JMenuBar jMenuBar;
	private TabDataModel tabDataModel;
	private TabbedContainer tabbedContainer;
	private MessageDialogManager messageDialogManager;
	private AboutDialogManager aboutDialogManager;
	private static final Map<Integer, Object> JSON_TREE_MAP = new HashMap<>();

	public Application() {
		jFrame = this;
		jFrame.setSize(Constants.WIDTH, Constants.HEIGHT);
		jFrame.setLocationRelativeTo(getOwner());
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setTitle(ResourceBundleManager.getTextByKey(APPLICATION_TITLE));
		jFrame.setIconImage(ImageIconLoader.loadImageByName(getResourcePathByName(LOGO_NAME)));
		//初始化其他全部组件
		initComponents();
		//添加主界面组件
		jFrame.setJMenuBar(jMenuBar);
		jFrame.add(tabbedContainer, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws Exception {
		//跟随系统风格
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//加载页面优先显现
		LoadingFrameManager.show();
		//主页面组件初始化
		EventQueue.invokeLater(() -> new Application().setVisible(Boolean.TRUE));
	}

	private void initComponents() {
		//初始化异常消息对话框
		messageDialogManager = new MessageDialogManager(jFrame);
		//初始化about对话框
		aboutDialogManager = new AboutDialogManager(jFrame);
		//初始化整体布局
		initMainLayout();
	}

	private void initMainLayout() {
		//初始化工具栏
		initMenuBar();
		//初始化tab容器
		initTabbedContainer();
	}

	private void initMenuBar() {
		jMenuBar = new JMenuBar();
		jMenuBar.add(createFileMenu());
		jMenuBar.add(createOptionMenu());
		jMenuBar.add(createAboutMenu());
	}

	//TODO 添加文件保存功能
	private JMenu createFileMenu() {
		JMenu menu = new JMenu();
		menu.setText(ResourceBundleManager.getTextByKey(MessageBundleKey.FILE_MENU));


		return menu;
	}

	private JMenu createAboutMenu() {
		JMenu menu = new JMenu();
		menu.setText(ResourceBundleManager.getTextByKey(MessageBundleKey.ABOUT_MENU));
		JMenuItem about = createMenuItem(MessageBundleKey.ABOUT_APPLICATION, KeyEvent.VK_I);
		about.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutDialogManager.show();
			}
		});
		menu.add(about);
		return menu;
	}

	private JMenu createOptionMenu() {
		JMenu menu = new JMenu();
		menu.setText(ResourceBundleManager.getTextByKey(MessageBundleKey.OPTION_MENU));
		JMenuItem addTab = createMenuItem(MessageBundleKey.ADD_TAB_OPTION, KeyEvent.VK_N);
		addTab.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewTab("tab", Boolean.TRUE);
			}
		});
		menu.add(addTab);
		JMenuItem format = createMenuItem(MessageBundleKey.FORMAT_OPTION, KeyEvent.VK_F);
		format.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent event) {
				formatJson();
			}
		});
		menu.add(format);
		JMenuItem minify = createMenuItem(MessageBundleKey.MINIFY_OPTION, KeyEvent.VK_M);
		minify.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minifyJson();
			}
		});
		menu.add(minify);
		return menu;
	}

	private void formatJson() {
		RSyntaxTextArea textArea = ComponentSelector.getRSyntaxTextArea(tabbedContainer);
		String text = textArea.getText();
		char c = text.trim().charAt(0);
		if (c == '{' || c == '[') {
			try {
				Object target = FastjsonUtils.parse(text);
				String json = FastjsonUtils.format(target);
				populateRSyntaxTextArea(json, textArea);
				populateTree(target);
			} catch (Exception e) {
				messageDialogManager.showMessageDialog("ERROR", e.getMessage());
			}
		}
	}

	private void minifyJson() {
		RSyntaxTextArea textArea = ComponentSelector.getRSyntaxTextArea(tabbedContainer);
		String text = textArea.getText();
		char c = text.trim().charAt(0);
		if (c == '{' || c == '[') {
			try {
				String json = FastjsonUtils.minify(text);
				populateRSyntaxTextArea(json, textArea);
			} catch (Exception e) {
				messageDialogManager.showMessageDialog("ERROR", e.getMessage());
			}
		}
	}

	private int addNewTab(String name, boolean selected) {
		TabData tabData = createTabData(name, name, null);
		int index = tabbedContainer.getTabCount();
		tabDataModel.addTab(index, tabData);
		if (selected) {
			tabbedContainer.getSelectionModel().setSelectedIndex(index);
		}
		return index;
	}

	private void populateRSyntaxTextArea(String json, RSyntaxTextArea textArea) {
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		textArea.setText(json);
	}

	private JMenuItem createMenuItem(String name, int keyCode) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.setAccelerator(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_MASK));
		menuItem.setText(ResourceBundleManager.getTextByKey(name));
		return menuItem;
	}

	private void initTabbedContainer() {
		TabData first = createTabData("main", "main", null);
		tabDataModel = new DefaultTabDataModel(new TabData[]{first});
		tabbedContainer = new TabbedContainer(tabDataModel, TabbedContainer.TYPE_EDITOR);
		tabbedContainer.getSelectionModel().setSelectedIndex(0);
		tabbedContainer.setShowCloseButton(Boolean.TRUE);
	}

	private TabData createTabData(String name, String tip, Icon icon) {
		final JSplitPane splitPane = new JSplitPane();
		splitPane.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				splitPane.setDividerLocation(0.6);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		RSyntaxTextArea textArea = RSyntaxTextAreaManager.crateRSyntaxTextArea();
		RTextScrollPane textScrollPane = new RTextScrollPane(textArea);
		textScrollPane.setFoldIndicatorEnabled(Boolean.TRUE);
		textScrollPane.setIconRowHeaderEnabled(Boolean.TRUE);
		Gutter gutter = textScrollPane.getGutter();
		gutter.setBookmarkingEnabled(Boolean.TRUE);
//		gutter.setBookmarkIcon(new ImageIcon(url));
		splitPane.setLeftComponent(textScrollPane);
		splitPane.setRightComponent(new JScrollPane(initFakeTree()));
		return new TabData(splitPane, icon, name, tip);
	}

	private JTree initFakeTree() {
		DefaultMutableTreeNode root = createTreeRootNode();
		DefaultTreeModel model = new DefaultTreeModel(root);
		JTree jTree = new JTree(model);
		createTreePopupMenu(jTree);
		return jTree;
	}

	private void createTreePopupMenu(JTree jTree) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem copyKey = new JMenuItem("copy key");
		copyKey.addActionListener(new TreeNodeCopyKeyListener(jTree));
		menu.add(copyKey);
		JMenuItem copyValue = new JMenuItem("copy value");
		copyValue.addActionListener(new TreeNodeCopyValueListener(jTree));
		menu.add(copyValue);
		JMenuItem copyKeyValue = new JMenuItem("copy key-value");
		copyKeyValue.addActionListener(new TreeNodeCopyKeyValueListener(jTree));
		menu.add(copyKeyValue);
		jTree.addMouseListener(new TreeNodeMenuListener(jTree, menu));
	}

	private JTree populateTree(Object jsonObject) {
		JTree jTree = ComponentSelector.getTree(tabbedContainer);
		DefaultMutableTreeNode root = createTreeRootNode();
		DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
		//缓存当前json对象待用
		JSON_TREE_MAP.put(jTree.hashCode(), jsonObject);
		//填充树表数据
		populateTreeNodeDate(jsonObject, root);
		jTree.setVisible(Boolean.TRUE);
		model.setRoot(root);
		//填充树表图标
//		populateTreeNodeIcon(jTree);
		return jTree;
	}

	private DefaultMutableTreeNode createTreeRootNode() {
		TreeNodePair rootNode = new TreeNodePair();
		rootNode.setType(TreeNodeType.ROOT);
		rootNode.setKey(Constants.ROOT);
		rootNode.setValue(Constants.ROOT);
		return new DefaultMutableTreeNode(rootNode);
	}

	//TODO 添加树表数据节点图标填充
	private void populateTreeNodeIcon(JTree jTree) {
		jTree.setCellRenderer(new DefaultTreeCellRenderer() {

			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value,
														  boolean sel, boolean expanded,
														  boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
				Object userObject = node.getUserObject();
				ImageIcon icon = null;
				if (null != userObject) {
					TreeNodePair pair = (TreeNodePair) userObject;
					if (TreeNodeType.ROOT.equals(pair.getType())) {
						icon = ImageIconLoader.loadImageIconByName(getResourcePathByName(JSON_ROOT));
					} else if (TreeNodeType.NODE.equals(pair.getType())) {
						icon = ImageIconLoader.loadImageIconByName(getResourcePathByName(JSON_NODE));
					} else if (TreeNodeType.LEAF.equals(pair.getType())) {
						icon = ImageIconLoader.loadImageIconByName(getResourcePathByName(JSON_LEAF));
					} else if (TreeNodeType.ARRAY.equals(pair.getType())) {
						icon = ImageIconLoader.loadImageIconByName(getResourcePathByName(JSON_ARRAY));
					}
				}
				if (null != icon) {
					this.setIcon(icon);
				}
				this.setText(node.toString());
				return this;
			}
		});
	}

	private void populateTreeNodeDate(Object jsonObject, DefaultMutableTreeNode root) {
		JsonTreeViewParser.parseJsonTree(jsonObject, root);
	}
}
