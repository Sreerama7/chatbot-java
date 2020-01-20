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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

@SuppressWarnings("deprecation")
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));

String json = "",res="";
if(br != null){
json = br.readLine();
}
System.out.println();
JSONObject jsonObject = new JSONObject(json);
String name = jsonObject.getString("name").toString();
String password =  jsonObject.getString("password").toString();
System.out.println(name);
response.setContentType("application/json");
Database d= new Database();
DBCollection collection=d.GetCollection("Darwin");
System.out.println(collection);
 List obj = new ArrayList();
 obj.add(new BasicDBObject("email", name));
 obj.add(new BasicDBObject("password", password));
 BasicDBObject whereQuery = new BasicDBObject();
 whereQuery.put("$and", obj);

int flag=0;
 DBCursor cursor = collection.find(whereQuery);
 for(DBObject doc : cursor) {
     flag=1;
    }
 if(flag==1)
 {
	 HttpSession session=request.getSession();  
     session.setAttribute("email",name); 
     System.out.println(name);
     JSONObject object = new JSONObject();

     object.put("answer", "Login Succesful");
     response.setContentType("application/json");
     PrintWriter out = response.getWriter();
     System.out.println(object);
     
     out.print(object);

	 //response.getWriter().write("Login Succesful"); 
	 
	 
 }
 else
 {
	 response.setStatus(400);
 }
 }
}

