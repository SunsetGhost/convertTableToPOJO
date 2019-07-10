package com.sf.startup;

import com.sf.util.ConvertTableToDomainUtil;

public class StartUp {
	
	public static void main(String[] args) {
		String inputFilePath = "G:\\tableToDomain\\inputFile.txt";
		String outputFilePath = "G:\\tableToDomain\\outputFile.txt";
		ConvertTableToDomainUtil.convertTableToDomain(inputFilePath, outputFilePath);
	}

}
