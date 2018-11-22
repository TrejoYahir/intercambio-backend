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
import models.User;

/**
 *
 * @author Yahir
 */
public class SelectTheme extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        Gson gson = new Gson();
        JsonObject resp = new JsonObject();
        int user = Integer.parseInt(request.getParameter("user"));
        int exchange = Integer.parseInt(request.getParameter("exchange"));
        String theme = request.getParameter("theme");
        
        int r = Queries.selectTheme(user, exchange, theme);

        try (PrintWriter out = response.getWriter();) {
            if(r > 0) {
                resp.addProperty("success", true);
                resp.addProperty("message", "Tema seleccionado");
            } else {
                resp.addProperty("success", false);
                resp.addProperty("message", "Error al seleccionar tema");
            }
            out.print(resp); 
        }
    }
}
