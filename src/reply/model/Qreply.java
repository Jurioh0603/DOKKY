package reply.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Qreply{
    private long rno;
    private long bno;
    private String memid;
    private String rcontent;
    private long parentRno;
    private LocalDateTime createdAt;
    
    
	public long getRno() {
		return rno;
	}
	public void setRno(long rno) {
		this.rno = rno;
	}
	public long getBno() {
		return bno;
	}
	public void setBno(long bno) {
		this.bno = bno;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public long getParentRno() {
		return parentRno;
	}
	public void setParentRno(long parentRno) {
		this.parentRno = parentRno;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
	    this.createdAt = createdAt;
	}
    
}
