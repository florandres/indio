package com.antflo.ws.rest.Messenger.model;


import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "message")
public class Message {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	 @Column
	private String text;
	 @Column
	private Date created;
	 @Column
	private String author;
	
//	private List<Link> links = new ArrayList<>();
	
	public Message(){
		
	}
	
	
	public Message(int id, String text, String author) {
		this.id = id;
		this.text = text;
		this.created = new Date();
		this.author = author;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	

	/*public List<Link> getLinks() {
		return links;
	}


	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url, String rel){
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	*/
}
