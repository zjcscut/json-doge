package org.throwable.json.common;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 19:20
 */
public enum  TreeNodeType {

	/**
	 * 根节点
	 */
	ROOT,

	/**
	 * 非叶子节点
	 */
	NODE,

	/**
	 * JSON数组节点
	 */
	ARRAY,

	/**
	 * 叶子节点
	 */
	LEAF
}
