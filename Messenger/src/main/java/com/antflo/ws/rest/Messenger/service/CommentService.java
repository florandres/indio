package com.antflo.ws.rest.Messenger.service;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.antflo.ws.rest.Messenger.Exception.DataNotFoundException;
import com.antflo.ws.rest.Messenger.model.Comment;
import com.antflo.ws.rest.Messenger.model.SessionUtil;


public class CommentService {

	
	public List<Comment> getAllComments(int messageId){
		 
		 Session session = SessionUtil.getSession();    
	     Query query = session.createQuery("from comment where messageId= :messageId");
	     List<Comment> comments =  query.list();
	     return comments;
		
	}
	
	
/*	public List<Comment> getAllComments(long messageId){
		 Map<Long,Comment> comments = messages.get(messageId).getComments();
		 return new ArrayList<Comment>(comments.values());
		
	}*/
	
	public Comment getComment(int id){
		Session session = SessionUtil.getSession();    
	    Query query = session.createQuery("from comment where id = :id");
	    Comment comment = (Comment) query;
		if (comment == null) {
			throw new DataNotFoundException("El comentario  " + id + " no ha sido encontrado.");
		}
		
		return comment;
	}
	
/*	public Comment getComment (long messageId, long commentId){
		ErrorMessage errorMessage = new ErrorMessage("Not Found",404,"Koushik");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}*/
	
	public Comment addComment (int messageId, Comment comment){
	
		Session session = SessionUtil.getSession();        
	    Transaction tx = session.beginTransaction();
	     addCommentq(session,comment);        
	     tx.commit();
	     session.close();
	     return comment;
	     
	}
	
	 public void addCommentq(Session session,Comment comment){
	      Comment c = new Comment();
	       c.setMessage(comment.getMessage());
	       c.setCreated(comment.getCreated());
	       c.setAuthor(comment.getAuthor());
	       c.setMessageId(comment.getMessageId());
	       session.save(c);
	        
	    }
	
	
	
/*	public Comment addComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}*/
	 
	 
		public Comment updateComment (int messageId, Comment comment){
			if (comment.getId() ==0){
				return null;
			}	
			Session session = SessionUtil.getSession();
	        Transaction tx = session.beginTransaction();
     
	        String hql = "update comment set message = :message where id = :id";
	        Query query = session.createQuery(hql);
	        query.setInteger("id",comment.getId());
	        query.setString("message",comment.getMessage());
	        query.executeUpdate();
	        tx.commit();
	        session.close();
			return comment;
			
		}
	
/*	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if (comment.getId()<=0){
			return null;
		} 
		comments.put(comment.getId(), comment);
		return comment;
	}
	*/
		
		public String removeComment(int messageId, int id){
			//return messages.remove(id);
			   Session session = SessionUtil.getSession();
		        Transaction tx = session.beginTransaction();
		        String hql = "delete from comment where id = :id";
		        Query query = session.createQuery(hql);
		        query.setInteger("id", id);
		        query.executeUpdate();
		        tx.commit();
		        session.close();
		        return "Se ha eliminado el comentario " + id + "correctamente"; 
		}
		
		
/*	public Comment removeComment(long messageId, long commentId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}*/
	
	
}
