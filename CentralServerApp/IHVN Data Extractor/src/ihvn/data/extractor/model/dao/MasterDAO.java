/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author The Bright
 */
public class MasterDAO {
    
    public void cleanUp(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if(con!=null){
                Database.connectionPool.free(con);
            }
        } catch (SQLException ex) {
            handleException(ex);
        }
    }
    
    public void handleException(Exception ex) {
        ex.printStackTrace();
    }
}
