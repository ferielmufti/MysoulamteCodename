/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iServices;

import entities.Appreciation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author feriel
 */
public interface iappreciation {
     public void ajouterAppreciation(Appreciation a);
    public void supprimerAppreciation(Appreciation a);
    public List<Appreciation> consulterAppreciation();
}
