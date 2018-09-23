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
import models.User;

/**
 *
 * @author Yahir
 */
public class DeleteExchange extends HttpServlet {

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        boolean result;
        Gson gson = new Gson();
        int id = Integer.parseInt(request.getParameter("id"));
        result = Queries.deleteExchange(id);
        String userList;
        JsonObject resp = new JsonObject();

        try (PrintWriter out = response.getWriter();) {
            if(result) {
                resp.addProperty("success", true);
                resp.addProperty("message", "Intercambio eliminado");
            } else {
                resp.addProperty("success", false);
                resp.addProperty("message", "IError al eliminar el intercambio");
            }

            out.print(resp); 
        }
    }
}
