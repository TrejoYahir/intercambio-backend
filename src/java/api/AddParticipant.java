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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libraries.Utils;
import models.Participant;

/**
 *
 * @author Yahir
 */
public class AddParticipant extends HttpServlet {

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
        
        Gson gson = new Gson();
        JsonObject resp = new JsonObject();
        String requestData = Utils.getBody(request);
        boolean result;
        
        System.out.println("data: " + requestData);
        
        Participant data = gson.fromJson(requestData, Participant.class);
        result = Queries.insertParticipant(data);

        try (PrintWriter out = response.getWriter();) {
            if(result) {
                resp.addProperty("success", true);
                resp.addProperty("message", "Añadido al intercambio");
            } else {
                resp.addProperty("success", false);
                resp.addProperty("message", "Error al añadir al intercambio");
            }
            out.print(resp); 
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
