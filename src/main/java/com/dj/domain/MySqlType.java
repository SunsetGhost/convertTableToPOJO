package com.sf.domain;

public enum MySqlType {
	VARCHAR("VARCHAR"),
	CHAR("CHAR"),
	TEXT("TEXT"),
	BIGINT("BIGINT"),
	INT("INT"),
	FLOAT("FLOAT"),
	DOUBLE("DOUBLE"),
	DECIMAL("DECIMAL"),
	DATE("DATE"),
	DATETIME("DATETIME"),
	ENUM("ENUM"),
	BLOB("BLOB");
	
	private String value;
	
	private MySqlType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
