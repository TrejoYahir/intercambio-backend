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
import libraries.Utils;
import models.Friendship;
import models.User;

/**
 *
 * @author Yahir
 */
public class AddExternalFriend extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Gson gson = new Gson();
        JsonObject resp = new JsonObject();
        String requestData = Utils.getBody(request);
        User f;
        
        System.out.println("data: " + requestData);
        
        f = gson.fromJson(requestData, User.class);
        
        ArrayList<User> results = Queries.searchUsers(f.email);
        if(results.size() > 0) {
            System.out.println("External friend already exists");
            User r = results.get(0);
            Friendship fr = new Friendship();
            fr.idUser1 = id;
            fr.idUser2 = r.id;
            try (PrintWriter out = response.getWriter()) {
                User user = Queries.saveFriendship(fr);
                resp.addProperty("success", true);
                resp.addProperty("message", "Amigo agregado");
                resp.addProperty("friend", gson.toJson(user));
                resp.addProperty("exists", true);
                out.print(resp); 
            } catch(Exception e) {
                System.out.println("An error ocurred while returning user");
                System.out.println(e);
            }
        } else {
            System.out.println("Creating external friend");
            try(PrintWriter out = response.getWriter()) {
                User exf = Queries.createExternalFriend(f);
                Friendship fr = new Friendship();
                fr.idUser1 = id;
                fr.idUser2 = exf.id;
                User user = Queries.saveFriendship(fr);
                resp.addProperty("success", true);
                resp.addProperty("message", "Amigo agregado");
                resp.addProperty("friend", gson.toJson(user));
                resp.addProperty("exists", false);
                System.out.println("External friend " + user.firstName);
                out.print(resp); 
            } catch(Exception e) {
                System.out.println("An error ocurred while returning external friend");
                System.out.println(e);
            }
        }
        
    }


}
