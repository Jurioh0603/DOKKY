package reply.dao;

import java.util.List;

import reply.model.Qreply;


public interface QreplyDAO {
	
 void insertReply(Qreply reply);
 
 List<Qreply> selectRepliesByBno(Long bno);
 
 void deleteReply(Long rno);
 
}

