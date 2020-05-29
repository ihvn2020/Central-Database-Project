/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author lordmaul
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientBiometricType", propOrder = { "biometricInfoId", "patientId", "template", "imageWidth", "imageHeight", "imageDPI",
    "imageQuality", "fingerPosition", "serialNumber",  "model", "manufacturer", "creator", "dateCreated"})
public class PatientBiometricType {
    
    @XmlElement(name = "biometric_info_id")
    private int biometricInfoId;
    
    @XmlElement(name = "patient_id")
    private int patientId;
    
    @XmlElement(name = "template")
    private String template;
    
    @XmlElement(name = "image_width")
    private int imageWidth;
    
    @XmlElement(name = "image_height")
    private int imageHeight;
    
    @XmlElement(name = "image_dpi")
    private int imageDPI;
    
    @XmlElement(name = "image_quality")
    private int imageQuality;
    
    @XmlElement(name = "finger_position")
    private String fingerPosition;
    
    @XmlElement(name = "serial_number")
    private String serialNumber;
    
    @XmlElement(name = "model")
    private String model;
    
    @XmlElement(name = "manufacturer")
    private String manufacturer;
    
    @XmlElement(name = "creator")
    private int creator;
    
    @XmlElement(name = "date_created")
    private XMLGregorianCalendar dateCreated;

    /**
     * @return the biometricInfoId
     */
    public int getBiometricInfoId() {
        return biometricInfoId;
    }

    /**
     * @param biometricInfoId the biometricInfoId to set
     */
    public void setBiometricInfoId(int biometricInfoId) {
        this.biometricInfoId = biometricInfoId;
    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return the imageHeight
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * @param imageHeight the imageHeight to set
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * @return the imageDPI
     */
    public int getImageDPI() {
        return imageDPI;
    }

    /**
     * @param imageDPI the imageDPI to set
     */
    public void setImageDPI(int imageDPI) {
        this.imageDPI = imageDPI;
    }

    /**
     * @return the imageQuality
     */
    public int getImageQuality() {
        return imageQuality;
    }

    /**
     * @param imageQuality the imageQuality to set
     */
    public void setImageQuality(int imageQuality) {
        this.imageQuality = imageQuality;
    }

    /**
     * @return the fingerPosition
     */
    public String getFingerPosition() {
        return fingerPosition;
    }

    /**
     * @param fingerPosition the fingerPosition to set
     */
    public void setFingerPosition(String fingerPosition) {
        this.fingerPosition = fingerPosition;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the creator
     */
    public int getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(int creator) {
        this.creator = creator;
    }

    /**
     * @return the dateCreated
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(XMLGregorianCalendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the imageWidth
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * @param imageWidth the imageWidth to set
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }
    
    
    
}
