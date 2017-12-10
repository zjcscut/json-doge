package org.throwable.json.support;

import org.throwable.json.common.Constants;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 10:50
 */
public abstract class ResourceBundleManager {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(Constants.PROPERTY_FILE_PATH, Locale.CHINA);

	public static String getTextByKey(final String key){
		return BUNDLE.getString(key);
	}
}
