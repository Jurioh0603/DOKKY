
package lunch.model;

public class Lcontent {
   
   private int bno;
   private String content;
   private String filerealname;
   
   public Lcontent(int bno, String content, String filerealname) {
	super();
	this.bno = bno;
	this.content = content;
	this.filerealname = filerealname;
   }

   public String getFilerealname() {
	return filerealname;
   }

   public void setFilerealname(String filerealname) {
	this.filerealname = filerealname;
   }

   public int getBno() {
      return bno;
   }

   public void setBno(int bno) {
      this.bno = bno;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }
}
