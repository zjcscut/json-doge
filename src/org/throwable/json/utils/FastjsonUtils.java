package org.throwable.json.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.throwable.json.exception.FastJsonParseException;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/12/10 12:36
 */
public abstract class FastjsonUtils {

	private static final Feature[] PARSE_FEATURE = {Feature.AllowSingleQuotes};
	private static final SerializerFeature[] SERIALIZER_FEATURE = {SerializerFeature.WriteMapNullValue,
			SerializerFeature.PrettyFormat};

	public static String parseAndFormatJson(String text) {
		return format(parse(text));
	}

	public static Object parse(String text){
		try {
			return JSON.parse(text, PARSE_FEATURE);
		}catch (Exception e){
			throw new FastJsonParseException(e);
		}
	}

	public static String format(Object target){
		try {
			return JSON.toJSONString(target, SERIALIZER_FEATURE);
		}catch (Exception e){
			throw new FastJsonParseException(e);
		}
	}

	public static String minify(String jsonText){
		try {
			Object target = JSON.parse(jsonText, PARSE_FEATURE);
			return JSON.toJSONString(target);
		}catch (Exception e){
			throw new FastJsonParseException(e);
		}
	}
}
