/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author Yahir
 */
public class Exchange {
    public String[] giftThemes;
    public int[] participants;
    public String exchangeName;
    public int maxAmount;
    public String exchangeDescription;
    public String limitDate;
    public String exchangeDate;
    public int idCreator;
    public String accessCode;
    public int id;
    public ArrayList<Participant> participantList;
    public ArrayList<String> giftThemesList;
}
