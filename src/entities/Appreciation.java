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
public class Appreciation {
    private int id_app;
    private int id_pub;

    public int getId_app() {
        return id_app;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_app(int id_app) {
        this.id_app = id_app;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public Appreciation(int id_app, int id_pub) {
        this.id_app = id_app;
        this.id_pub = id_pub;
    }

    @Override
    public String toString() {
        return "AppreciationEntity{" + "id_app=" + id_app + ", id_pub=" + id_pub + '}';
    }

    public Appreciation() {
    }

    public Appreciation(int id_pub) {
        this.id_pub = id_pub;
    }
    
    
}

