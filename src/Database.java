import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Database 
{
	public DBCollection GetCollection(String name) throws UnknownHostException
	{
		
		MongoClient mongoClient=new MongoClient("localhost",27017);
		 DB db=mongoClient.getDB("Dbox");
		 DBCollection collection = db.getCollection(name);
		 return collection;
	     
	
		
		
   }
	
	public void add(String bot,String user,String email) throws UnknownHostException
	{
		MongoClient mongoClient=new MongoClient("localhost",27017);
		 DB db=mongoClient.getDB("Dbox");
		 DBCollection collection1 = db.getCollection("Bot");
         DBCollection collection2 = db.getCollection("user");
         BasicDBObject whereQuery = new BasicDBObject();
         whereQuery.put("email",email);
         DBCursor cursor = collection1.find(whereQuery);
         DBCursor cursor1 = collection2.find(whereQuery);

         while(cursor.hasNext()) {
         DBObject obj=cursor.next();
        
         DBObject listItem1 = new BasicDBObject("bot",bot);
         DBObject updateQuery1 = new BasicDBObject("$push", listItem1);
         collection1.update(obj, updateQuery1);
         }
         
         while(cursor1.hasNext()) {
	         DBObject obj=cursor1.next();
	         DBObject listItem2 = new BasicDBObject("user",user);
	      DBObject updateQuery2 = new BasicDBObject("$push", listItem2);
        	collection2.update(obj, updateQuery2);

	}
	
	}

	
}
