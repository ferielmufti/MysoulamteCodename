/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author feriel
 */
public class PublicationLike {
    
    private int id;
    private int idUser;
    private int idPub;

    public PublicationLike(int id, int idUser, int idPub) {
        this.id = id;
        this.idUser = idUser;
        this.idPub = idPub;
    }

    public PublicationLike() {
    }

    public PublicationLike(int idUser, int idPub) {
        this.idUser = idUser;
        this.idPub = idPub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPub() {
        return idPub;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }
    
    
    
}
