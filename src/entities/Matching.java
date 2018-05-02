/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Fatma
 */
public class Matching {
    int idUser1;
    int idUser2;
    int pourcentage; 
    public Matching(){}
    public Matching(int idUser1, int idUser2, int pourcentage) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.pourcentage = pourcentage;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Override
    public String toString() {
        return "Matching{" + "idUser1=" + idUser1 + ", idUser2=" + idUser2 + ", pourcentage=" + pourcentage + "}\n";
    }
    
}
