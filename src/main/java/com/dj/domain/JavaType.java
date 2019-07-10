package com.dj.domain;

public enum JavaType {
	STRING("String"),
	INTEGER("Integer"),
	LONG("Long"),
	DATE("Date"),
	FLOAT("Float"),
	DOUBLE("Double"),
	BIG_INTEGER("BigInteger"),
	BIG_DECIMAL("BigDecimal"),
	BYTES("Byte[]");
	
	private String value;
	
	private JavaType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
