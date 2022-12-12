package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Authorities;
import com.laioffer.onlineorder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER"); // 设定权限用的

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback(); // rollback 把执行成功（save的那部分）的撤销掉
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;
        try {session = sessionFactory.openSession();
            customer = session.get(Customer.class, email); // 只读不用开transaction
        } catch (Exception ex) {
            ex.printStackTrace(); // 打印失败的原因
        }finally{
            if(session != null) session.close();
        }
        return customer;
    }
}
