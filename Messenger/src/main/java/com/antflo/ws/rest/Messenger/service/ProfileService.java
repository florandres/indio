package com.antflo.ws.rest.Messenger.service;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.antflo.ws.rest.Messenger.Exception.DataNotFoundException;
import com.antflo.ws.rest.Messenger.model.Profile;
import com.antflo.ws.rest.Messenger.model.SessionUtil;

public class ProfileService {

	
	
	public ProfileService(){
		
	}
	
	
	
	public List<Profile> getAllProfiles(){
		//return new ArrayList<Profile>(profiles.values());
		 Session session = SessionUtil.getSession();    
	     Query query = session.createQuery("from profile");
	     List<Profile> profiles = query.list();
	     return profiles;
		
	}
	
	public Profile getProfile(String profileName){
		Session session = SessionUtil.getSession();    
	    Query query = session.createQuery("from profile where profileName = :profileName");
	    Profile profile = (Profile) query;
		if (profile == null) {
			throw new DataNotFoundException("El perfil " + profileName + " no ha sido encontrado.");
		}
		
		return profile;
	}
	
	public Profile addProfile (Profile profile){
		//message.setId(messages.size()+1);
		//messages.put(message.getId(), message);
		//return message;	
		Session session = SessionUtil.getSession();        
	    Transaction tx = session.beginTransaction();
	     addProfileq(session,profile);        
	     tx.commit();
	     session.close();
	     return profile;
	     
	}

	 public void addProfileq(Session session,Profile profile){
	      Profile p = new Profile();
	       p.setProfileName(profile.getProfileName());
	      p.setFirstName(profile.getFirstName());
	      p.setLastName(profile.getLastName());
	       session.save(p);
	        
	    }
	
	 public Profile updateProfile (Profile profile) {
		 if (profile.getId() == 0) {
			 return null;
		 }
		 
		 Session session = SessionUtil.getSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "update profile set profileName = :profileName, firstName= :firstName, lastName= :lastName where id = :id";
	        Query query = session.createQuery(hql);
	        query.setInteger("id",profile.getId());
	        query.setString("profileName",profile.getProfileName());
	        query.setString("firstName",profile.getFirstName());
	        query.setString("lastName",profile.getLastName());
	        query.executeUpdate();
	        tx.commit();
	        session.close();
			return profile;
	 }
	 
		
		public String removeProfile(String profileName){
			//return messages.remove(id);
			   Session session = SessionUtil.getSession();
		        Transaction tx = session.beginTransaction();
		        String hql = "delete from profile where profileName = :profileName";
		        Query query = session.createQuery(hql);
		        query.setString("profileName", profileName);
		        query.executeUpdate();
		        tx.commit();
		        session.close();
		        return "Se ha eliminado el perfil " + profileName + "correctamente"; 
		}
		
			
	
	
	/*public Profile addProfile (Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;	
	}
	
	public Profile updateProfile (Profile profile){
		if (profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
	*/
	
	
}
