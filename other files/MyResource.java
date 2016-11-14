package org.kusuma.com.vendor.com;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource extends vendor {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	static final String DB_URL = "jdbc:mysql://localhost:3306";
	final private String user = "root";
	final private String passwd = "Root123";
	 public vendor v=new vendor();
	 public ArrayList<vendor> varray=new ArrayList<vendor>();
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
	    return "Got it!";
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/vendor")
	public String readDataBase() throws Exception {
		 
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection(DB_URL,user,passwd);
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select * from test.vendor");
		     return writeResultSet(resultSet);
	      
		    } catch (Exception e) {
		      throw e;
		    } 
	  }

	private String writeResultSet(ResultSet resultSet2)throws SQLException  {
		// TODO Auto-generated method stub
		 StringBuilder st=new StringBuilder();
		
		while (resultSet2.next()) {
		      // It is possible to get the columns via name
		      // also possible to get the columns via the column number
		      // which starts at 1
		      // e.g. resultSet.getSTring(2);
			 vendor v=new vendor();
		      v.vendorname = resultSet2.getString("vendor_name");
		     
		      v.sensorno = resultSet2.getInt("sensor_no");
		      v.email1=resultSet2.getString("email");
		      v.contact=resultSet2.getLong("contact");
		
		      
		     varray.add(v);
		    }	
		System.out.println(varray);
	   Gson gson = new Gson();
       String json = gson.toJson(varray);
		System.out.println(json);
		String s=st.toString();
		return json;
	}
  
  
    
}    