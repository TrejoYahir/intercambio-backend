/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import database.Queries;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libraries.Utils;
import org.json.simple.JSONObject;
import models.Exchange;

/**
 *
 * @author Yahir
 */
public class EditExchange extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        Exchange existingExchange;
        JSONObject resp = new JSONObject();
        String data = Utils.getBody(request);
        Gson gson = new Gson();
        System.out.println("data: " + data);
        Exchange exchange = gson.fromJson(data, Exchange.class);
        
        try (PrintWriter out = response.getWriter()) {
            
            boolean r = Queries.editExchange(exchange);
            if(r) {
                String e = gson.toJson(exchange);
                System.out.println("Exchange created " + e);
                resp.put("success", true);
                resp.put("message", "Intercambio editado");
            } else {
                System.out.println("Error creating exchange");
                resp.put("success", false);
                resp.put("message", "Ocurrió un error al editar el intercambio");
            } 
            out.print(resp.toString()); 
        }
    }

}
