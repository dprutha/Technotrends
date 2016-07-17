package org.prutha.stackexchange.technotrends.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {
	
	private long id;
	private String post;
	private Date CreationDate;
	private String OwnerUserId;
	private int Score;
	
	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public Post()
	{
		
	}
	
	public Post(long id, String post, String author)
	{
		this.id = id;
		this.post = post;
		this.OwnerUserId = author;
		this.CreationDate = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Date getCreated() {
		return CreationDate;
	}
	public void setCreated(Date created) {
		this.CreationDate = created;
	}
	public String getAuthor() {
		return OwnerUserId;
	}
	public void setAuthor(String author) {
		this.OwnerUserId = author;
	}
	
	

}
