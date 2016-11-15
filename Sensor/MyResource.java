package org.cloud.mobilesensor.ui;

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
	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/schema";
	final private String user = "root";
	final private String passwd = "root";
	 public vendor v=new vendor();
	 public sensor s=new sensor();
	 public ArrayList<vendor> varray=new ArrayList<vendor>();
	 public ArrayList<sensor> sensorarray=new ArrayList<sensor>();
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
	      
	      Class.forName("com.mysql.jdbc.Driver").newInstance();  
	     
	      Connection con=DriverManager.getConnection(DB_URL,user,passwd);  
	     	     
	      statement = con.createStatement();
	     // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select * from schema.vendor");
		     return writeResultSet(resultSet);
	      
		    } catch (Exception e) {
		      throw e;
		    } 
	  }

	private String writeResultSet(ResultSet resultSet2)throws SQLException  {
		// TODO Auto-generated method stub
		 StringBuilder st=new StringBuilder();
		while (resultSet2.next()) {
		 	 vendor v=new vendor();
		      v.vendorname = resultSet2.getString("vendor_name");
		      v.sensorno = resultSet2.getInt("sensor_no");
		      v.email=resultSet2.getString("vendor_email");
		      v.contact=resultSet2.getLong("vendor_contact");
		      System.out.println("Vendorname="+v.vendorname);
		      varray.add(v);
		    }	
		System.out.println(varray);
	   Gson gson = new Gson();
       String json = gson.toJson(varray);
		System.out.println(json);
		String s=st.toString();
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sensor")
	public String readSensorDataBase() throws Exception {
		
	    try {
	      
	      Class.forName("com.mysql.jdbc.Driver").newInstance();  
	      Connection con=DriverManager.getConnection(DB_URL,user,passwd);  
	      statement = con.createStatement();
	      resultSet = statement.executeQuery("select * from schema.sensor");
		     return writeResultSetSensor(resultSet);
	      
		    } catch (Exception e) {
		      throw e;
		    } 
	  }

	private String writeResultSetSensor(ResultSet resultSet)throws SQLException  {
		// TODO Auto-generated method stub
		 StringBuilder st=new StringBuilder();
		while (resultSet.next()) {
		 	 sensor s=new sensor();
		      s.sensorname = resultSet.getString("sensor_name");
		      s.sensorid = resultSet.getInt("sensor_id");
		      s.sensorlocation=resultSet.getString("sensor_location");
		      s.sensortype=resultSet.getString("sensor_type");
		      s.sensorno=resultSet.getInt("sensor_no");
		      s.sensorused=resultSet.getBoolean("sensor_used");
		      System.out.println("Sensor Name="+s.sensorname);
		      sensorarray.add(s);
		    }	
		System.out.println(sensorarray);
	   Gson gson = new Gson();
       String json = gson.toJson(sensorarray);
		System.out.println(json);
		String s=st.toString();
		return json;
	}
  
    
}    