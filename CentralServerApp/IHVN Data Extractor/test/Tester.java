
import ihvn.data.extractor.model.dao.Database;
import ihvn.data.extractor.model.dao.VisitDAO;

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
        VisitDAO dao = new VisitDAO();
        Database.initConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3316/openmrs?useCursorFetch=true\"", "openmrs", "42Gg5Kj4a^Tw");
        System.out.println(dao.tableExists("person"));
    }
}
