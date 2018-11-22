/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Yahir
 */
public class Participant extends User{
    public boolean acceptInvite;
    public boolean isInGroup;
    public int idExchange;
    public String theme;

    public Participant() {
    }

    public Participant(boolean acceptInvite, boolean isInGroup, int idExchange) {
        this.acceptInvite = acceptInvite;
        this.isInGroup = isInGroup;
        this.idExchange = idExchange;
    }

    public Participant(boolean acceptInvite, boolean isInGroup, int idExchange, int id, String email, String firstName, String lastName, String alias, String pass) {
        super(id, email, firstName, lastName, alias, pass);
        this.acceptInvite = acceptInvite;
        this.isInGroup = isInGroup;
        this.idExchange = idExchange;
    }
    
    public boolean isAcceptInvite() {
        return acceptInvite;
    }

    public void setAcceptInvite(boolean acceptInvite) {
        this.acceptInvite = acceptInvite;
    }

    public boolean isIsInGroup() {
        return isInGroup;
    }

    public void setIsInGroup(boolean isInGroup) {
        this.isInGroup = isInGroup;
    }

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
