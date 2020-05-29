/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import ihvn.data.extractor.model.xml.PatientBiometricType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author rsuth
 */
public class PatientBiometricDAO {
    
    
    public List<PatientBiometricType> getPatientBiometric(int patientId) {
        int id = patientId;
        //Connection connection;
        Connection con = null;
        Statement statement = null;
        ResultSet result = null;
        List<PatientBiometricType> allPatientBiometric = new ArrayList<>();
        try {

            con = Database.connectionPool.getConnection();
            //  DBConnection connResult = Utils.getNmrsConnectionDetails();
            //connection = DriverManager.getConnection(connResult.getUrl(), connResult.getUsername(), connResult.getPassword());
            statement = con.createStatement();//Database.getConnection().createStatement();
            String sqlStatement = ("SELECT patient_id, template, biometricinfo_id, imageHeight, imageWidth, imageDPI, imageQuality, serialNumber, model, manufacturer,  fingerPosition, date_created,creator FROM biometricinfo WHERE patient_Id = " + id);
            result = statement.executeQuery(sqlStatement);

            XMLGregorianCalendar dataCaptured = null;
            Integer creator = null;
            while (result.next()) {
                PatientBiometricType biometric = new PatientBiometricType();
                biometric.setBiometricInfoId(result.getInt("biometricinfo_id"));
                biometric.setCreator(result.getInt("creator"));
                biometric.setDateCreated(Misc.getXMLdateTime(result.getDate("date_created")));
                biometric.setFingerPosition(result.getString("fingerPosition"));
                biometric.setImageDPI(result.getInt("imageDPI"));
                biometric.setImageHeight(result.getInt("imageHeight"));
                biometric.setImageQuality(result.getInt("imageQuality"));
                biometric.setImageWidth(result.getInt("imageWidth"));
                biometric.setManufacturer(result.getString("manufacturer"));
                biometric.setModel(result.getString("manufacturer"));
                biometric.setPatientId(patientId);
                biometric.setSerialNumber(result.getString("serialNumber"));
                biometric.setTemplate(result.getString("template"));
                allPatientBiometric.add(biometric);

            }
        }
        catch (SQLException e) {
                e.printStackTrace();
                //LoggerUtils.write(NDRMainDictionary.class.getName(), e.getMessage(), LogFormat.FATAL, LoggerUtils.LogLevel.live.live);
        }
        catch (Exception e) {
                e.printStackTrace();
        }
        finally {
                //cleanUp(resultSet, pStatement);

            try {
                    result.close();
                    statement.close();
                    Database.connectionPool.free(con);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                    //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return allPatientBiometric;
    }
}
