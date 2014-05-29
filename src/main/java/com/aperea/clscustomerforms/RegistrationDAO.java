/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aperea.clscustomerforms;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 *
 * @author Armando
 */
public class RegistrationDAO implements Serializable {

    public Requestor searchByID(Long id) {
        Requestor returnRequestor;
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        returnRequestor = (Requestor) session.get(Requestor.class, id);
        session.getTransaction().commit();

        return returnRequestor;
    }

    public void Add(Requestor requestor) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(requestor);
        session.getTransaction().commit();
    }

    public List<Requestor> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(Requestor.class);
        session.beginTransaction();
        session.getTransaction().commit();

        return crit.list();
    }

    public List<Student> getStudents(Requestor requestor) {
        Requestor returnRequestor;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(Student.class);
        session.beginTransaction();
        session.getTransaction().commit();

        return crit.list();
    }

}
