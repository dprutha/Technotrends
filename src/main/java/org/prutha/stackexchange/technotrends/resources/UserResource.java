package org.prutha.stackexchange.technotrends.resources;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.prutha.stackexchange.technotrends.database.MongoDBConnection;
import org.prutha.stackexchange.technotrends.database.MySQLDBConnection;
import org.prutha.stackexchange.technotrends.model.Post;
import org.prutha.stackexchange.technotrends.model.User;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Path("/users")
public class UserResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers()
	{
		return "OK";
	}
	
	/*@SuppressWarnings("finally")
	@GET
	@Path("/trendingusers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getTrendingUsers() {
		MongoDBConnection dbConn = null;
		DB db = null;
		DBCollection coll = null;
		try
		{
			dbConn = MongoDBConnection.getInstance();
			db = dbConn.getTestdb();
			coll = db.getCollection("users");
			
			DBCursor cursor = coll.find().sort(new BasicDBObject("Reputation", -1)).limit(10);
			List<User> list = new ArrayList<User>();
			//String list="";  
			while (cursor.hasNext()) { 
		             DBObject o = cursor.next();
		             User bools = new User();
		             bools.setId((int)o.get("Id"));
		             bools.setDisplayName((String)o.get("DisplayName"));
		             bools.setReputation((int)o.get("Reputation"));
		             list.add(bools);
		             
		             //list += o.get("Id") + ":" + o.get("Reputation") + ";";
		             
		          }
		      return list;
		}
		catch(Exception e)
		{
			System.out.println("Exception" + e.getMessage());
		}
	    finally
	    {
	    	System.out.println(dbConn.toString());
	    }
		return null;
	}
	
	@GET
	@Path("/graph") //Reputation and Creating time
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getGraph()
	{
		MongoDBConnection dbConn = null;
		DB db = null;
		DBCollection coll = null;
		try
		{
			dbConn = MongoDBConnection.getInstance();
			db = dbConn.getTestdb();
			coll = db.getCollection("Post");
			
			BasicDBObject selectId = new BasicDBObject();
			selectId.put("OwnerUserId", new BasicDBObject("$eq",5));
			
			BasicDBObject fields = new BasicDBObject();
			fields.put("CreationDate", 1);
			fields.put("Score", 1);

			DBCursor cursor = coll.find(selectId,fields);
			System.out.println(cursor);
			//String list = "";
			List<Post> list = new ArrayList<>();
			while(cursor.hasNext())
			{
				DBObject o = cursor.next();
				Post p = new Post();
				
				DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				Date result = dtf.parse((String) o.get("CreationDate"));
				p.setCreated(result);
				p.setScore((int) o.get("Score"));
				list.add(p);
				//list += o.get("CreationDate").toString() + "-" + o.get("Score").toString();
			}
			//System.out.println(list);
			return list;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
		}
		return null;
		
	}*/
	
	@GET
	@Path("/trendingusers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getTrendingUsers()
	{
		Connection dbConn = null;
		
		try
		{
			dbConn = MySQLDBConnection.createConnection();
			Statement stmt = dbConn.createStatement();

			String query = "SELECT DisplayName,Reputation FROM user"
                    + "ORDER BY Reputation DESC LIMIT 10";
			System.out.println("dbconn :" + dbConn.toString());
			ResultSet rs = stmt.executeQuery(query);
			List<User> list = new ArrayList<>();
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
            	User userObj = new User();
            	userObj.setDisplayName(rs.getString(1));
            	userObj.setReputation(rs.getLong(2));
            	list.add(userObj);
            }
            return list;
		}
		catch(Exception e)
		{
			System.out.println(dbConn.toString());
			System.out.println("Exception" + e.getMessage());
		}
	    finally
	    {
	    	System.out.println("dbcon finally :" + dbConn.toString());
	    	if (dbConn != null) {
                try {
					dbConn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
	    }
		return null;
	}
	
	@GET
	@Path("/graph/{owneruserid}") //Reputation and Creating time
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getGraph(@PathParam("owneruserid") String ownerUserId)
	{
		Connection dbConn = null;
		
		try
		{
			dbConn = MySQLDBConnection.createConnection();
			Statement stmt = dbConn.createStatement();

			String query = "SELECT CreationDate,Score FROM Post"
                    + "WHERE OwnerUserId=" + "'" + ownerUserId + "'";
	
			ResultSet rs = stmt.executeQuery(query);
			List<Post> list = new ArrayList<>();
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
            	Post postObj = new Post();
            	postObj.setCreated(rs.getDate(1));
            	postObj.setScore(rs.getInt(2));
            	list.add(postObj);
            }
            return list;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(dbConn!=null)
			{
				try {
					dbConn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}
	
	
	
}
