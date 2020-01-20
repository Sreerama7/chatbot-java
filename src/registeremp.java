

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * Servlet implementation class registeremp
 */
@WebServlet("/registeremp")
public class registeremp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registeremp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "",res="";
		if(br != null){
		json = br.readLine();

		}
		JSONObject jsonObject = new JSONObject(json);
		String name = jsonObject.getString("name").toString();
		String password =  jsonObject.getString("password").toString();
		String email =  jsonObject.getString("email").toString();
		String contact_number =  jsonObject.getString("contact").toString();
		MongoClient mongoClient=new MongoClient("localhost",27017);
		System.out.print(name);
		DB db=mongoClient.getDB("Dbox");
		 DBCollection collection = db.getCollection("Darwin");
Random random=new Random();
ArrayList<String> arr=new ArrayList<>();
arr.add("Naresh");
arr.add("Vedanth");
arr.add("Harish");
arr.add("Chaitanya");
arr.add("Srikanth");
		int randomIndex = (int) (Math.random() * arr.size());
		WriteResult k=collection.insert(new BasicDBObject().append("name", name).append("password", password).append("email", email).append("who is my manager", "Bhargava Chinthoju").append("contact number",contact_number).append("how many leaves", random.nextInt(10)).append("who is my mentor",arr.get( randomIndex )).append("how many tasks do i have",random.nextInt(10)).append("how many requests do i have",random.nextInt(10)));
			System.out.print(k);
			System.out.println(k.getN());
			if(k.getN()<=0)
			{
				JSONObject object = new JSONObject();

			     object.put("answer", "Registration Successful");
			     response.setContentType("application/json");
			     PrintWriter out = response.getWriter();
			     System.out.println(object);
			     out.print(object);

				//response.getWriter().write("Registration Successful");
			}
			else
			{
				 response.setStatus(400);
			}
	}

}
