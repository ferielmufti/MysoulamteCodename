/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iServices;

import entities.Publication;
import java.util.List;

/**
 *
 * @author feriel
 */
public interface ipublication {
     public void ajouterPublication(Publication p);
    public void supprimerPublication(Publication p);
    public void modifierPublication (Publication pub,String contenu_pub,String date_pub);
    public  void modifierpub (String contenu_pub,int id_pub1);
    public List<Publication> consulterPublication();
     
     
    }

