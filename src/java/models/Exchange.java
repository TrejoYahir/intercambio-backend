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

    public Exchange() {
    }

    public Exchange(String[] giftThemes, int[] participants, String exchangeName, int maxAmount, String exchangeDescription, String limitDate, String exchangeDate, int idCreator, String accessCode, int id, ArrayList<Participant> participantList, ArrayList<String> giftThemesList) {
        this.giftThemes = giftThemes;
        this.participants = participants;
        this.exchangeName = exchangeName;
        this.maxAmount = maxAmount;
        this.exchangeDescription = exchangeDescription;
        this.limitDate = limitDate;
        this.exchangeDate = exchangeDate;
        this.idCreator = idCreator;
        this.accessCode = accessCode;
        this.id = id;
        this.participantList = participantList;
        this.giftThemesList = giftThemesList;
    }

    public String[] getGiftThemes() {
        return giftThemes;
    }

    public void setGiftThemes(String[] giftThemes) {
        this.giftThemes = giftThemes;
    }

    public int[] getParticipants() {
        return participants;
    }

    public void setParticipants(int[] participants) {
        this.participants = participants;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getExchangeDescription() {
        return exchangeDescription;
    }

    public void setExchangeDescription(String exchangeDescription) {
        this.exchangeDescription = exchangeDescription;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(ArrayList<Participant> participantList) {
        this.participantList = participantList;
    }

    public ArrayList<String> getGiftThemesList() {
        return giftThemesList;
    }

    public void setGiftThemesList(ArrayList<String> giftThemesList) {
        this.giftThemesList = giftThemesList;
    }
    
    
}
