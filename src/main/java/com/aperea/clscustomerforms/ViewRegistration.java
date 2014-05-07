/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aperea.clscustomerforms;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Armando
 */
@ManagedBean(name = "viewRegistration")
@ViewScoped
public class ViewRegistration implements Serializable {

    private Requestor requestor = new Requestor();

    private Student newStudent = new Student();

    private RegistrationDAO registrationDAO = new RegistrationDAO();

    public ViewRegistration() {
        requestor = new Requestor();
        registrationDAO = new RegistrationDAO();
        this.requestor.getRegistration().setRegistrationDate(getCurrentDate());
    }

    public void submit() {

        registrationDAO.Add(requestor);

        sendOutEmail();
    }

    public void addStudent() {

        requestor.getRegistration().getStudents().add(this.newStudent);
        this.newStudent = new Student();
    }

    public Requestor getRequestor() {
        return requestor;
    }

    public void setRequestor(Requestor requestor) {
        this.requestor = requestor;
    }

    private Date getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    //wizard stuff
    private boolean skip;

    private static Logger logger = Logger.getLogger(ViewRegistration.class.getName());

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    //student list edit row methods
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Student Edited", ((Student) event.getObject()).getLastName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Student Editing Cancelled", ((Student) event.getObject()).getLastName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void sendOutEmail() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("techtrng.course.reg@gmail.com", "dmewualfcajjfxqd");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            // message.setFrom(new InternetAddress("techtrng.course.reg@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("techtng@ciena.com"));
            message.setSubject(this.getRequestor().getRegistration().getCourseName() + " Course Registration on " + this.getRequestor().getRegistration().getCourseDate());
            message.setText("This is an automated email."
                    + "\n\n Ciena Course Dedicated Registration information follows:"
                    + "\n\n Request Date: " + this.getRequestor().getRegistration().getRegistrationDate()
                    + "\n Request Name (First, Last): " + this.getRequestor().getFirstName() + " " + this.getRequestor().getLastName()
                    + "\n Company Name: " + this.getRequestor().getCompany().getCompanyName()
                    + "\n Requestor Email: " + this.getRequestor().getEmail()
                    + "\n Company Street1: " + this.getRequestor().getCompany().getCompanyAddress().getStreet1()
                    + "\n Company Street2: " + this.getRequestor().getCompany().getCompanyAddress().getStreet2()
                    + "\n Company City: " + this.getRequestor().getCompany().getCompanyAddress().getCity()
                    + "\n Company State: " + this.getRequestor().getCompany().getCompanyAddress().getMyState()
                    + "\n Company ZIP: " + this.getRequestor().getCompany().getCompanyAddress().getZip()
                    + "\n Company Country: " + this.getRequestor().getCompany().getCompanyAddress().getCountry()
                    + "\n Company Phone: " + this.getRequestor().getCompany().getCompanyPhone()
                    + "\n Ciena Relation: " + this.getRequestor().getRegistration().getCienaRelation()
                    + "\n\n Course Information: "
                    + "\n Course Type: " + this.getRequestor().getRegistration().getCourseType()
                    + "\n Course Number: " + this.getRequestor().getRegistration().getCourseNumber()
                    + "\n Course Name: " + this.getRequestor().getRegistration().getCourseName()
                    + "\n Course Date: " + this.getRequestor().getRegistration().getCourseDate()
                    + "\n PO Number: " + this.getRequestor().getRegistration().getPurchaseOrderNumber()
                    + "\n Using Training Credits: " + String.valueOf(this.getRequestor().getRegistration().isIsTrainingCredits())
                    + "\n Non-Rev Number: " + this.getRequestor().getRegistration().getNonRevNumber()
                    + "\n Onsite Information: "
                    + "\n\n Site Address: "
                    + "\n Site Street1: " + this.getRequestor().getRegistration().getSiteAddress().getStreet1()
                    + "\n Site Street2: " + this.getRequestor().getRegistration().getSiteAddress().getStreet2()
                    + "\n Site City: " + this.getRequestor().getRegistration().getSiteAddress().getCity()
                    + "\n Site State: " + this.getRequestor().getRegistration().getSiteAddress().getMyState()
                    + "\n Site ZIP: " + this.getRequestor().getRegistration().getSiteAddress().getZip()
                    + "\n Site Country: " + this.getRequestor().getRegistration().getSiteAddress().getCountry()
                    + "\n\n Shipping Address: "
                    + "\n Shipping Street1: " + this.getRequestor().getRegistration().getShippingAddress().getStreet1()
                    + "\n Shipping Street2: " + this.getRequestor().getRegistration().getShippingAddress().getStreet2()
                    + "\n Shipping City: " + this.getRequestor().getRegistration().getShippingAddress().getCity()
                    + "\n Shipping State: " + this.getRequestor().getRegistration().getShippingAddress().getMyState()
                    + "\n Shipping ZIP: " + this.getRequestor().getRegistration().getShippingAddress().getZip()
                    + "\n Shipping Country: " + this.getRequestor().getRegistration().getShippingAddress().getCountry()
                    + "\n\n Point of Contact Information: "
                    + "\n POC First Name: " + this.getRequestor().getRegistration().getPocFirstName()
                    + "\n POC Last Name: " + this.getRequestor().getRegistration().getPocLastName()
                    + "\n POC Email Address: " + this.getRequestor().getRegistration().getPocEmail()
                    + "\n POC Phone Number: " + this.getRequestor().getRegistration().getPocPhoneNumber()
                    + "\n\n Equipment Information: "
                    + "\n Projector: " + String.valueOf(this.getRequestor().getRegistration().isHasProjector())
                    + "\n Whiteboards: " + String.valueOf(this.getRequestor().getRegistration().isHasWhiteBoards())
                    + "\n Laptops with Admin for IP: " + String.valueOf(this.getRequestor().getRegistration().isHasLaptops())
                    + "\n Additional Notes: " + this.getRequestor().getRegistration().getAdditionalNotes()
                    + "\n\n Student List: " + studentListString()
                    + "\n\n End");

            Transport.send(message);

            System.out.println("Email Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String studentListString() {
        String returnString = "";

        for (Student student : this.getRequestor().getRegistration().getStudents()) {
            returnString += "\n\nFirst Name: " + student.getFirstName()
                    + "\nLast Name: " + student.getLastName()
                    + "\nEmail: " + student.getEmail()
                    + "\nPhone Number: " + student.getPhoneNumber() + "\t";
        }

        return returnString;
    }
}
