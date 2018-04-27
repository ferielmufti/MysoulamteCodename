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
public class Publication {
     private int id_pub ;
    private String contenu_pub;
    private String date_pub;
        private String imagePublication;
       private String videoPublication;

    //private int appreciation;
    //private int id;
    //private String username; 

    public Publication(int id_pub,String contenu_pub, String date_pub) {
       this.id_pub=id_pub;
       this.contenu_pub=contenu_pub;
       this.date_pub=date_pub;
    }
    public Publication(String contenu_pub,String imagePublication ,String videoPublication) {
       this.contenu_pub=contenu_pub;
             this.imagePublication=imagePublication;
     this.videoPublication=videoPublication;

    }
    
    public Publication(String contenu_pub, String imagePublication ) {
       this.contenu_pub=contenu_pub;
             this.imagePublication=imagePublication;

    }
    public Publication(int id_pub,String contenu_pub, String date_pub,String imagePublication,String videoPublication) {
               this.id_pub=id_pub;
        this.contenu_pub=contenu_pub;
       this.date_pub=date_pub;
      this.imagePublication=imagePublication;
     this.videoPublication=videoPublication;


    }

    public String getImagePublication() {
        return imagePublication;
    }

    public void setImagePublication(String imagePublication) {
        this.imagePublication = imagePublication;
    }

    public String getVideoPublication() {
        return videoPublication;
    }

    public void setVideoPublication(String videoPublication) {
        this.videoPublication = videoPublication;
    }
    

    public int getId_pub() {
        return id_pub;
    }

    
   
    public String getContenu_pub() {
        return contenu_pub;
    }

    

    public String getDate_pub() {
        return date_pub;
    }
    


    public Publication() {
    }

    public Publication(int id_pub) {
        this.id_pub = id_pub;
    }
 

    public Publication(int id_pub, String contenu_pub) {
        this.id_pub = id_pub;
        this.contenu_pub = contenu_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setContenu_pub(String contenu_pub) {
        this.contenu_pub = contenu_pub;
    }

    
    public void setDate_pub(String date_pub) {
        this.date_pub = date_pub;
    }

    public Publication(String contenu_pub) {
        this.contenu_pub = contenu_pub;
    }

    

    @Override
    public String toString() {
        return "PublicationEntity{" + "id_pub=" + id_pub + ", contenu=" + contenu_pub + ", date_pub=" + date_pub + "\n}";
    }

   
    

   

}
