/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aperea.clscustomerforms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author aperea
 */
@ManagedBean(name = "viewAdminRegistration")
@ViewScoped
public class ViewAdminRegistration extends ListDataModel<Requestor> implements Serializable, SelectableDataModel<Requestor> {

    private List<Requestor> requestors;
    
    private Requestor requestor, selectedRequestor;
    

    private RegistrationDAO registrationDAO = new RegistrationDAO();

    public ViewAdminRegistration() {
        requestor = new Requestor();
        requestors = new ArrayList<>();
        requestors = registrationDAO.getAll();
        
    }
    
    public void search(){
                this.requestor=selectedRequestor;
                List<Student>tempList =  requestor.getRegistration().getStudents();
                for ( Student student : tempList) {
    System.out.println("Derp: "+student.getFirstName());
}
    }
    
    //getters n setters
    public Requestor getRequestor() {
        return requestor;
    }

    public void setRequestor(Requestor requestor) {
        this.requestor = requestor;
    }

    public Requestor getSelectedRequestor() {
        return selectedRequestor;
    }

    public void setSelectedRequestor(Requestor selectedRequestor) {
        this.selectedRequestor = selectedRequestor;
    }

    public List<Requestor> getRequestors() {
        return requestors;
    }

    @Override
    public Object getRowKey(Requestor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Requestor getRowData(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
}
