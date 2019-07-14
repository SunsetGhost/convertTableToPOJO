package com.dj.startup;

import com.dj.util.ConvertTableToDomainUtil;

public class StartUp {
	
	public static void main(String[] args) {
//		String inputFilePath = "G:\\tableToDomain\\inputFile.txt";
//		String outputFilePath = "G:\\tableToDomain\\outputFile.txt";
		String inputFilePath = null;
		String outputFilePath = null;
		ConvertTableToDomainUtil.convertTableToDomain(inputFilePath, outputFilePath);
	}

}
