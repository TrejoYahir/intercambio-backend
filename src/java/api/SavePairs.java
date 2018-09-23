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
            out.print(resp); 
        }
    }

}
