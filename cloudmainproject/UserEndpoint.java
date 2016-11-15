package org.kusuma.com.cloudmainproject;

import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("User")
public class UserEndpoint extends User {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	static final String DB_URL = "jdbc:mysql://localhost:3306";
	final private String user = "root";
	final private String passwd = "Root123";
	public User u=new User();
	public  Gson gson = new Gson();
	public ArrayList<User> uarray=new ArrayList<User>();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
	    return "pong!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllUser")
	public String readDataBase() throws Exception {
	    try {
		    Class.forName("com.mysql.jdbc.Driver");
	    	// Setup the connection with the DB
	    	connect = DriverManager
	          .getConnection(DB_URL,user,passwd);
	    	// Statements allow to issue SQL queries to the database
	    	statement = connect.createStatement();
	    	// Result set get the result of the SQL query
	    	resultSet = statement.executeQuery("select * from test.User");
		    return writeResultSet(resultSet);
		} catch (Exception e) {
		    //TODO - Add log here
			return "Error in getAllUser";
		} 
	}

	private String writeResultSet(ResultSet resultSet2)throws SQLException  {
		while (resultSet2.next()) {
			  User u=new User();
		      u.username = resultSet2.getString("User_name");
		      u.uemail=resultSet2.getString("uemail");
		      u.ucontact=resultSet2.getLong("ucontact");
		      uarray.add(u);
		}	
		System.out.println(uarray);
        String json = gson.toJson(uarray);
		System.out.println(json);
		return json;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertUser")
	public String insertDataBase(String str) throws Exception {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection(DB_URL,user,passwd);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			User temp = gson.fromJson(str, User.class);
			String query = " INSERT INTO  test.User (User_name, uemail, ucontact)"+" values(\""+temp.username+"\",\""+temp.uemail+"\","+temp.ucontact+")";
			statement.execute(query); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{ \"result\" : \"Sucess\"}";
	}
}    