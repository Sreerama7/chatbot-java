

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.w3c.dom.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class chat
 */
@WebServlet("/chat")
public class chat extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	{
		 HttpSession session = request.getSession();
		
		try
		{
			
		
		
		String email=request.getSession(false).getAttribute("email").toString();
		System.out.println(email);
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "",res="";
		if(br != null){
		json = br.readLine();

		}
		String chat="";
		JSONObject jsonObject = new JSONObject(json);
		response.setContentType("text/html;charset=UTF-8");
		String text1 = (String) jsonObject.getString("text");
		String text=text1.toLowerCase();
		int flag=0;
		if(text.equals("hello")||text.equals("hi"))
		{
		
			Database d=new Database();
			d.add("hi", ""+text, ""+email);
	         JSONObject object = new JSONObject();
		     object.put("answer", "hi");
		     response.setContentType("application/json");
		     PrintWriter out = response.getWriter();
		     System.out.println(object);
		     out.print(object);
		}
		else if(text.equals("bye"))
		{		 response.setStatus(400);
			
		}
		else
		{
		
			 Database d=new Database();
			 DBCollection collection=d.GetCollection("Darwin");
			 BasicDBObject whereQuery = new BasicDBObject();
	         whereQuery.put("email",email);
             DBCursor cursor = collection.find(whereQuery);
             while(cursor.hasNext()) {
        	 BasicDBObject object = (BasicDBObject) cursor.next();
             String answer = object.getString(text);
        	 if(answer!=null)
        	 {
        		answer=answer; 
        	 }
        	 else
        	 {
        		 answer="Sorry,I'm unable to understand!";
            	 
        	 }
 			d.add(answer, ""+text, ""+email);
        	 if(answer.length()>0)
        	 {
	         object.put("answer",answer);
		     response.setContentType("application/json");
		     PrintWriter out = response.getWriter();
		     System.out.println(object);
		     out.print(object);
        	 break;
        	 }
        	
         }
        	 
        
		    
	}
		
		
}
		catch(NullPointerException e)
		{
      response.setStatus(400);	
		}
	}
}
}

	
