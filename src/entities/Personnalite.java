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
public class Personnalite {
    int idUser;
    int nerveux;
    int attirant;
    int sensible;
    int romantique;
    int sportif;
    int artiste;
    int musicien;	
    int famille;
    int actif;
    int sociable;
    int fidele;
    int soigne;
    int genereux;
    int ambitieux;
    int jaloux;
    int serieux;
    int drole;
    int materialiste;
    int tolerant;
    int aventurier;
    public Personnalite(){}
    public Personnalite(int idUser, int nerveux, int attirant, int sensible, int romantique, int sportif, int artiste, int musicien, int famille, int actif, int sociable, int fidele, int soigne, int genereux, int ambitieux, int jaloux, int serieux, int drole, int materialiste, int tolerant, int aventurier) {
        this.idUser = idUser;
        this.nerveux = nerveux;
        this.attirant = attirant;
        this.sensible = sensible;
        this.romantique = romantique;
        this.sportif = sportif;
        this.artiste = artiste;
        this.musicien = musicien;
        this.famille = famille;
        this.actif = actif;
        this.sociable = sociable;
        this.fidele = fidele;
        this.soigne = soigne;
        this.genereux = genereux;
        this.ambitieux = ambitieux;
        this.jaloux = jaloux;
        this.serieux = serieux;
        this.drole = drole;
        this.materialiste = materialiste;
        this.tolerant = tolerant;
        this.aventurier = aventurier;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getNerveux() {
        return nerveux;
    }

    public int getAttirant() {
        return attirant;
    }

    public int getSensible() {
        return sensible;
    }

    public int getRomantique() {
        return romantique;
    }

    public int getSportif() {
        return sportif;
    }

    public int getArtiste() {
        return artiste;
    }

    public int getMusicien() {
        return musicien;
    }

    public int getFamille() {
        return famille;
    }

    public int getActif() {
        return actif;
    }

    public int getSociable() {
        return sociable;
    }

    public int getFidele() {
        return fidele;
    }

    public int getSoigne() {
        return soigne;
    }

    public int getGenereux() {
        return genereux;
    }

    public int getAmbitieux() {
        return ambitieux;
    }

    public int getJaloux() {
        return jaloux;
    }

    public int getSerieux() {
        return serieux;
    }

    public int getDrole() {
        return drole;
    }

    public int getMaterialiste() {
        return materialiste;
    }

    public int getTolerant() {
        return tolerant;
    }

    public int getAventurier() {
        return aventurier;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNerveux(int nerveux) {
        this.nerveux = nerveux;
    }

    public void setAttirant(int attirant) {
        this.attirant = attirant;
    }

    public void setSensible(int sensible) {
        this.sensible = sensible;
    }

    public void setRomantique(int romantique) {
        this.romantique = romantique;
    }

    public void setSportif(int sportif) {
        this.sportif = sportif;
    }

    public void setArtiste(int artiste) {
        this.artiste = artiste;
    }

    public void setMusicien(int musicien) {
        this.musicien = musicien;
    }

    public void setFamille(int famille) {
        this.famille = famille;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public void setSociable(int sociable) {
        this.sociable = sociable;
    }

    public void setFidele(int fidele) {
        this.fidele = fidele;
    }

    public void setSoigne(int soigne) {
        this.soigne = soigne;
    }

    public void setGenereux(int genereux) {
        this.genereux = genereux;
    }

    public void setAmbitieux(int ambitieux) {
        this.ambitieux = ambitieux;
    }

    public void setJaloux(int jaloux) {
        this.jaloux = jaloux;
    }

    public void setSerieux(int serieux) {
        this.serieux = serieux;
    }

    public void setDrole(int drole) {
        this.drole = drole;
    }

    public void setMaterialiste(int materialiste) {
        this.materialiste = materialiste;
    }

    public void setTolerant(int tolerant) {
        this.tolerant = tolerant;
    }

    public void setAventurier(int aventurier) {
        this.aventurier = aventurier;
    }

    @Override
    public String toString() {
        return "Personnalite{" + "idUser=" + idUser + ", nerveux=" + nerveux + ", attirant=" + attirant + ", sensible=" + sensible + ", romantique=" + romantique + ", sportif=" + sportif + ", artiste=" + artiste + ", musicien=" + musicien + ", famille=" + famille + ", actif=" + actif + ", sociable=" + sociable + ", fidele=" + fidele + ", soigne=" + soigne + ", genereux=" + genereux + ", ambitieux=" + ambitieux + ", jaloux=" + jaloux + ", serieux=" + serieux + ", drole=" + drole + ", materialiste=" + materialiste + ", tolerant=" + tolerant + ", aventurier=" + aventurier +  "}\n";
    }
    

}
