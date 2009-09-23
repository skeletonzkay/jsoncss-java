package com.github.skeletonzkay.css;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author skeletonzkay
 *
 */
public class CssNode {
	
	private Map<String, CssValue> map = new HashMap<String, CssValue>();
	
	public CssValue get(String key) throws CssException{
		CssValue value = map.get(key);
		if( CSSUtil.DEBUG ){
		if( value == null ){ 
			throw new CssException("selected node value is not found"); 
		}
		}
		return value;
	}
	
	public void put(String key,CssValue value){
		map.put(key, value);
	}

}
