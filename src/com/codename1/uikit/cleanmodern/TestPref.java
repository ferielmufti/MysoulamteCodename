/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Fos_user;
import entities.Matching;
import entities.Question;
import iServices.ServiceMatching;
import java.util.ArrayList;

/**
 *
 * @author Fatma
 */
public class TestPref {

    Form f;
    Label question;
    RadioButton reponse1;
    RadioButton reponse2;
    RadioButton reponse3;
    Label nbquestion;
    Button btnajout;
    private Resources theme;
    int id = 61;

    int i = 0;

    public TestPref() {
       theme = UIManager.initFirstTheme("/theme_1");
        f = new Form("Test préférences");
        ServiceMatching serviceMatching = new ServiceMatching();
        ArrayList<Question> Questions = serviceMatching.TestPref();
        serviceMatching.ajouterPrefVide(id);
        Label nbquestion = new Label("Question numéro 1");
        btnajout = new Button("Suivant");
        question = new Label(Questions.get(0).getQuestion());
        reponse1 = new RadioButton(Questions.get(0).getReponse1());
        reponse2 = new RadioButton(Questions.get(0).getReponse2());
        reponse3 = new RadioButton(Questions.get(0).getReponse3());
        ButtonGroup gp = new ButtonGroup(reponse1, reponse2, reponse3);
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        C.add(nbquestion);
        C.add(question);
        C.add(reponse1);
        C.add(reponse2);
        C.add(reponse3);
        C.add(btnajout);
        f.add(C);
        btnajout.addActionListener((e) -> {
            System.out.println("Entier i=" + i);
            if (i < 39) {
                int selected = gp.getSelectedIndex();
                System.out.println("Selected=" + selected);
                int reponse = 0;
                switch (selected) {
                    case 0:
                        reponse = 25;
                        break;
                    case 1:
                        reponse = 50;
                        break;
                    case 2:
                        reponse = 0;
                        break;
                    default:
                        break;
                }
                serviceMatching.ajouterPourcentagePref(id, Questions.get(i).getTrait(), reponse);
                System.out.println("trait=" + Questions.get(i).getTrait());
                i++;
                System.out.println("i=" + i);
                nbquestion.setText("Question numéro "+ (i+1));
                question.setText(Questions.get(i).getQuestion());
                reponse1.setText(Questions.get(i).getReponse1());
                reponse2.setText(Questions.get(i).getReponse2());
                reponse3.setText(Questions.get(i).getReponse3());

            } else {
                int selected = gp.getSelectedIndex();
                System.out.println("Selected=" + selected);
                int reponse = 0;
                if (selected == 0) {
                    reponse = 25;
                } else if (selected == 1) {
                    reponse = 50;
                } else if (selected == 2) {
                    reponse = 0;
                }
                serviceMatching.ajouterPourcentagePref(id, Questions.get(i).getTrait(), reponse);
                System.out.println("trait=" + Questions.get(i).getTrait());
                System.out.println("trait=" + Questions.get(i).getTrait());
                MesMatchs a = new MesMatchs();
                a.getF().show();
            }
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
