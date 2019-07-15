package com.dj.test;

import com.dj.util.ConvertTableToDomainUtil;

public class StartUp {
	
	public static void main(String[] args) {
		String inputFilePath = "txt\\inputFile.txt";
		String outputFilePath = "txt\\outputFile.txt";
		ConvertTableToDomainUtil.convertTableToDomain(inputFilePath, outputFilePath);
	}

}
