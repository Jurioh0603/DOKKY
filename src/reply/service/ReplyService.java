package reply.service;

import java.util.List;

import reply.model.Qreply;


	public interface ReplyService {
		
	    void addReply(Qreply reply);
	    
	    List<Qreply> getRepliesByBno(Long bno);
	    
	    void removeReply(Long rno);
	}

