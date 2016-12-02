package com.antflo.ws.rest.Messenger.service;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.antflo.ws.rest.Messenger.Exception.DataNotFoundException;
import com.antflo.ws.rest.Messenger.model.Message;
import com.antflo.ws.rest.Messenger.model.SessionUtil;


public class MessageService {


	public MessageService(){
	
	}


	
	public List<Message> getAllMessages(){
		//return new ArrayList<Message>(messages.values());
		 Session session = SessionUtil.getSession();    
	     Query query = session.createQuery("from message");
	     List<Message> messages = query.list();
	    
	     return messages;
		
	}

	
	
/*	public List<Message> getAllMessagesForYear (int year){
		Session session = SessionUtil.getSession();  
		Query query = session.createQuery("from messages where year() = :year");
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message: messages.values()){
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	
	public List<Message> getAllMessagesPaginated (int start, int size){
		ArrayList<Message> list = new ArrayList<Message> (messages.values());
		if (start+size> list.size()) return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	*/
	
	
	
	public Message getMessage(int id){
		Session session = SessionUtil.getSession();    
	    Query query = session.createQuery("from message where id = :id");
	    Message message = (Message) query;
		if (message == null) {
			throw new DataNotFoundException("El mensaje " + id + " no ha sido encontrado.");
		}
		
		return message;
	}
	
	public Message addMessage (Message message){
		//message.setId(messages.size()+1);
		//messages.put(message.getId(), message);
		//return message;	
		Session session = SessionUtil.getSession();        
	    Transaction tx = session.beginTransaction();
	     addMessageq(session,message);        
	     tx.commit();
	     session.close();
	     return message;
	     
	}

	 public void addMessageq(Session session,Message message){
	       Message m = new Message();
	       m.setText(message.getText());
	       m.setCreated(message.getCreated());
	       m.setAuthor(message.getAuthor());
	       session.save(m);
	        
	    }
	    
	   
	
	
	
	
	
	public Message updateMessage (Message message){
		if (message.getId() ==0){
			return null;
		}
	
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "update message set text = :text where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",message.getId());
        query.setString("text",message.getText());
        query.executeUpdate();
        tx.commit();
        session.close();
		return message;
		
	}
	
	
	
	
	
	public String removeMessage(int id){
		//return messages.remove(id);
		   Session session = SessionUtil.getSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "delete from message where id = :id";
	        Query query = session.createQuery(hql);
	        query.setInteger("id", id);
	        query.executeUpdate();
	        tx.commit();
	        session.close();
	        return "Se ha eliminado el mensaje " + id + "correctamente"; 
	}
	
	
	
	
}
