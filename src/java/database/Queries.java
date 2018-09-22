/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import models.User;
import models.Users;

/**
 *
 * @author Yahir
 */
public class Queries {
    
    public static synchronized ArrayList<User> searchUser(User user){
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
    
        public static synchronized User searchUserByLogin(User user){
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
    
}
