/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aperea.clscustomerforms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Armando
 */
@Entity
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    @OneToOne(mappedBy = "registration",cascade=CascadeType.ALL)
    private Requestor requestor;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Student> students;
    
    private boolean isTrainingSite;
    
    private String cienaRelation;
    
    private String courseType;
    
    private String courseNumber;
    
    private String courseName;
    
        @Temporal(javax.persistence.TemporalType.DATE)
    private Date courseDate;
    
            @Temporal(javax.persistence.TemporalType.DATE)
    private Date registrationDate;
        
    private String purchaseOrderNumber;
    
    private boolean isTrainingCredits;
    
    private String nonRevNumber;
    
    private boolean isAgree;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Address siteAddress;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Address shippingAddress;
    
    private String pocFirstName, pocLastName;
    
    private String pocPhoneNumber;
    
    private String pocEmail;
    
    private boolean hasProjector;
    
    private boolean hasWhiteBoards;
    
    private boolean hasLaptops;
    
    private String additionalNotes;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aperea.clscustomerforms.Registration[ id=" + id + " ]";
    }


    public List<Student> getStudents() {
        if (students==null){
            students= new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean isIsTrainingSite() {
        return isTrainingSite;
    }

    public void setIsTrainingSite(boolean isTrainingSite) {
        this.isTrainingSite = isTrainingSite;
    }

    public String getCienaRelation() {
        return cienaRelation;
    }

    public void setCienaRelation(String cienaRelation) {
        this.cienaRelation = cienaRelation;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public boolean isIsTrainingCredits() {
        return isTrainingCredits;
    }

    public void setIsTrainingCredits(boolean isTrainingCredits) {
        this.isTrainingCredits = isTrainingCredits;
    }

    public String getNonRevNumber() {
        return nonRevNumber;
    }

    public void setNonRevNumber(String nonRevNumber) {
        this.nonRevNumber = nonRevNumber;
    }

    public boolean isIsAgree() {
        return isAgree;
    }

    public void setIsAgree(boolean isAgree) {
        this.isAgree = isAgree;
    }

    public Address getSiteAddress() {
        if(siteAddress==null){
            siteAddress = new Address();
        }
        return siteAddress;
    }

    public void setSiteAddress(Address siteAddress) {
        this.siteAddress = siteAddress;
    }

    public Address getShippingAddress() {
        if (shippingAddress==null){
            shippingAddress = new Address();
        }
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPocFirstName() {
        return pocFirstName;
    }

    public void setPocFirstName(String pocFirstName) {
        this.pocFirstName = pocFirstName;
    }

    public String getPocLastName() {
        return pocLastName;
    }

    public void setPocLastName(String pocLastName) {
        this.pocLastName = pocLastName;
    }

    public String getPocPhoneNumber() {
        return pocPhoneNumber;
    }

    public void setPocPhoneNumber(String pocPhoneNumber) {
        this.pocPhoneNumber = pocPhoneNumber;
    }

    public String getPocEmail() {
        return pocEmail;
    }

    public void setPocEmail(String pocEmail) {
        this.pocEmail = pocEmail;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public boolean isHasWhiteBoards() {
        return hasWhiteBoards;
    }

    public void setHasWhiteBoards(boolean hasWhiteBoards) {
        this.hasWhiteBoards = hasWhiteBoards;
    }

    public boolean isHasLaptops() {
        return hasLaptops;
    }

    public void setHasLaptops(boolean hasLaptops) {
        this.hasLaptops = hasLaptops;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public Requestor getRequestor() {
        if(requestor==null){
            requestor=new Requestor();
        }
        return requestor;
    }

    public void setRequestor(Requestor requestor) {
        this.requestor = requestor;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

   
    
}
