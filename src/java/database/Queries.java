/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Exchange;
import models.Friendship;
import models.Participant;
import models.User;

/**
 *
 * @author Yahir
 */
public class Queries {
    
    public static synchronized ArrayList<User> userExists(User user){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<User> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT * FROM Users WHERE email = ? OR alias = ?");
            cs.setString(1, user.email);
            cs.setString(2, user.alias);
            rs=cs.executeQuery();
            while(rs.next()) {
                User u = new User();
                u.firstName = rs.getString("firstName");
                u.lastName = rs.getString("lastName");
                u.alias = rs.getString("alias");
                u.email = rs.getString("email");
                u.id = rs.getInt("id");
                results.add(u);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized User searchUser(User user){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        User u = new User();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT * FROM Users WHERE email = ? AND pass = ?");
            cs.setString(1, user.email);
            cs.setString(2, user.pass);
            rs=cs.executeQuery();
            if(rs.next()) {
                u.firstName = rs.getString("firstName");
                u.lastName = rs.getString("lastName");
                u.alias = rs.getString("alias");
                u.email = rs.getString("email");
                u.id = rs.getInt("id");
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUserByLogin sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUserByLogin error" + e);
             e.printStackTrace();
        }
        return u;
    }
    
    public static synchronized User searchUserById(int id){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        User u = new User();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT * FROM Users WHERE id = ?");
            cs.setInt(1, id);
            rs=cs.executeQuery();
            if(rs.next()) {
                u.firstName = rs.getString("firstName");
                u.lastName = rs.getString("lastName");
                u.alias = rs.getString("alias");
                u.email = rs.getString("email");
                u.id = rs.getInt("id");
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUserByLogin sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUserByLogin error" + e);
             e.printStackTrace();
        }
        return u;
    }
    
    public static synchronized int createUser(User user) {
        Connection cn = null;
        CallableStatement cs = null;
        int r = -1;
        ResultSet rs = null;
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("INSERT INTO Users (email, firstName, lastName, alias, pass) values (?, ?, ?, ?, ?)");
            cs.setString(1, user.email);
            cs.setString(2, user.firstName);
            cs.setString(3, user.lastName);
            cs.setString(4, user.alias);
            cs.setString(5, user.pass);
            cs.executeUpdate();
            rs = cs.getGeneratedKeys();
            if(rs.next()){
                r=rs.getInt(1);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (SQLException e){
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             e.printStackTrace();
        }
        return r;
    }
    
    public static synchronized ArrayList<User> searchUsers(String keyword){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<User> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT id, firstName, lastName, email, alias FROM Users WHERE email LIKE ? OR alias LIKE ? OR firstName LIKE ? OR lastName LIKE ?");
            cs.setString(1, "%" + keyword + "%");
            cs.setString(2, "%" + keyword + "%");
            cs.setString(3, "%" + keyword + "%");
            cs.setString(4, "%" + keyword + "%");

            rs=cs.executeQuery();
            while(rs.next()) {
                User u = new User();
                u.firstName = rs.getString("firstName");
                u.lastName = rs.getString("lastName");
                u.alias = rs.getString("alias");
                u.email = rs.getString("email");
                u.id = rs.getInt("id");
                results.add(u);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized User saveFriendship(Friendship f){
        Connection cn = null;
        CallableStatement cs = null;
        Boolean result = false;
        System.out.println("user1 " + f.idUser1);
        System.out.println("user2 " + f.idUser2);
        User user = new User();
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("{call saveFriendship(?,?)}");
            cs.setInt(1, f.idUser1);
            cs.setInt(2, f.idUser2);
            result=cs.executeUpdate() == 1;
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            user = Queries.searchUserById(f.idUser2);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        return user;
    }
    
    public static synchronized ArrayList<User> getFriendList(int id){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<User> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("{call getFriendList(?)}");
            cs.setInt(1, id);

            rs=cs.executeQuery();
            while(rs.next()) {
                User u = new User();
                u.firstName = rs.getString("firstName");
                u.lastName = rs.getString("lastName");
                u.alias = rs.getString("alias");
                u.email = rs.getString("email");
                u.id = rs.getInt("id");
                results.add(u);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized int createExchange(Exchange exchange) {

        Connection cn = null;
        CallableStatement cs = null;
        int r = -1;
        ResultSet rs = null;
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("INSERT INTO Exchanges (exchangeName, exchangeDescription, exchangeDate, limitDate, maxAmount, accessCode, idCreator) values (?, ?, ?, ?, ?, ?, ?)");
            cs.setString(1, exchange.exchangeName);
            cs.setString(2, exchange.exchangeDescription);
            cs.setString(3, exchange.exchangeDate);
            cs.setString(4, exchange.limitDate);
            cs.setInt(5, exchange.maxAmount);
            cs.setString(6, exchange.accessCode);
            cs.setInt(7, exchange.idCreator);
            cs.executeUpdate();
            rs = cs.getGeneratedKeys();
            cn.commit();
            
            if(rs.next()){
                r=rs.getInt(1);
                Queries.insertExchangeThemes(r, exchange.giftThemes);
                System.out.println("themes inserted");
                Queries.insertExchangeParticipants(r, exchange.participants);
                System.out.println("participants inserted");
            }
            
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            
        } catch (SQLException e){
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             e.printStackTrace();
        }
        return r;
    }
    
    public static synchronized void insertExchangeThemes(int id, String [] themes) {

        Connection cn = null;
        CallableStatement cs = null;
        int r = -1;
        ResultSet rs = null;
        String sql = "INSERT into Themes (idExchange, themeName) values ";
        for (int i = 0; i<themes.length-1; i++) {
            sql += "(" + id  + ", '" + themes[i] + "'), ";
        }
        
        sql += "(" + id  + ", '" + themes[themes.length-1] + "');";
        
        System.out.println("insert themes sql " + sql);
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall(sql);
            cs.executeUpdate();
            cn.commit();
            cs.close();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (SQLException e){
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             e.printStackTrace();
        }
    }
    
    public static synchronized void insertExchangeParticipants(int id, int [] participants) {

        Connection cn = null;
        CallableStatement cs = null;
        int r = -1;
        ResultSet rs = null;
        String sql = "INSERT into ParticipantList (idExchange, idUser, acceptInvite, isInGroup) values ";
        for (int i = 0; i<participants.length-1; i++) {
            sql += "(" + id  + ", " + participants[i] + ", false, false),";
        }
        
        sql += "(" + id  + ", " + participants[participants.length-1] + ", false, false);";
        
        System.out.println("insert participants sql " + sql);
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs = cn.prepareCall(sql);
            cs.executeUpdate();
            cn.commit();
            cs.close();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (SQLException e){
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             e.printStackTrace();
        }
    }
        
    public static synchronized Exchange searchExchange(String code){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Exchange ex = new Exchange();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT * FROM Exchanges WHERE accessCode = ?");
            cs.setString(1, code);
            rs=cs.executeQuery();
            if(rs.next()) {
                ex.exchangeName = rs.getString("exchangeName");
                ex.exchangeDescription = rs.getString("exchangeDescription");
                ex.limitDate = rs.getString("limitDate");
                ex.exchangeDate = rs.getString("exchangeDate");
                ex.id = rs.getInt("id");
                ex.idCreator = rs.getInt("idCreator");
                ex.accessCode = rs.getString("accessCode");
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUserByLogin sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUserByLogin error" + e);
             e.printStackTrace();
        }
        return ex;
    }
    
    public static synchronized ArrayList<Exchange> getExchangeList(int id){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<Exchange> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT * FROM Exchanges WHERE idCreator = ?");
            cs.setInt(1, id);

            rs=cs.executeQuery();
            while(rs.next()) {
                Exchange e = new Exchange();
                e.exchangeName = rs.getString("exchangeName");
                e.exchangeDescription = rs.getString("exchangeDescription");
                e.limitDate = rs.getString("limitDate");
                e.exchangeDate = rs.getString("exchangeDate");
                e.id = rs.getInt("id");
                e.idCreator = rs.getInt("idCreator");
                e.accessCode = rs.getString("accessCode");
                e.participantList = Queries.getExchangeParticipants(e.id);
                e.giftThemesList = Queries.getExchangeThemes(e.id);
                results.add(e);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized ArrayList<Exchange> getInviteList(int id){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<Exchange> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("{call getExchangeInvited(?)}");
            cs.setInt(1, id);

            rs=cs.executeQuery();
            while(rs.next()) {
                Exchange e = new Exchange();
                e.exchangeName = rs.getString("exchangeName");
                e.exchangeDescription = rs.getString("exchangeDescription");
                e.limitDate = rs.getString("limitDate");
                e.exchangeDate = rs.getString("exchangeDate");
                e.id = rs.getInt("id");
                e.idCreator = rs.getInt("idCreator");
                e.accessCode = rs.getString("accessCode");
                e.participantList = Queries.getExchangeParticipants(e.id);
                e.giftThemesList = Queries.getExchangeThemes(e.id);
                results.add(e);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized ArrayList<Participant> getExchangeParticipants(int id){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<Participant> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("{call getExchangeInvites(?)}");
            cs.setInt(1, id);

            rs=cs.executeQuery();
            while(rs.next()) {
                Participant p = new Participant();
                p.firstName = rs.getString("firstName");
                p.lastName = rs.getString("lastName");
                p.alias = rs.getString("alias");
                p.email = rs.getString("email");
                p.id = rs.getInt("id");
                p.acceptInvite = rs.getBoolean("acceptInvite");
                p.isInGroup = rs.getBoolean("isInGroup");
                results.add(p);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized ArrayList<String> getExchangeThemes(int id){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<String> results = new ArrayList<>();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT themeName FROM  Themes WHERE idExchange=?");
            cs.setInt(1, id);
            int i = 0;
            rs=cs.executeQuery();
            while(rs.next()) {
                String theme = rs.getString("themeName");
                results.add(theme);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        System.out.println("results length " + results.size());
        return results;
    }
    
    public static synchronized Exchange getExchange(String code){
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Exchange ex = new Exchange();
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("SELECT * FROM Exchanges WHERE accessCode = ?");
            cs.setString(1, code);

            rs=cs.executeQuery();
            if(rs.next()) {
                ex.exchangeName = rs.getString("exchangeName");
                ex.exchangeDescription = rs.getString("exchangeDescription");
                ex.limitDate = rs.getString("limitDate");
                ex.exchangeDate = rs.getString("exchangeDate");
                ex.id = rs.getInt("id");
                ex.idCreator = rs.getInt("idCreator");
                ex.accessCode = rs.getString("accessCode");
                ex.participantList = Queries.getExchangeParticipants(ex.id);
                ex.giftThemesList = Queries.getExchangeThemes(ex.id);
            }
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
            Conexion.closeResultset(rs);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        return ex;
    }
    
    public static synchronized boolean changeParticipantStatus(Participant p, boolean status){
        Connection cn = null;
        CallableStatement cs = null;
        Boolean result = false;
        System.out.println("user " + p.id);
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("Update ParticipantList SET acceptInvite=?, isInGroup=? WHERE idUser=? AND idExchange=?");
            cs.setBoolean(1, status);
            cs.setBoolean(2, status);
            cs.setInt(3, p.id);
            cs.setInt(4, p.idExchange);
            result=cs.executeUpdate() == 1;
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (SQLException e){
            System.err.print("searchUser sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUser error" + e);
             e.printStackTrace();
        }
        return result;
    }
    
    public static synchronized boolean deleteExchange(int id){
        Connection cn = null;
        CallableStatement cs = null;
        boolean r = false;
        
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs=cn.prepareCall("delete FROM Exchanges WHERE id = ?");
            cs.setInt(1, id);
            r=cs.executeUpdate() == 1;
            cn.commit();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (SQLException e){
            System.err.print("searchUserByLogin sql error" + e);
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             System.err.print("searchUserByLogin error" + e);
             e.printStackTrace();
        }
        return r;
    }
    
    public static synchronized boolean insertParticipant(Participant p) {
        Connection cn = null;
        CallableStatement cs = null;
        boolean response = false;
        String sql = "INSERT into ParticipantList (idExchange, idUser, acceptInvite, isInGroup) values (?, ?, false, false)";
        try{
            cn=Conexion.getConexion();
            cn.setAutoCommit(false);
            cs = cn.prepareCall(sql);
            cs.setInt(1, p.idExchange);
            cs.setInt(2, p.id);
            response = cs.executeUpdate() == 1;
            cn.commit();
            cs.close();
            Conexion.closeStatement(cs);
            Conexion.closeConexion(cn);
        } catch (SQLException e){
            e.printStackTrace();
            Conexion.rollback(cn);
            Conexion.closeConexion(cn);
        } catch (Exception e){
             e.printStackTrace();
        }
        return response;
    }
}
