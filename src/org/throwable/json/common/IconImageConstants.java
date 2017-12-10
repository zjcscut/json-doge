package org.throwable.json.common;

import static org.throwable.json.common.Constants.RESOURCE_PATH;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 11:08
 */
public class IconImageConstants {

	public static final String LOGO_NAME = "doge.jpg";

	public static final String SURPRISE_IMAGE = "surprise.jpg";

	public static final String JSON_ROOT = "json.png";

	public static final String JSON_ARRAY = "array_node.png";

	public static final String JSON_NODE = "node.png";

	public static final String JSON_LEAF = "spring.png";


	public static String getResourcePathByName(String name) {
		return String.format("%s%s", RESOURCE_PATH, name);
	}
}
