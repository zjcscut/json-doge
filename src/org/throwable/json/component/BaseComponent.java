package org.throwable.json.component;

/**
 * @author throwable
 * @version v1.0
 * @description 禁止组件管理器实例化
 * @since 2017/12/9 23:12
 */
public abstract class BaseComponent {

	protected BaseComponent() {
		throw new IllegalAccessError();
	}
}
