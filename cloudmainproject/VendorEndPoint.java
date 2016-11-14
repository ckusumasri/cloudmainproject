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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;
import java.lang.*;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("vendor")
public class VendorEndPoint {

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
	public Vendor v=new Vendor();
	public  Gson gson = new Gson();
	public ArrayList<Vendor> varray=new ArrayList<Vendor>();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
	    return "pong!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllVendors")
	public String readDataBase() throws Exception {
	    try {
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
		    //TODO - Add log here
			return "Error in getAllVendors";
		} 
	}

	private String writeResultSet(ResultSet resultSet2)throws SQLException  {
		while (resultSet2.next()) {
			 Vendor v=new Vendor();
		      v.vendorname = resultSet2.getString("vendor_name");
		     
		      //v.sensorno = resultSet2.getInt("sensor_no");
		      v.email=resultSet2.getString("email");
		      v.contact=resultSet2.getLong("contact");
		      varray.add(v);
		}	
		System.out.println(varray);
        String json = gson.toJson(varray);
		System.out.println(json);
		return json;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertVendor")
	public String insertDataBase(String str) throws Exception {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection(DB_URL,user,passwd);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			Vendor temp = gson.fromJson(str, Vendor.class);
			String query = " INSERT INTO  test.vendor (vendor_name, email, contact)"+" values(\""+temp.vendorname+"\",\""+temp.email+"\","+temp.contact+")";
			statement.execute(query); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{ \"result\" : \"Sucess\"}";
	}
}    