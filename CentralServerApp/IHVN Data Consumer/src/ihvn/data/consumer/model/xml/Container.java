/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.xml;

/**
 *
 * @author lordmaul
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageHeader" type="{}MessageHeaderType"/>
 *         &lt;choice>
 *           &lt;element name="IndividualReport" type="{}IndividualReportType"/>
 *         &lt;/choice>
 *         &lt;element name="Validation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "messageHeader", "messageData" })
@XmlRootElement(name = "Container")
public class Container {
	
    @XmlElement(name = "MessageHeader", required = true)
    private MessageHeaderType messageHeader;

    @XmlElement(name = "MessageData")
    private MessageDataType messageData;

    /**
     * @return the messageHeader
     */
    public MessageHeaderType getMessageHeader() {
        return messageHeader;
    }

    /**
     * @param messageHeader the messageHeader to set
     */
    public void setMessageHeader(MessageHeaderType messageHeader) {
        this.messageHeader = messageHeader;
    }

    /**
     * @return the messageData
     */
    public MessageDataType getMessageData() {
        return messageData;
    }

    /**
     * @param messageData the messageData to set
     */
    public void setMessageData(MessageDataType messageData) {
        this.messageData = messageData;
    }
	
        
	
		
} 
