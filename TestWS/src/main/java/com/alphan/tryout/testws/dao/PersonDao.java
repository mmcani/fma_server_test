/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphan.tryout.testws.dao;

import com.alphan.tryout.testws.model.Person;
import com.alphan.tryout.testws.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Alphan
 */
public class PersonDao {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public Person getPersonById(int id) {
        Person person = null;
        Session session = null;
        
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            person = (Person) session.createQuery("from Person p where p.id = :ID").setParameter("ID", id).uniqueResult();
            session.getTransaction().commit();
        } catch(Exception ex) {
            
            if (session != null) {
                session.getTransaction().rollback();
            }
            
        } finally {
            
            if (session != null) {
                session.close();
            }
            
        }
        
        return person;
    }
    
    public List<Person> getAllPersons() {
        List<Person> persons = null;
        Session session = null;
        
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            persons = session.createQuery("from Person p").list();
            session.getTransaction().commit();
        } catch(Exception ex) {
            
            if (session != null) {
                session.getTransaction().rollback();
            }
            
        } finally {
            
            if (session != null) {
                session.close();
            }
            
        }
        
        return persons;
    }
}
