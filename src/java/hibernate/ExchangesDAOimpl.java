/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yahir
 */
public class ExchangesDAOimpl {
    

    Session session = null;

    public ExchangesDAOimpl() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List<Users> getFriendList(int id) {
         List<Users> friendList = null;
         try {
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(
                "CALL getFriendList(:id)")
                .addEntity(Users.class)
                .setParameter("id", id);
            friendList = (List<Users>) query.list();
            tx.commit();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return friendList;
    }
    
    public List<Exchanges> getExchanges(int id) {
         List<Exchanges> exchangeList = null;
         try {
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(
                "FROM Exchanges WHERE id = :id")
                .addEntity(Users.class)
                .setParameter("id", id);
            exchangeList = (List<Exchanges>) query.list();
            tx.commit();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return exchangeList;
    }

    public Users searchUser(String name) {
         Users user = null;
         try {
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(
                "CALL getUser(:name)")
                .addEntity(Users.class)
                .setParameter("firstName", name);
            user = (Users) query.uniqueResult();
            tx.commit();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return user;
    }
    
}
