package org.throwable.json.support;

import javax.swing.*;
import java.awt.*;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 11:02
 */
public abstract class ImageIconLoader {

	private static final ClassLoader CLASS_LOADER = ImageIconLoader.class.getClassLoader();

	@SuppressWarnings("ConstantConditions")
	public static ImageIcon loadImageIconByName(String name){
		return new ImageIcon(CLASS_LOADER.getResource(name));
	}

	public static Image loadImageByName(String name){
		return loadImageIconByName(name).getImage();
	}
}
