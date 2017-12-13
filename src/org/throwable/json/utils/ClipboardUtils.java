package org.throwable.json.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/10/3 下午6:27
 */
public abstract class ClipboardUtils {

	public static void resetClipboardContent(String content) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = new StringSelection(content);
		clipboard.setContents(transferable, null);
	}
}
