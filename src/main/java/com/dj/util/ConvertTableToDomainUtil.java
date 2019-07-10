package com.sf.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sf.domain.FieldDomain;
import com.sf.domain.MySqlType;

public class ConvertTableToDomainUtil {
	
	public static void convertTableToDomain(String inputFilePath, String outputFilePath) {
		if(StringUtil.isNotEmpty(inputFilePath) && StringUtil.isNotEmpty(outputFilePath)) {
			File inputFile = new File(inputFilePath);
			File outputFile = new File(outputFilePath);
			List<FieldDomain> list = readInputFile(inputFile);
			writeOutputFile(outputFile, list);
		}
	}
	
	private static void writeOutputFile(File outputFile, List<FieldDomain> list) {
		try {
			if(outputFile.exists()) {
				outputFile.delete();
			}
			if(!outputFile.exists()) {
				outputFile.createNewFile();
			}
			OutputStream outputStream = new FileOutputStream(outputFile);
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
			BufferedWriter outputFileWriter = new BufferedWriter(writer);
			if(list != null && list.size() > 0) {
				for(FieldDomain domain : list) {
					outputFileWriter.write(domain.toString());
				}
			}
			if(outputFileWriter != null) {
				outputFileWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static List<FieldDomain> readInputFile(File inputFile) {
		List<FieldDomain> list = new ArrayList<>();
		BufferedReader bufferedReader = null;
		InputStreamReader fileReader = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(inputFile);
			fileReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(fileReader);
			String currentLine = null;
			while(StringUtil.isNotEmpty(currentLine = bufferedReader.readLine())) {
				FieldDomain fieldDomain = convert(currentLine);
				if(fieldDomain != null) {
					list.add(fieldDomain);
				}
			}
			if(fileReader != null) {
				fileReader.close();
			}
			if(bufferedReader != null) {
				bufferedReader.close();
			}
			if(inputStream != null) {
				inputStream.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static FieldDomain convert(String currentLine) {
		FieldDomain fieldDomain = null;
		if(StringUtil.isNotEmpty(standardLine(currentLine))) {
			fieldDomain = new FieldDomain();
			setRemark(fieldDomain, currentLine);
			String[] array = currentLine.split(" ");
			setAnnotation(fieldDomain, array[0]);
			setFieldType(fieldDomain, array[1]);
			setFieldName(fieldDomain, array[0]);
		}
		return fieldDomain;
	}
	
	private static void setRemark(FieldDomain fieldDomain, String currentLine) {
		if(currentLine.contains(" COMMENT ")) {
			String remark = currentLine.substring(currentLine.indexOf(" COMMENT "));
			remark = remark.replace(" COMMENT ", "");
			remark = remark.substring(0, remark.length() - 1);
			remark = remark.replace("'", "");
			fieldDomain.setRemark(remark);
		}
	}
	
	private static void setFieldType(FieldDomain fieldDomain, String str) {
		if(str.contains("(")) {
			str = str.toUpperCase().substring(0, str.indexOf("("));
		} else {
			str = str.toUpperCase();
		}
		MySqlAndJavaMatchUtil mySqlAndJavaMatchUtil = new MySqlAndJavaMatchUtil();
		fieldDomain.setFieldType(mySqlAndJavaMatchUtil.getJavaTypeBy(MySqlType.valueOf(str)));
	}
	
	private static void setFieldName(FieldDomain fieldDomain, String str) {
		str = standardLine(str).replace("`", "").toLowerCase();
		Pattern pattern = Pattern.compile("_\\w{1}");
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			String group = matcher.group();
			str = str.replace(group, group.substring(1).toUpperCase());
		}
		fieldDomain.setFieldName(str);
	}
	
	private static void setAnnotation(FieldDomain fieldDomain, String str) {
		str = standardLine(str).replace("`", "").toUpperCase();
		fieldDomain.setAnnotation("@Column(name = \"" + str + "\")");
	}
	
	private static String standardLine(String currentLine) {
		if(StringUtil.isNotEmpty(currentLine)) {
			return currentLine.trim().replaceAll("[\\t|\\s]+", " ");
		}
		return null;
	}
	
	public static void main(String[] args) {
		String str = "`util_type` INT(2) NULL             DEFAULT NULL COMMENT '实用性航班类型:1主用 ，2备用',";
		System.out.println(str = str.trim().replaceAll("[\\t|\\s]+", "&&&")); // 移除首尾空格
		System.out.println(str = str.replaceAll("[\\t|\\s]+", "&&&"));
//		String str1 = "send_belong_network_code";
//		Pattern pattern = Pattern.compile("_\\w{1}");
//		Matcher matcher = pattern.matcher(str1);
//		while(matcher.find()) {
//			String group = matcher.group();
//			str1 = str1.replace(group, group.substring(1).toUpperCase());
////			System.out.println(matcher.group());
//		}
//		System.out.println(JavaType.BIG_DECIMAL);
//		convert(str);
	}

}
