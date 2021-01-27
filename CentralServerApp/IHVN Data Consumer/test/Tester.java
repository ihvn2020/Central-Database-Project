
import ihvn.data.consumer.controller.MainController;
import ihvn.data.consumer.model.dao.Misc;
import ihvn.data.consumer.model.dao.PatientDAO;
import java.util.List;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lordmaul
 */
public class Tester {
    public static void main(String [] args)
    {
        MainController main = new MainController(null);
        main.initDb();
        List<JSONObject> data = PatientDAO.getAddress();
        for(int i=0; i<data.size(); i++)
        {
            try{
                //System.out.println(data.get(i).get("address1").toString());
                
                //System.out.println(address);
                if(data.get(i).get("address1") != null && data.get(i).get("patientUUID") != null){
                    String address = Misc.decrypt(data.get(i).get("address1").toString());
                    String patientUUID = data.get(i).get("patientUUID").toString();
                    PatientDAO.fixAddress(patientUUID, address);
                    System.out.println("helli");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
}
