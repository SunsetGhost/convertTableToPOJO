package com.dj.util;

import java.util.HashMap;
import java.util.Map;

import com.dj.domain.JavaType;
import com.dj.domain.MySqlType;
import com.dj.exception.MySqlTypeException;

public class MySqlAndJavaMatchUtil {
	
	private Map<MySqlType, JavaType> matchMap = new HashMap<>();
	
	public MySqlAndJavaMatchUtil() {
		initializeMatchMap();
	}
	
    private void initializeMatchMap() {
    	matchMap.put(MySqlType.BIGINT, JavaType.LONG);
    	matchMap.put(MySqlType.BLOB, JavaType.BYTES);
    	matchMap.put(MySqlType.CHAR, JavaType.STRING);
    	matchMap.put(MySqlType.DATE, JavaType.DATE);
    	matchMap.put(MySqlType.DATETIME, JavaType.DATE);
    	matchMap.put(MySqlType.DECIMAL, JavaType.BIG_DECIMAL);
    	matchMap.put(MySqlType.DOUBLE, JavaType.DOUBLE);
    	matchMap.put(MySqlType.ENUM, JavaType.STRING);
    	matchMap.put(MySqlType.FLOAT, JavaType.FLOAT);
    	matchMap.put(MySqlType.TEXT, JavaType.STRING);
    	matchMap.put(MySqlType.INT, JavaType.INTEGER);
    	matchMap.put(MySqlType.VARCHAR, JavaType.STRING);
	}
	
	public String getJavaTypeBy(MySqlType mySqlType) {
		JavaType javaType = this.matchMap.get(mySqlType);
		if(javaType == null) {
			throw new MySqlTypeException();
		}
		return javaType.getValue();
	}
	
	
	public MySqlAndJavaMatchUtil setCustomType(MySqlType mysqlType, JavaType javaType) {
		this.matchMap.put(mysqlType, javaType);
		return this;
	}
}
