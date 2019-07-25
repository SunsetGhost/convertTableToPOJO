package com.dj.test;

import com.dj.domain.RemarkLocation;
import com.dj.util.ConvertTableToDomainUtil;

public class StartUp {
	
	public static void main(String[] args) {
		String inputFilePath = "txt\\inputFile.txt";
		String outputFilePath = "txt\\outputFile.txt";
		ConvertTableToDomainUtil convertUtil = new ConvertTableToDomainUtil();
		convertUtil.setOutputRemark(true).setOutputAnnotation(true).setRemarkLocation(RemarkLocation.TOP_OF_FIELD).convertTableToDomain(inputFilePath, outputFilePath);
	}

}
