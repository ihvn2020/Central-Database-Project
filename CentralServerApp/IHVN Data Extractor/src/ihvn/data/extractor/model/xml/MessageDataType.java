/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.xml;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author lordmaul
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageDataType", propOrder = { "Demographics", "Visits"})
public class MessageDataType {
    
    @XmlElement(name = "Demographics")
    private DemographicsType Demographics;
    
    @XmlElement(name = "Visits")
    private List<VisitType> Visits;

    /**
     * @return the Demographics
     */
    public DemographicsType getDemographics() {
        return Demographics;
    }

    /**
     * @param Demographics the Demographics to set
     */
    public void setDemographics(DemographicsType Demographics) {
        this.Demographics = Demographics;
    }

    /**
     * @return the Visits
     */
    public List<VisitType> getVisits() {
        return Visits;
    }

    /**
     * @param Visits the Visits to set
     */
    public void setVisits(List<VisitType> Visits) {
        this.Visits = Visits;
    }
    
    

   
    
    
}
