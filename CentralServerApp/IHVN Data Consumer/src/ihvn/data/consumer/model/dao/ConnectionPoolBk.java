/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lordmaul
 */

package ihvn.data.consumer.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

 

/**
 * @author lordmaul
 */
public class ConnectionPoolBk {
	
	private String driver, url, username, password;
	
	
        private static BasicDataSource dataSource;
        private Connection connection = null;
	
	private boolean connectionPending = false;
	
	public ConnectionPoolBk(String driver, String url, String username, String password, int initialConnections,
	    int maxConnections, boolean waitIfBusy) throws SQLException {
            
            
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		
	}
	
	public Connection getConnection() throws SQLException {
            /*if (dataSource == null)
            {
                BasicDataSource ds = new BasicDataSource();
                ds.setDriverClassName(this.driver);
                ds.setUrl(this.url);
                ds.setUsername(this.username);
                ds.setPassword(this.password);
                ds.setInitialSize(5);

                ds.setMinIdle(5);
                ds.setMaxIdle(100);
                ds.setMaxOpenPreparedStatements(100);

                dataSource = ds;
            }
            return dataSource.getConnection();*/
            if(connection == null || connection.isClosed())
                connection = DriverManager.getConnection(url + "", username, password);
            return connection;
	}
	
	/**
	 * You can't just make a new connection in the foreground // when none are available, since this
	 * can take several // seconds with a slow network connection. Instead, // start a thread that
	 * establishes a new connection, // then wait. You get woken up either when the new connection
	 * // is established or if someone finishes with an existing // connection.
	 */
	
}

