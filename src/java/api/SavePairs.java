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
public class SavePairs extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        Gson gson = new Gson();
        JsonObject resp = new JsonObject();
        String requestData = Utils.getBody(request);
        Boolean result;
        int idExchange = Integer.parseInt(request.getParameter("id"));
        
        System.out.println("data: " + requestData);
        
        int[][] pairs = gson.fromJson(requestData, int[][].class);
        result = Queries.savePairs(pairs, idExchange);
        System.out.println("Save finished " + result);

        try (PrintWriter out = response.getWriter();) {
            if(result) {
                resp.addProperty("success", true);
                resp.addProperty("message", "Parejas guardadas");
            } else {
                resp.addProperty("success", false);
                resp.addProperty("message", "Error al guardar parejas");
            }
            for(int[] pair: pairs) {
                User u1  = Queries.searchUserById(pair[0]);
                User u2  = Queries.searchUserById(pair[1]);
                String theme = Queries.searchTheme(u2.id, idExchange);
                Exchange ex = Queries.searchExchangeById(idExchange);
                String body = Utils.createPairedBody(u1, u2, theme, ex);
                String subject = u1.firstName + ", un intercambio está por realizarse.";
                Thread t = new Thread(new EmailHelper(u1.email, subject, body));
                t.start();
            }
            out.print(resp); 
        }
    }

}
