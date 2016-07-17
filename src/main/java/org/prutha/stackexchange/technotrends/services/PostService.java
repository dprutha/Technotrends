package org.prutha.stackexchange.technotrends.services;

import java.util.ArrayList;
import java.util.List;

import org.prutha.stackexchange.technotrends.model.Post;

public class PostService {
	
	public List<Post> getAllPosts()
	{
		Post p1 = new Post(1L, "Stackexchange.com", "Prutha");
		Post p2 = new Post(2L, "Stackoverflow.com", "Siddarth");
		List<Post> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		
		return list;
	}

}
