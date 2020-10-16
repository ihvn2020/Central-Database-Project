/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

/**
 *
 * @author lordmaul
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.DriverManager;
import java.sql.*;
import java.util.logging.Level;

/**
 * @author lordmaul
 */
public class Database {
	
	//static DBConnection openmrsConn = Utils.getNmrsConnectionDetails();
	
	public static Connection conn = null;
	
	//public static Connection [] connPool = new Connection[10];
	//public static DBConnection openmrsConn2 = null;
	
	public static ConnectionPool connectionPool;
	
	/*public static void initConnection(DBConnection openmrsConn) {
            try {

                    if (openmrsConn != null) {
                            System.out.println("not null");
                            System.out.println(openmrsConn.getPassword());
                            connectionPool = new ConnectionPool("com.mysql.jdbc.Driver", openmrsConn.getUrl(),
                                    openmrsConn.getUsername(), openmrsConn.getPassword(), 5, 10, true);
                            //conn = DriverManager.getConnection(openmrsConn.getUrl() + "?useCursorFetch=true", openmrsConn.getUsername(),
                            // openmrsConn.getPassword());

                    } else {
                            Class.forName("com.mysql.jdbc.Driver");
                            connectionPool = new ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.167.142:3317/openmrs",
                                    "openmrs", "@37~maa5RyqR", 50, 100, true);

                            //conn = DriverManager.getConnection("jdbc:mysql://192.168.167.138:3317/openmrs?useCursorFetch=true",   "openmrs", "@37~maa5RyqR");
                    }

                    //conn = DriverManager.getConnection("jdbc:mysql://192.168.167.138:3317/openmrs?useCursorFetch=true", "openmrs",
                    //"@37~maa5RyqR");
                    //conn = DriverManager.getConnection("jdbc:mysql://192.168.43.230:3317/openmrs", "openmrs", "@37~maa5RyqR");
            }
            catch (Exception ex) {
                    ex.printStackTrace();
            }
		
	}*/
        
        public static void initConnection(String className, String connString, String username, String password) 
        {
            try {

                 
                    Class.forName(className);
                    connectionPool = new ConnectionPool(className, connString, username, password, 4, 50, true);

            }
            catch (Exception ex) {
                    ex.printStackTrace();
            }
		
	}
        
        public static boolean testConnection(String className, String connString, String username, String password)  {
		try {
		
                    Class.forName(className);
			Connection connection = DriverManager.getConnection(connString + "", username, password);
			// Connection connection = ds.getConnection();
			
			return true;
		}
		catch (Exception cnfe) {
                    return false;
		}
	}
	
	public static Connection getConnection() {
            /*if (Database.conn == null) {
                    Database.initConnection();
                    return Database.conn;
            } else {
                    return Database.conn;
            }*/
            return null;
		
	}
	
	public void closeConnection() {
            if (conn != null) {
                    try {
                            conn.close();
                            conn = null;
                    }
                    catch (SQLException ex) {
                            ex.printStackTrace();
                    }
            }
		
	}
	
}

