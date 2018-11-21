/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.Queries;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        
        User existingUser;
        JsonObject resp = new JsonObject();
        String data = Utils.getBody(request);
        Gson gson = new Gson();
        
        System.out.println("data: " + data);
        
        User usr = gson.fromJson(data, User.class);
        existingUser = Queries.searchUserByEmail(usr.email);

        try (PrintWriter out = response.getWriter();) {
            
            if(existingUser.id > 0) {
                System.out.println("Existing user " + existingUser.firstName);
                if(existingUser.external) {
                    System.out.println("External user exists");
                    usr.id = existingUser.id;
                    Queries.updateUser(usr);
                    User user = Queries.searchUserById(usr.id);
                    user.pass = null;
                    String u = gson.toJson(user);
                    System.out.println("User created");
                    resp.addProperty("success", true);
                    resp.addProperty("message", "Usuario creado");
                    resp.addProperty("user", u);
                } else {
                    System.out.println("User already exists");
                    resp.addProperty("success", false);
                    resp.addProperty("message", "Ya existe un usuario con ese alias o email");
                    resp.addProperty("alias", existingUser.alias);
                    resp.addProperty("email", existingUser.email);
                    System.out.println("Response: " + resp);
                    System.out.println("Existing user: " + existingUser.alias);
                }
            } else {
                System.out.println("User doesn't exists");
                int id = Queries.createUser(usr);
                if(id != -1) {
                    usr.pass = null;
                    usr.id = id;
                    String u = gson.toJson(usr);
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
