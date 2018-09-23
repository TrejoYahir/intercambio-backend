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
public class GetPairs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        ArrayList<int[]> results = new ArrayList<>();
        Gson gson = new Gson();
        String code = request.getParameter("code");
        System.out.println("Code: " + code);
        results = Queries.getPairs(code);
        String pairList;

        try (PrintWriter out = response.getWriter();) {
            pairList = gson.toJson(results);
            System.out.println("Response: " + pairList);
            out.print(pairList); 
        }

    }


}
