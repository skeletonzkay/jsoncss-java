package com.github.skeletonzkay.css;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author skeletonzkay
 *
 */
public class Css {
	
	private Map<String, CssNode> map = new HashMap<String, CssNode>();
	
	public CssNode get(String key){
		return map.get(key);
	}

	public void put(String key,CssNode value){
		map.put(key,value);
	}
	
	public String getValue(String path) throws CssException{
		String[] keys = path.split("/");
		CssValue value = getNode(replace(keys[0]))
							.get(replace(keys[1]));
		return value.getValue();
	}

	private CssNode getNode(String key) throws CssException {
		CssNode node = get(key);
		if( CSSUtil.DEBUG ){
		if( node == null ){ 
			throw new CssException("selected node is not found"); 
		}
		}
		return node;
	}

	private String replace(String string) {
		return string.replaceAll(" ", "");
	}


}
