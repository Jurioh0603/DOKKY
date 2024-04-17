package study.model;

public class Scontent {
   
   private int bno;
   private String content;
   
   public Scontent(int bno, String content) {
      this.bno = bno;
      this.content = content;
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
