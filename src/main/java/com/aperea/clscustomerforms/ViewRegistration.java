/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aperea.clscustomerforms;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
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
    
    private List<SelectItem>trainingSiteSelectItems;

    @PostConstruct
    public void init() {
        requestor = new Requestor();
        registrationDAO = new RegistrationDAO();
        this.requestor.getRegistration().setRegistrationDate(getCurrentDate());
        
        //training site selectitems
       
        trainingSiteSelectItems = new ArrayList<>();
        trainingSiteSelectItems.add(new SelectItem("Ciena Site"));
                trainingSiteSelectItems.add(new SelectItem("Customer Site"));

    }

    public void submit() {
        
        registrationDAO.Add(requestor);

        sendEmailToAdmin();
        
        sendEmailToUser(); 
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

    public List<SelectItem> getTrainingSiteSelectItems() {
        return trainingSiteSelectItems;
    }

    public void setTrainingSiteSelectItems(List<SelectItem> trainingSiteSelectItems) {
        this.trainingSiteSelectItems = trainingSiteSelectItems;
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

    private void sendEmailToAdmin() {

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
                    InternetAddress.parse("aperea@ciena.com"));
            message.setSubject(this.getRequestor().getRegistration().getCourseName() + " Course Registration on " + this.getRequestor().getRegistration().getCourseDate());
             message.setText("This is an automated email. Please note that you are not yet officially registered with training until final approval by Ciena."
                    + "\n\n Ciena Course Dedicated Registration information follows:"
                    + "\n\n Request Date: " + this.getRequestor().getRegistration().getRegistrationDate()
                    + "\n\n Request Name (First, Last): " + this.getRequestor().getFirstName() + " " + this.getRequestor().getLastName()
                    + "\n\n Company Name: " + this.getRequestor().getCompany().getCompanyName()
                    + "\n\n Requestor Email: " + this.getRequestor().getEmail()
                    + "\n\n Company Street1: " + this.getRequestor().getCompany().getCompanyAddress().getStreet1()
                    + "\n\n Company Street2: " + this.getRequestor().getCompany().getCompanyAddress().getStreet2()
                    + "\n\n Company City: " + this.getRequestor().getCompany().getCompanyAddress().getCity()
                    + "\n\n Company State: " + this.getRequestor().getCompany().getCompanyAddress().getMyState()
                    + "\n\n Company ZIP: " + this.getRequestor().getCompany().getCompanyAddress().getZip()
                    + "\n\n Company Country: " + this.getRequestor().getCompany().getCompanyAddress().getCountry()
                    + "\n\n Company Phone: " + this.getRequestor().getCompany().getCompanyPhone()
                    + "\n\n\n Course Information: "
                    + "\n\n Course Number: " + this.getRequestor().getRegistration().getCourseNumber()
                    + "\n\n Course Name: " + this.getRequestor().getRegistration().getCourseName()
                    + "\n\n Course Date: " + this.getRequestor().getRegistration().getCourseDate()
                    + "\n\n PO Number: " + this.getRequestor().getRegistration().getPurchaseOrderNumber()
                    + "\n\n Using Training Credits: " + String.valueOf(this.getRequestor().getRegistration().isIsTrainingCredits())
                    + "\n\n Non-Rev Number: " + this.getRequestor().getRegistration().getNonRevNumber()
                    + "\n\n Onsite Information: "
                    + "\n\n\n Site Address: "
                    + "\n\n\n Training Site: " + this.getRequestor().getRegistration().getTrainingSite()
                    + "\n\n Site Street1: " + this.getRequestor().getRegistration().getSiteAddress().getStreet1()
                    + "\n\n Site Street2: " + this.getRequestor().getRegistration().getSiteAddress().getStreet2()
                    + "\n\n Site City: " + this.getRequestor().getRegistration().getSiteAddress().getCity()
                    + "\n\n Site State: " + this.getRequestor().getRegistration().getSiteAddress().getMyState()
                    + "\n\n Site ZIP: " + this.getRequestor().getRegistration().getSiteAddress().getZip()
                    + "\n\n Site Country: " + this.getRequestor().getRegistration().getSiteAddress().getCountry()
                    + "\n\n\n Shipping Address: "
                    + "\n\n Shipping Street1: " + this.getRequestor().getRegistration().getShippingAddress().getStreet1()
                    + "\n\n Shipping Street2: " + this.getRequestor().getRegistration().getShippingAddress().getStreet2()
                    + "\n\n Shipping City: " + this.getRequestor().getRegistration().getShippingAddress().getCity()
                    + "\n\n Shipping State: " + this.getRequestor().getRegistration().getShippingAddress().getMyState()
                    + "\n\n Shipping ZIP: " + this.getRequestor().getRegistration().getShippingAddress().getZip()
                    + "\n\n Shipping Country: " + this.getRequestor().getRegistration().getShippingAddress().getCountry()
                    + "\n\n\n Point of Contact Information: "
                    + "\n\n POC First Name: " + this.getRequestor().getRegistration().getPocFirstName()
                    + "\n\n POC Last Name: " + this.getRequestor().getRegistration().getPocLastName()
                    + "\n\n POC Email Address: " + this.getRequestor().getRegistration().getPocEmail()
                    + "\n\n POC Phone Number: " + this.getRequestor().getRegistration().getPocPhoneNumber()
                    + "\n\n\n Equipment Information: "
                    + "\n\n Projector: " + String.valueOf(this.getRequestor().getRegistration().isHasProjector())
                    + "\n\n Whiteboards: " + String.valueOf(this.getRequestor().getRegistration().isHasWhiteBoards())
                    + "\n\n Laptops with Admin for IP: " + String.valueOf(this.getRequestor().getRegistration().isHasLaptops())
                    + "\n\n Additional Notes: " + this.getRequestor().getRegistration().getAdditionalNotes()
                    + "\n\n\n Student List: " + studentListString()
                    + "\n\n End");

            Transport.send(message);

            System.out.println("Admin Email Sent");

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

    private void sendEmailToUser() {

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
            message.setFrom(new InternetAddress("training_approval@ciena.com", "Ciena Learning Solutions"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.getRequestor().getEmail()));
            message.setSubject(this.getRequestor().getRegistration().getCourseName() + " Confirmation Course Registration on " + this.getRequestor().getRegistration().getCourseDate());
            message.setText("This is an automated email. Please note that you are not yet officially registered with training until final approval by Ciena."
                    + "\n\n Ciena Course Dedicated Registration information follows:"
                    + "\n\n Request Date: " + this.getRequestor().getRegistration().getRegistrationDate()
                    + "\n\n Request Name (First, Last): " + this.getRequestor().getFirstName() + " " + this.getRequestor().getLastName()
                    + "\n\n Company Name: " + this.getRequestor().getCompany().getCompanyName()
                    + "\n\n Requestor Email: " + this.getRequestor().getEmail()
                    + "\n\n Company Street1: " + this.getRequestor().getCompany().getCompanyAddress().getStreet1()
                    + "\n\n Company Street2: " + this.getRequestor().getCompany().getCompanyAddress().getStreet2()
                    + "\n\n Company City: " + this.getRequestor().getCompany().getCompanyAddress().getCity()
                    + "\n\n Company State: " + this.getRequestor().getCompany().getCompanyAddress().getMyState()
                    + "\n\n Company ZIP: " + this.getRequestor().getCompany().getCompanyAddress().getZip()
                    + "\n\n Company Country: " + this.getRequestor().getCompany().getCompanyAddress().getCountry()
                    + "\n\n Company Phone: " + this.getRequestor().getCompany().getCompanyPhone()
                    + "\n\n\n Course Information: "
                    + "\n\n Course Number: " + this.getRequestor().getRegistration().getCourseNumber()
                    + "\n\n Course Name: " + this.getRequestor().getRegistration().getCourseName()
                    + "\n\n Course Date: " + this.getRequestor().getRegistration().getCourseDate()
                    + "\n\n PO Number: " + this.getRequestor().getRegistration().getPurchaseOrderNumber()
                    + "\n\n Using Training Credits: " + String.valueOf(this.getRequestor().getRegistration().isIsTrainingCredits())
                    + "\n\n Non-Rev Number: " + this.getRequestor().getRegistration().getNonRevNumber()
                    + "\n\n Onsite Information: "
                    + "\n\n\n Site Address: "
                    + "\n\n\n Training Site: " + this.getRequestor().getRegistration().getTrainingSite()
                    + "\n\n Site Street1: " + this.getRequestor().getRegistration().getSiteAddress().getStreet1()
                    + "\n\n Site Street2: " + this.getRequestor().getRegistration().getSiteAddress().getStreet2()
                    + "\n\n Site City: " + this.getRequestor().getRegistration().getSiteAddress().getCity()
                    + "\n\n Site State: " + this.getRequestor().getRegistration().getSiteAddress().getMyState()
                    + "\n\n Site ZIP: " + this.getRequestor().getRegistration().getSiteAddress().getZip()
                    + "\n\n Site Country: " + this.getRequestor().getRegistration().getSiteAddress().getCountry()
                    + "\n\n\n Shipping Address: "
                    + "\n\n Shipping Street1: " + this.getRequestor().getRegistration().getShippingAddress().getStreet1()
                    + "\n\n Shipping Street2: " + this.getRequestor().getRegistration().getShippingAddress().getStreet2()
                    + "\n\n Shipping City: " + this.getRequestor().getRegistration().getShippingAddress().getCity()
                    + "\n\n Shipping State: " + this.getRequestor().getRegistration().getShippingAddress().getMyState()
                    + "\n\n Shipping ZIP: " + this.getRequestor().getRegistration().getShippingAddress().getZip()
                    + "\n\n Shipping Country: " + this.getRequestor().getRegistration().getShippingAddress().getCountry()
                    + "\n\n\n Point of Contact Information: "
                    + "\n\n POC First Name: " + this.getRequestor().getRegistration().getPocFirstName()
                    + "\n\n POC Last Name: " + this.getRequestor().getRegistration().getPocLastName()
                    + "\n\n POC Email Address: " + this.getRequestor().getRegistration().getPocEmail()
                    + "\n\n POC Phone Number: " + this.getRequestor().getRegistration().getPocPhoneNumber()
                    + "\n\n\n Equipment Information: "
                    + "\n\n Projector: " + String.valueOf(this.getRequestor().getRegistration().isHasProjector())
                    + "\n\n Whiteboards: " + String.valueOf(this.getRequestor().getRegistration().isHasWhiteBoards())
                    + "\n\n Laptops with Admin for IP: " + String.valueOf(this.getRequestor().getRegistration().isHasLaptops())
                    + "\n\n Additional Notes: " + this.getRequestor().getRegistration().getAdditionalNotes()
                    + "\n\n\n Student List: " + studentListString()
                    + "\n\n End");

            Transport.send(message);

            System.out.println("User Email Sent");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }
}
