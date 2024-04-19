package lunch.model;

public class Image {

	String fileName;
	String fileRealName;
	int bNo;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	
	public Image(int iNo, String fileName, String fileRealName, int bNo) {
		super();
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.bNo = bNo;
	}
	
	
}