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

/**
 *
 * @author Yahir
 */
public class DeletePairs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        JsonObject resp = new JsonObject();
        Boolean result;
        int idExchange = Integer.parseInt(request.getParameter("id"));
        
        result = Queries.deletePairs(idExchange);
        System.out.println("delete finished " + result);

        try (PrintWriter out = response.getWriter();) {
            if(result) {
                resp.addProperty("success", true);
                resp.addProperty("message", "Parejas eliminadas");
            } else {
                resp.addProperty("success", false);
                resp.addProperty("message", "Error al eliminar parejas");
            }
            out.print(resp); 
        }
    }

}
