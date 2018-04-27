/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iServices;


import entities.Commentaire;
import entities.Publication;
import java.util.List;

/**
 *
 * @author feriel
 */
public interface icommentaire {
    public void ajouterCommentaire(Commentaire c);
    public void supprimerCommentaire(Commentaire c);
    public void modifierCommentaire (Commentaire c);
    public List<Commentaire> consulterCom(Publication p);
    public List<Commentaire> consulterComCom(Commentaire c);
    
    
}
