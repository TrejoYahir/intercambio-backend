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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Exchange;
import org.json.simple.JSONObject;

/**
 *
 * @author Yahir
 */
public class GetExchange extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        Exchange result;
        Gson gson = new Gson();
        String code = request.getParameter("code");
        result = Queries.getExchange(code);
        String ex;
        JSONObject resp = new JSONObject();

        try (PrintWriter out = response.getWriter();) {
            if(result.accessCode != null) {
               ex = gson.toJson(result);
               resp.put("success", true);
               resp.put("exchange", ex);
            } else {
               resp.put("success", false);
               resp.put("message", "No se encontró el intercambio");
            }
            out.print(resp); 
        }
    }

}
