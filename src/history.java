

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class history
 */
@WebServlet("/history")

public class history extends HttpServlet {
	 List<DBObject> list1=new ArrayList<>();
	 List<DBObject> list2=new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public history() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getSession(false).getAttribute("email").toString();
		MongoClient mongoClient=new MongoClient("localhost",27017);
        DB db=mongoClient.getDB("Dbox");
        DBCollection collection = db.getCollection("user");
        DBCollection collection1 = db.getCollection("Bot");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("email",email);
        DBCursor cursor = collection.find(whereQuery);
        DBCursor cursor1 = collection1.find(whereQuery);
        List<DBObject> res=new ArrayList<>();
        while (cursor.hasNext()) {
        	DBObject str = cursor.next();
           list1 = (List<DBObject>)str.get("user");
          }
       while (cursor1.hasNext()) {
       	DBObject str = cursor1.next();
           list2 = (List<DBObject>)str.get("bot");
         }
      String result="";
      
     for(int i=0;i<list1.size();i++)
     {
    	  result+="YOU:"+list1.get(i)+"#";
    	  result+="ALEX:"+list2.get(i)+"#";
}
     System.out.println(result);
     //response.getWriter().write(result);
     JSONObject object=new JSONObject();
     object.put("answer",result);
     response.setContentType("application/json");
     PrintWriter out = response.getWriter();
     System.out.println(object);
     out.print(object);
      
     
       
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

		
		

