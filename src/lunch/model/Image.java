package lunch.model;

public class Image {

	private String filename;
	private String filerealname;
	private int bno;
	
	public Image(String filename, String filerealname, int bno) {
		super();
		this.filename = filename;
		this.filerealname = filerealname;
		this.bno = bno;
	}

	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}

	public String getFileRealName() {
		return filerealname;
	}

	public void setFileRealName(String filerealname) {
		this.filerealname = filerealname;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
	
}
