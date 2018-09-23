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
import models.Friendship;
import models.Participant;
import models.User;

/**
 *
 * @author Yahir
 */
public class JoinExchange extends HttpServlet {

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
        result = Queries.changeParticipantStatus(data, true);

        try (PrintWriter out = response.getWriter();) {
            if(result) {
                resp.addProperty("success", true);
                resp.addProperty("message", "Te has unido al intercambio");
            } else {
                resp.addProperty("success", false);
                resp.addProperty("message", "Error al unirse");
            }
            out.print(resp); 
        }
    }

}
