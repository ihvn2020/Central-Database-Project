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
 * @author rsuth
 */
public class FacilityDAO extends MasterDAO {
    
    
    public static String getGlobalProperty(String propertyName) {
        String query = "SELECT global_property.* FROM global_property where global_property.property= '" + propertyName
                + "' LIMIT 1";

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
                con = Database.connectionPool.getConnection();
                stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                //stmt = Database.conn.createStatement();
                //stmt.setString(1, propertyName);
                rs = stmt.executeQuery(query);
                String value = "";
                if (rs.next()) {
                        value = rs.getString("property_value");
                }

                //rs.close();
                //stmt.close();
                return value;
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                return "";
        }
        finally {
                try {
                        if (rs != null && stmt != null) {
                                rs.close();
                                stmt.close();
                                Database.connectionPool.free(con);
                        }

                }
                catch (SQLException ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        //return "";
    }
}
