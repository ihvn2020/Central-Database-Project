//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.06 at 02:19:42 AM WAT 
//


package ihvn.data.consumer.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PatientIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientIdentifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patient_identifier_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="patient_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="location_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="identifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identifier_type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="preferred" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date_created" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="date_changed" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="changed_by" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="voided" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="voided_by" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date_voided" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="patient_identifier_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patient_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datim_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientIdentifierType", propOrder = {
    "patientIdentifierId",
    "patientId",
    "locationId",
    "identifier",
    "identifierType",
    "preferred",
    "creator",
    "dateCreated",
    "dateChanged",
    "changedBy",
    "voided",
    "voidedBy",
    "dateVoided",
    "patientIdentifierUuid",
    "patientUuid",
    "datimId"
})
public class PatientIdentifierType {

    @XmlElement(name = "patient_identifier_id")
    protected int patientIdentifierId;
    @XmlElement(name = "patient_id")
    protected int patientId;
    @XmlElement(name = "location_id")
    protected int locationId;
    @XmlElement(required = true)
    protected String identifier;
    @XmlElement(name = "identifier_type")
    protected int identifierType;
    protected int preferred;
    protected int creator;
    @XmlElement(name = "date_created", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    @XmlElement(name = "date_changed", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateChanged;
    @XmlElement(name = "changed_by")
    protected int changedBy;
    protected int voided;
    @XmlElement(name = "voided_by")
    protected int voidedBy;
    @XmlElement(name = "date_voided", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateVoided;
    @XmlElement(name = "patient_identifier_uuid", required = true)
    protected String patientIdentifierUuid;
    @XmlElement(name = "patient_uuid", required = true)
    protected String patientUuid;
    @XmlElement(name = "datim_id", required = true)
    protected String datimId;

    /**
     * Gets the value of the patientIdentifierId property.
     * 
     */
    public int getPatientIdentifierId() {
        return patientIdentifierId;
    }

    /**
     * Sets the value of the patientIdentifierId property.
     * 
     */
    public void setPatientIdentifierId(int value) {
        this.patientIdentifierId = value;
    }

    /**
     * Gets the value of the patientId property.
     * 
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Sets the value of the patientId property.
     * 
     */
    public void setPatientId(int value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the locationId property.
     * 
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     */
    public void setLocationId(int value) {
        this.locationId = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the identifierType property.
     * 
     */
    public int getIdentifierType() {
        return identifierType;
    }

    /**
     * Sets the value of the identifierType property.
     * 
     */
    public void setIdentifierType(int value) {
        this.identifierType = value;
    }

    /**
     * Gets the value of the preferred property.
     * 
     */
    public int getPreferred() {
        return preferred;
    }

    /**
     * Sets the value of the preferred property.
     * 
     */
    public void setPreferred(int value) {
        this.preferred = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     */
    public int getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     */
    public void setCreator(int value) {
        this.creator = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the dateChanged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateChanged() {
        return dateChanged;
    }

    /**
     * Sets the value of the dateChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateChanged(XMLGregorianCalendar value) {
        this.dateChanged = value;
    }

    /**
     * Gets the value of the changedBy property.
     * 
     */
    public int getChangedBy() {
        return changedBy;
    }

    /**
     * Sets the value of the changedBy property.
     * 
     */
    public void setChangedBy(int value) {
        this.changedBy = value;
    }

    /**
     * Gets the value of the voided property.
     * 
     */
    public int getVoided() {
        return voided;
    }

    /**
     * Sets the value of the voided property.
     * 
     */
    public void setVoided(int value) {
        this.voided = value;
    }

    /**
     * Gets the value of the voidedBy property.
     * 
     */
    public int getVoidedBy() {
        return voidedBy;
    }

    /**
     * Sets the value of the voidedBy property.
     * 
     */
    public void setVoidedBy(int value) {
        this.voidedBy = value;
    }

    /**
     * Gets the value of the dateVoided property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateVoided() {
        return dateVoided;
    }

    /**
     * Sets the value of the dateVoided property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateVoided(XMLGregorianCalendar value) {
        this.dateVoided = value;
    }

    /**
     * Gets the value of the patientIdentifierUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientIdentifierUuid() {
        return patientIdentifierUuid;
    }

    /**
     * Sets the value of the patientIdentifierUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientIdentifierUuid(String value) {
        this.patientIdentifierUuid = value;
    }

    /**
     * Gets the value of the patientUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientUuid() {
        return patientUuid;
    }

    /**
     * Sets the value of the patientUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientUuid(String value) {
        this.patientUuid = value;
    }

    /**
     * Gets the value of the datimId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatimId() {
        return datimId;
    }

    /**
     * Sets the value of the datimId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatimId(String value) {
        this.datimId = value;
    }

}
