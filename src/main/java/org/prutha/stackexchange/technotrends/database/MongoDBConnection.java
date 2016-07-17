package org.prutha.stackexchange.technotrends.database;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
 
public class MongoDBConnection {
	private static MongoDBConnection mongoDbConn;
    private static MongoClient mongoClient;
    private static DB db ;
    
    private static final String dbHost = "192.168.0.100";
    private static final int dbPort = 27017;
    private static final String dbName = "maths";
   // private static final String dbUser = "dbUser here";
   // private static final String dbPassword = "dbPassword here";
 
    private MongoDBConnection(){};
  
    public static MongoDBConnection getInstance(){
    	if(mongoDbConn == null){
    		mongoDbConn = new MongoDBConnection();
    	}
    	return mongoDbConn;
    } 
  
    public DB getTestdb() throws UnknownHostException{
    	if(mongoClient == null){
    		mongoClient = new MongoClient(dbHost , dbPort);
    	}
    	if(db == null)
    		db = mongoClient.getDB(dbName);
    	/*if(!db.isAuthenticated()){
    		boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
    	}*/
    	return db;
    }
}
