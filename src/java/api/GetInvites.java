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

/**
 *
 * @author Yahir
 */
public class GetInvites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        ArrayList<Exchange> results;
        Gson gson = new Gson();
        int id = Integer.parseInt(request.getParameter("id"));
        results = Queries.getInviteList(id);
        String exchangeList;
        System.out.println("Resultset size " + results.size());

        try (PrintWriter out = response.getWriter();) {
            exchangeList = gson.toJson(results);
            System.out.println("Response: " + exchangeList);

            out.print(exchangeList); 
        }
    }


}
