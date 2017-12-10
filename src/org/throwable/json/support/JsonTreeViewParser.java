package org.throwable.json.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.throwable.json.common.Constants;
import org.throwable.json.common.TreeNodeType;
import org.throwable.json.model.TreeNodePair;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 19:53
 */
public abstract class JsonTreeViewParser {

	public static void parseJsonTree(Object jsonObject, DefaultMutableTreeNode root) {
		if (jsonObject instanceof JSONObject) {
			parseJsonObject((JSONObject) jsonObject, root);
		} else if (jsonObject instanceof JSONArray) {
			parseJsonArray((JSONArray) jsonObject, root, "");
		}
	}

	private static void parseJsonObject(JSONObject jsonObject, DefaultMutableTreeNode root) {
		if (null == jsonObject || jsonObject.isEmpty()) {
			return;
		}
		List<String> keys = new ArrayList<>(jsonObject.keySet());
		Collections.sort(keys);
		keys.forEach(key -> {
			Object value = jsonObject.get(key);
			if (value instanceof JSONObject) {
				DefaultMutableTreeNode node = createNode(key, value);
				parseJsonObject((JSONObject) value, node);
				root.add(node);
			} else if (value instanceof JSONArray) {
				parseJsonArray((JSONArray) value, root, key);
			} else if (value instanceof String) {
				root.add(createLeaf(key, value));
			} else if (value instanceof Number) {
				root.add(createLeaf(key, value));
			} else if (value instanceof Boolean) {
				root.add(createLeaf(key, value));
			} else if (value == null) {
				root.add(createLeaf(key, Constants.NULL));
			}
		});
	}

	private static void parseJsonArray(JSONArray jsonArray, DefaultMutableTreeNode root, String key) {
		int index = 0;
		DefaultMutableTreeNode topNode = createArrayNode(key, null);
		for (Object value : jsonArray) {
			if (value instanceof JSONObject) {
				DefaultMutableTreeNode childNode = createNode(String.valueOf(index), value);
				parseJsonObject((JSONObject) value, childNode);
				topNode.add(childNode);
			} else if (value instanceof JSONArray) {
				parseJsonArray((JSONArray) value, topNode, String.valueOf(index));
			} else if (value instanceof String) {
				topNode.add(createLeaf(String.valueOf(index), value));
			} else if (value instanceof Number) {
				topNode.add(createLeaf(String.valueOf(index), value));
			} else if (value instanceof Boolean) {
				topNode.add(createLeaf(String.valueOf(index), value));
			} else if (value == null) {
				topNode.add(createLeaf(String.valueOf(index), Constants.NULL));
			}
			++index;
		}
		root.add(topNode);
	}

	private static TreeNodePair createNewPair(String key, Object value) {
		TreeNodePair pair = new TreeNodePair();
		pair.setKey(key);
		pair.setValue(value);
		return pair;
	}

	private static DefaultMutableTreeNode createNode(String key, Object value) {
		TreeNodePair pair = createNewPair(key, value);
		pair.setType(TreeNodeType.NODE);
		return new DefaultMutableTreeNode(pair);
	}

	private static DefaultMutableTreeNode createLeaf(String key, Object value) {
		TreeNodePair pair = createNewPair(key, value);
		pair.setType(TreeNodeType.LEAF);
		return new DefaultMutableTreeNode(pair);
	}

	private static DefaultMutableTreeNode createArrayNode(String key, Object value) {
		TreeNodePair pair = createNewPair(key, value);
		pair.setType(TreeNodeType.ARRAY);
		return new DefaultMutableTreeNode(pair);
	}
}
