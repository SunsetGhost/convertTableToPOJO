# convertTableToPOJO
Convert mysql to java POJO.

You can use like this:
public class StartUp {
	
	public static void main(String[] args) {
		String inputFilePath = "E:\\Git\\convertTableToPOJO\\txt\\inputFile.txt";
		String outputFilePath = "E:\\Git\\convertTableToPOJO\\txt\\outputFile.txt";
		ConvertTableToDomainUtil.convertTableToDomain(inputFilePath, outputFilePath);
	}

}

inputFile.txt:
`runoob_id` INT UNSIGNED AUTO_INCREMENT comment 'id',
`runoob_title` VARCHAR(100) NOT NULL comment '标题',
`runoob_author` VARCHAR(40) NOT NULL comment '作者',

outputFile.txt:
@Column(name = "RUNOOB_ID")
private Integer runoobId; // ID

@Column(name = "RUNOOB_TITLE")
private String runoobTitle; // 标题

@Column(name = "RUNOOB_AUTHOR")
private String runoobAuthor; // 作者
