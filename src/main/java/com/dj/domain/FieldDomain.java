package com.dj.domain;

import com.dj.util.StringUtil;

public class FieldDomain {
	
	private String fieldType; // 字段类型
	
	private String fieldName; // 字段名称
	
	private String annotation; // 注解 
	
	private String remark; // 注释
	
	private String CHANGE_LINE = "\r\n";
	
	private String SPACE = " ";
	
	private String SEMICOLON = ";";

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.annotation).append(CHANGE_LINE);
		sb.append("private").append(SPACE).append(this.fieldType).append(SPACE).append(this.fieldName).append(SEMICOLON);
		if(StringUtil.isNotEmpty(this.remark)) {
			sb.append(SPACE).append("//").append(SPACE).append(this.remark);
		}
		sb.append(CHANGE_LINE).append(CHANGE_LINE);
		return sb.toString();
	}
	
	
	
}
