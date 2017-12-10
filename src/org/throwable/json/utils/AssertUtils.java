package org.throwable.json.utils;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/9 23:18
 */
public abstract class AssertUtils {

	public static void assertTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void assertNotNull(Object target, String message) {
		if (null == target) {
			throw new IllegalArgumentException(message);
		}
	}
}
