# convertTableToPOJO
Convert mysql to java POJO.

You can use like this:

public class StartUp {
	
	public static void main(String[] args) {
		String inputFilePath = "txt\\inputFile.txt";
		String outputFilePath = "txt\\outputFile.txt";
		ConvertTableToDomainUtil.convertTableToDomain(inputFilePath, outputFilePath);
	}

}


inputFile.txt:

`runoob_id` INT UNSIGNED AUTO_INCREMENT comment 'id',\r\n
`runoob_title` VARCHAR(100) NOT NULL comment '标题',\r\n
`runoob_author` VARCHAR(40) NOT NULL comment '作者',

outputFile.txt:
private Integer runoobId; // ID

private String runoobTitle; // 标题

private String runoobAuthor; // 作者
