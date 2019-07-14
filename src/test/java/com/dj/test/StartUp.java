package com.dj.test;

import com.dj.util.ConvertTableToDomainUtil;

public class StartUp {
	
	public static void main(String[] args) {
		String inputFilePath = "E:\\Git\\convertTableToPOJO\\txt\\inputFile.txt";
		String outputFilePath = "E:\\Git\\convertTableToPOJO\\txt\\outputFile.txt";
		ConvertTableToDomainUtil.convertTableToDomain(inputFilePath, outputFilePath);
	}

}
