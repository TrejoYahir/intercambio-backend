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
import models.User;
import org.json.simple.JSONObject;

/**
 *
 * @author Yahir
 */
public class GetUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        User result;
        Gson gson = new Gson();
        int id = Integer.parseInt(request.getParameter("id"));
        result = Queries.searchUserById(id);
        String ex;
        JSONObject resp = new JSONObject();

        try (PrintWriter out = response.getWriter();) {
            if(result.id > 0) {
               ex = gson.toJson(result);
               resp.put("success", true);
               resp.put("user", ex);
            } else {
               resp.put("success", false);
               resp.put("message", "No se encontró el usuario");
            }
            out.print(resp); 
        }
    }
}
