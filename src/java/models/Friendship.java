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
public class Friendship {
    public int id;
    public int idUser1;
    public int idUser2;

    public Friendship() {
    }

    public Friendship(int id, int idUser1, int idUser2) {
        this.id = id;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }
}
