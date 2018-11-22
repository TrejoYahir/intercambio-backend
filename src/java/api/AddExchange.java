/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.Queries;
import email.EmailHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libraries.Utils;
import models.Exchange;
import models.User;

/**
 *
 * @author Yahir
 */
public class AddExchange extends HttpServlet {

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
        
        Exchange existingExchange;
        JsonObject resp = new JsonObject();
        String data = Utils.getBody(request);
        Gson gson = new Gson();
        System.out.println("data: " + data);
        Exchange exchange = gson.fromJson(data, Exchange.class);
 
        do {
            exchange.accessCode = Utils.generateCode();
            existingExchange = Queries.searchExchange(exchange.accessCode);
            System.out.println("Existing exchange code " + existingExchange.exchangeName);
        } while(existingExchange.accessCode != null);
        
        try (PrintWriter out = response.getWriter()) {
            
            int id = Queries.createExchange(exchange);
            if(id != -1) {
                exchange.id = id;
                exchange.participantList = Queries.getExchangeParticipants(id);
                String e = gson.toJson(exchange);
                System.out.println("Exchange created " + e);
                resp.addProperty("success", true);
                resp.addProperty("message", "Intercambio añadido");
                resp.addProperty("exchange", e);
            } else {
                System.out.println("Error creating exchange");
                resp.addProperty("success", false);
                resp.addProperty("message", "Ocurrió un error al crear el intercambio");
            } 
            exchange.participantList.forEach((u) -> {
                User creator  = Queries.searchUserById(exchange.idCreator);
                String body = Utils.createInviteBody(
                        exchange.exchangeName,
                        u,
                        creator.firstName + " " + creator.lastName,
                        exchange.accessCode,
                        exchange.limitDate);
                String subject = u.firstName + ", has sido invitado a un intercambio";
                Thread t = new Thread(new EmailHelper(u.email, subject, body));
                t.start();
            });
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
