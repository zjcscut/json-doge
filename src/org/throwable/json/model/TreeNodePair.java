package org.throwable.json.model;

import org.throwable.json.common.TreeNodeType;

/**
 * @author throwable
 * @version v1.0
 * @description 数节点数据模型
 * @since 2017/12/10 19:20
 */
public class TreeNodePair {

	private TreeNodeType type;

	private String key;

	private Object value;

	public TreeNodeType getType() {
		return type;
	}

	public TreeNodePair setType(TreeNodeType type) {
		this.type = type;
		return this;
	}

	public String getKey() {
		return key;
	}

	public TreeNodePair setKey(String key) {
		this.key = key;
		return this;
	}

	public Object getValue() {
		return value;
	}

	public TreeNodePair setValue(Object value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		if (TreeNodeType.ROOT.equals(type) || TreeNodeType.NODE.equals(type) || TreeNodeType.ARRAY.equals(type)) {
			return key;
		}
		return String.format("%s:%s", key, value);
	}
}
