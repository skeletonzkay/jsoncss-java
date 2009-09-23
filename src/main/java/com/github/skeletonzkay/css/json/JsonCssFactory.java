package com.github.skeletonzkay.css.json;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


import org.json.JSONException;
import org.json.JSONObject;

import com.github.skeletonzkay.css.Css;
import com.github.skeletonzkay.css.CssFactory;
import com.github.skeletonzkay.css.CssNode;
import com.github.skeletonzkay.css.CssValue;

/**
 * JSON css paser
 * 
 * @author skeletonzkay
 *
 */
public class JsonCssFactory implements CssFactory{
	
	private Css css = new Css();
	
	public Css getCss() {
		return css;
	}

	public static CssFactory getInstance(String jsoncss) 
		throws JSONException{
		return new JsonCssFactory(jsoncss);
	}

	public static CssFactory getInstance(Reader reader) 
		throws JSONException, IOException {
		return new JsonCssFactory(reader);
	}

	private JSONObject jsonObject;

	public JsonCssFactory(Reader reader) 
		throws JSONException, IOException{
		StringBuilder builder = new StringBuilder();
		int len,BUFSIZE = 8192;
		char[] buf = new char[BUFSIZE];
		while(0<(len=reader.read(buf,0,BUFSIZE))){
			builder.append(buf,0,len);
		}
		jsonObject = new JSONObject(builder.toString());
//		jsonObject = new JSONObject(reader); //NOTE not working
		init();
	}

	protected JsonCssFactory(String jsoncss) throws JSONException{
		jsonObject = new JSONObject(jsoncss);
		init();

	}
	
	private void init() throws JSONException {
		for(Iterator<String> iter = jsonObject.keys()
					;iter.hasNext();){
			String key = iter.next();
			key = key.replaceAll(" ", "");
			addToCss(key);
		}
	}

	private void addToCss(String key) throws JSONException {
		css.put(key, getCssNode(key))	;
	}

	private CssNode getCssNode(String key) throws JSONException {
		CssNode cssNode = new CssNode();
		putCssNode(cssNode 
				, jsonObject.getJSONObject(key));
		return cssNode;
	}

	private void putCssNode(CssNode cssNode, JSONObject object) 
		throws JSONException {

		for(Iterator<String> iter = object.keys()
				;iter.hasNext();){
			String key = iter.next();
			key = key.replaceAll(" ", "");
			cssNode.put(key, getValue(object,key));
		}
	}

	private CssValue getValue(JSONObject object, String key) 
		throws JSONException {
		return new CssValue( 
				object.getString(key) );
	}
	
}
