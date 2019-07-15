package com.dj.util;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dj.domain.FieldDomain;
import com.dj.domain.MySqlType;

public class ConvertTableToDomainUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ConvertTableToDomainUtil.class);
	
	private static boolean isOutputAnnotation = false;
	
	private static boolean isOutputRemark = false;
	
	public static void convertTableToDomain(String inputFilePath, String outputFilePath) {
		if(StringUtil.isNotEmpty(inputFilePath) && StringUtil.isNotEmpty(outputFilePath)) {
			File inputFile = new File(inputFilePath);
			File outputFile = new File(outputFilePath);
			List<FieldDomain> list = readInputFile(inputFile);
			writeOutputFile(outputFile, list);
		} else {
			logger.info("inputFilePath or outputFilePath can not be empty!");
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
		String standardCurLine = standardLine(currentLine);
		if(StringUtil.isNotEmpty(standardCurLine)) {
			fieldDomain = new FieldDomain();
			if(ConvertTableToDomainUtil.isOutputRemark) {
				setRemark(fieldDomain, standardCurLine);
			}
			String[] array = standardCurLine.split(" ");
			if(ConvertTableToDomainUtil.isOutputRemark) {
				setAnnotation(fieldDomain, array[0]);
			}
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
			return currentLine.trim().replaceAll("[\\t|\\s]+", " ").toUpperCase();
		}
		return null;
	}

	public static boolean isOutputAnnotation() {
		return isOutputAnnotation;
	}

	public static void setOutputAnnotation(boolean isOutputAnnotation) {
		ConvertTableToDomainUtil.isOutputAnnotation = isOutputAnnotation;
	}

	public static boolean isOutputRemark() {
		return isOutputRemark;
	}

	public static void setOutputRemark(boolean isOutputRemark) {
		ConvertTableToDomainUtil.isOutputRemark = isOutputRemark;
	}
	
}
