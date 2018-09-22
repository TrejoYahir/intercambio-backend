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
public class Login extends HttpServlet {

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
        String requestData = Utils.getBody(request);
        Gson gson = new Gson();
        
        System.out.println("data: " + requestData);
        
        User data = gson.fromJson(requestData, User.class);
        User user = Queries.searchUserByLogin(data);

        try (PrintWriter out = response.getWriter();) {
            
            if(user.id != 0) {
                String u = gson.toJson(user);
                resp.addProperty("success", true);
                resp.addProperty("message", "Sesión iniciada");
                resp.addProperty("user", u);
                System.out.println("Response: " + resp);
            } else {
                System.out.println("Error creating user");
                resp.addProperty("success", false);
                resp.addProperty("message", "Usuario o contraseña incorrectos.");
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
    }// </editor-fold>

}
