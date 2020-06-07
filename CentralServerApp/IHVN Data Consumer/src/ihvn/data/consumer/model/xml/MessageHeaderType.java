/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.xml;

/**
 *
 * @author rsuth
 */
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for MessageHeaderType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageStatusCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{}CodeType">
 *               &lt;enumeration value="INITIAL"/>
 *               &lt;enumeration value="UPDATED"/>
 *               &lt;enumeration value="REDACTED"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MessageCreationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="MessageSchemaVersion">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="99999"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MessageUniqueID" type="{}StringType"/>
 *         &lt;element name="MessageSendingOrganization" type="{}FacilityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeaderType", propOrder = { "messageStatusCode", "messageCreationDateTime", "messageSchemaVersion",
        "messageUniqueID", "FacilityDatimCode", "FacilityName" })
public class MessageHeaderType {
	
	@XmlElement(name = "MessageStatusCode", required = true)
	private String messageStatusCode;
	
	@XmlElement(name = "MessageCreationDateTime", required = true)
	@XmlSchemaType(name = "dateTime")
	private XMLGregorianCalendar messageCreationDateTime;
	
	@XmlElement(name = "MessageSchemaVersion", required = true)
	private BigDecimal messageSchemaVersion;
	
	@XmlElement(name = "MessageUniqueID", required = true)
	private String messageUniqueID;
	
	@XmlElement(name="FacilityDatimCode", required=true)
        private String FacilityDatimCode;
        
        @XmlElement(name="FacilityName", required=true)
        private String FacilityName;

        
    /**
     * @return the messageStatusCode
     */
    public String getMessageStatusCode() {
        return messageStatusCode;
    }

    /**
     * @param messageStatusCode the messageStatusCode to set
     */
    public void setMessageStatusCode(String messageStatusCode) {
        this.messageStatusCode = messageStatusCode;
    }

    /**
     * @return the messageCreationDateTime
     */
    public XMLGregorianCalendar getMessageCreationDateTime() {
        return messageCreationDateTime;
    }

    /**
     * @param messageCreationDateTime the messageCreationDateTime to set
     */
    public void setMessageCreationDateTime(XMLGregorianCalendar messageCreationDateTime) {
        this.messageCreationDateTime = messageCreationDateTime;
    }

    /**
     * @return the messageSchemaVersion
     */
    public BigDecimal getMessageSchemaVersion() {
        return messageSchemaVersion;
    }

    /**
     * @param messageSchemaVersion the messageSchemaVersion to set
     */
    public void setMessageSchemaVersion(BigDecimal messageSchemaVersion) {
        this.messageSchemaVersion = messageSchemaVersion;
    }

    /**
     * @return the messageUniqueID
     */
    public String getMessageUniqueID() {
        return messageUniqueID;
    }

    /**
     * @param messageUniqueID the messageUniqueID to set
     */
    public void setMessageUniqueID(String messageUniqueID) {
        this.messageUniqueID = messageUniqueID;
    }

    /**
     * @return the FacilityDatimCode
     */
    public String getFacilityDatimCode() {
        return FacilityDatimCode;
    }

    /**
     * @param FacilityDatimCode the FacilityDatimCode to set
     */
    public void setFacilityDatimCode(String FacilityDatimCode) {
        this.FacilityDatimCode = FacilityDatimCode;
    }

    /**
     * @return the FacilityName
     */
    public String getFacilityName() {
        return FacilityName;
    }

    /**
     * @param FacilityName the FacilityName to set
     */
    public void setFacilityName(String FacilityName) {
        this.FacilityName = FacilityName;
    }
        
        
	
	
	
}
