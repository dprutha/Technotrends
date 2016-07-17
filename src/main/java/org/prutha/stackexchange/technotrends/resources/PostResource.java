package org.prutha.stackexchange.technotrends.resources;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.prutha.stackexchange.technotrends.database.MongoDBConnection;
import org.prutha.stackexchange.technotrends.model.Post;
import org.prutha.stackexchange.technotrends.model.User;
import org.prutha.stackexchange.technotrends.services.PostService;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Path("/posts")
public class PostResource {

	PostService postService = new PostService();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Post> getPosts()
	{
		return postService.getAllPosts();
	}
	
	@GET
	@Path("/{postId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPost(@PathParam("postId") long postId)
	{
		return "Post id " + postId;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPost(String post)
	{
		return post;
	}
	
	/*@SuppressWarnings("finally")
	@POST
	@Path("/upload")
	@Produces(MediaType.TEXT_PLAIN)
	public String addPost() {
		MongoDBConnection dbConn = null;
		DB db = null;
		DBCollection coll = null;
		try
		{
			dbConn = MongoDBConnection.getInstance();
			db = dbConn.getTestdb();
			coll = db.getCollection("Post");
			
			
			DBCursor cursor = coll.find().limit(2);
			//List<User> list = new ArrayList<User>();
			//List<Post> list = new ArrayList<Post>();
			String list="";  
			while (cursor.hasNext()) { 
		             DBObject o = cursor.next();
		             Post bools = new Post();
		             //User bools = new User();
		             //bools.setId((long) o.get("Id"));
		             //bools.setDisplayName((String) o.get("DisplayName"));
		             //bools.setReputation((long) o.get("Reputation"));
		             //bools.setId((long) o.get("Id"));
		             //list.add(bools);
		             
		             list += o.get("Id") + ":";
		             
		             
		          }
		      return list;
		}
		catch(Exception e)
		{
			System.out.println("Exception");
			//return e.getMessage();
		}
	      //DBCollection coll = db.getCollection("posts");
	    finally
	    {
	    	System.out.println(dbConn.toString());
	      //return "OK" + dbConn.toString() + db.toString();
	    	try {
				//return dbConn.toString() + ":" + db.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//return e.getMessage();
			}
	    }
		return null;
	}*/
}
