/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author The Bright
 */
public class MasterDAO {
    public void cleanUp(ResultSet rs, Statement stmt, Connection con) {
        try {
            rs.close();
            stmt.close();
            Database.connectionPool.free(con);
        } catch (Exception ex) {
            handleException(ex);
        }
    }
    public void handleException(Exception ex) {
        ex.printStackTrace();
    }
}
