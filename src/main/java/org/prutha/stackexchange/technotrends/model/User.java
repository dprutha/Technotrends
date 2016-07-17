package org.prutha.stackexchange.technotrends.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private long Id;
	private String DisplayName;
	private long Reputation;
	
	public User()
	{
		
	}
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	public long getReputation() {
		return Reputation;
	}
	public void setReputation(long reputation) {
		Reputation = reputation;
	}
	
	
	
}
