/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraries;

import email.EmailHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import models.User;
import models.Exchange;

/**
 *
 * @author Yahir
 */
public class Utils {
    private static final String CHAR_LIST = 
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 8;
    
    public static String getBody(HttpServletRequest request) throws IOException {

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        return data;
    }
     
    public static String generateCode(){
         
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        System.out.println("generated code " + randStr.toString());
        return randStr.toString();
    }
     
    /**
     * This method generates random numbers
     * @return int
     */
    private static int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    
    public static void sendMails(String to, String subject, String body) {
        for(int i = 0; i<5; i++) {
            Thread t1 = new Thread(new EmailHelper(to, subject, body));
            t1.start();
        }
    }
    
    public static String createInviteBody(String exchangeName, User user, String creator, String code, String limitDate) {
        String body = String.join(
        System.getProperty("line.separator"),
            "<h1><strong>" + user.firstName + " " + user.lastName + ", has sido invitado a un intercambio</strong></h1>",
            "<h2>"+exchangeName+"</h2>",
            "<h4><strong>Creador: </strong>"+creator+"</h4>",
            "<p>"+creator+" te ha invitado a un intercambio, por favor confirma tu participación ", 
            "antes de " +limitDate + ", puedes buscar el intercambio con el código",
            "<strong>" + code + "</strong></p><br>",
            "<a href='http://localhost:4200/#/exchange-invite/"+code.trim(),
            "?id="+user.id+"'>",
            "Visita el intercambio</a><br>",
            "<a href='http://localhost:4200/#/signup'>Registrate</a>"
        );
        
        return body;
    }
    
    public static String createPairedBody(User u1, User u2, String theme, Exchange exchange) {
        String t = "Sin preferencia";
        if(theme != null && !theme.trim().equals("")) {
            t = theme;
        }
        String body = String.join(
        System.getProperty("line.separator"),
            "<h1><strong>" + u1.firstName + " " + u1.lastName + ", un intercambio está por realizarse</strong></h1>",
            "<h2>"+exchange.exchangeName+"</h2>",
            "<h4>Se te ha asignado una pareja, no olvides conseguir su regalo antes de " +exchange.exchangeDate +"</h4>",
            "<h4><strong>Pareja: </strong>"+u2.firstName+" "+u2.lastName+" ("+u2.email+")</h4>",
            "<h4><strong>Tema de preferencia: </strong>"+t+"</h4>",
            "<p>Puedes acceder al intercambio con el código: <strong>" + exchange.accessCode + "</strong></p><br>",
            "<a href='http://localhost:4200/#/exchange-invite/"+exchange.accessCode.trim(),
            "?id="+u1.id+"'>",
            "Visita el intercambio</a><br>",
            "<a href='http://localhost:4200/#/signup'>Registrate</a>"
        );
        
        return body;
    }
    
}
