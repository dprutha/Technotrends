package org.prutha.stackexchange.technotrends.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.prutha.stackexchange.technotrends.database.MongoDBConnection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@XmlRootElement
@Path("/login")
public class LoginResource {

	@POST
	//@Path("/login")
	@Path("/{username}/{password}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin(
			@PathParam("username") String uname,
			@PathParam("password") String pwd)
	{
        String response = "";
        if(validateUser(uname, pwd)){
            response = Utility.constructJSON("login",true);
        }else{
            response = Utility.constructJSON("login", false, "Incorrect Email or Password");
        }
        return response;        
    }
	
	/**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private boolean validateUser(String uname, String pwd)
    {
        System.out.println("Inside validateUser");
        boolean result = false;
        if(Utility.isNotNull(uname) && Utility.isNotNull(pwd))
        {
        	MongoDBConnection dbConn = null;
    		DB db = null;
    		DBCollection coll = null;
    		try
    		{
    			dbConn = MongoDBConnection.getInstance();
    			db = dbConn.getTestdb();
    			coll = db.getCollection("users");
                System.out.println("Inside validateUser try "+result);
                
                //BasicDBObject query = );
                //query.put("DisplayName", uname);
                DBCursor cursor = coll.find(new BasicDBObject("DisplayName",uname));
                System.out.println(cursor.toString());
                if(cursor.hasNext()) //validatepassowrd
                {
                	result = true;
                	//return true;
                }
                else
                {
                	System.out.println("in else");
                }
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside validateUser catch");
                result = false;
            }
        }else{
            System.out.println("Inside validateUser else");
            result = false;
        }
		return result;
 
        
    }
 
}
