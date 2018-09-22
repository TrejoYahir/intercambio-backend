/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.Conexion;
import database.Queries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libraries.Utils;
import models.User;

/**
 *
 * @author Yahir
 */
public class Signup extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        ArrayList<User> existingUsers;
        JsonObject resp = new JsonObject();
        String data = Utils.getBody(request);
        Gson gson = new Gson();
        
        System.out.println("data: " + data);
        
        User user = gson.fromJson(data, User.class);
        existingUsers = Queries.userExists(user);
        System.out.println("ExistingUsers size " + existingUsers.size());

        try (PrintWriter out = response.getWriter();) {
            
            if(existingUsers.size() > 0) {
                resp.addProperty("success", false);
                resp.addProperty("message", "Ya existe un usuario con ese alias o email");
                resp.addProperty("alias", existingUsers.get(0).alias);
                resp.addProperty("email", existingUsers.get(0).email);
                System.out.println("Response: " + resp);
                System.out.println("Existing user: " + existingUsers.get(0).alias);
            } else {
                int id = Queries.createUser(user);
                if(id != -1) {
                    user.pass = null;
                    user.id = id;
                    String u = gson.toJson(user);
                    System.out.println("User created");
                    resp.addProperty("success", true);
                    resp.addProperty("message", "Usuario creado");
                    resp.addProperty("user", u);
                } else {
                    System.out.println("Error creating user");
                    resp.addProperty("success", false);
                    resp.addProperty("message", "Ocurrió un error al crear el usuario");
                } 
            }
            out.print(resp.toString()); 
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
