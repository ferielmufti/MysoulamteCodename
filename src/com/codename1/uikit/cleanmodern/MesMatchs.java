/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Fos_user;
import iServices.ServiceMatching;
import entities.Matching;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class MesMatchs {

    int id = 61;
    Form f;
    SpanLabel lb;
    private Resources theme;
    Form f2;

    public MesMatchs() {

        theme = UIManager.initFirstTheme("/theme_1");

        f = new Form("Mes Matchs", BoxLayout.y());
        lb = new SpanLabel(" Mes Matchs ");
        f.add(lb);
        ServiceMatching serviceMatching = new ServiceMatching();
        serviceMatching.ajouterMatchs(id);
        ArrayList<Matching> Matchings = serviceMatching.getList2(id);

        for (Matching t : Matchings) {

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Label pourcent = new Label(t.getPourcentage() + "% compatibilité");
            //photo user
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64, 64), true);
            String url = "http://localhost/MySoulmateWebessai/web/images/users/" + serviceMatching.getUserByMatch(t.getIdUser2()).getPhoto_de_profil();
            Image img = URLImage.createToStorage(placeholder, "uploads/images/" + serviceMatching.getUserByMatch(t.getIdUser2()).getPhoto_de_profil(), url);
            ImageViewer imv = new ImageViewer(img);
            //!photo user
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label idUser2 = new Label(serviceMatching.getUserByMatch(t.getIdUser2()).getPrenom() + " " + serviceMatching.getUserByMatch(t.getIdUser2()).getNom());

            C2.add(idUser2);
            C2.add(pourcent);
            C1.add(imv);
            C1.add(C2);
            C1.setLeadComponent(idUser2);
            f.add(C1);
            idUser2.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    f2 = new Form(BoxLayout.y());

                    Toolbar tb = f2.getToolbar();

                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

                        f.showBack();

                    });

                    // ImageViewer img = new ImageViewer(theme.getImage(t.getImg()));   
                    Fos_user user = serviceMatching.getUserByMatch(t.getIdUser2());
                    System.out.println("utilisateur courant=" + user);
                    Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    SpanLabel sp = new SpanLabel("\n" + user.getPrenom() + " " + user.getNom() + "\n" + user.getSexe() + "\n" + user.getDate_de_naissance() + "\n" + user.getAdresse());
                    sp.setTextBlockAlign(2);
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(85, 85), true);
                    System.out.println("Photo=" + user.getPhoto_de_profil());
                    Label pourcent1 = new Label(t.getPourcentage() + "% compatibilité");
                    String url = "http://localhost/MySoulmateWebessai/web/images/users/" + user.getPhoto_de_profil();
                    Image img1 = URLImage.createToStorage(placeholder, "uploads/images/" + user.getPhoto_de_profil(), url);
                    ImageViewer imv1 = new ImageViewer(img1);

                    Button like = new Button("Like");
                    Button dislike = new Button("Dislike");
                    C3.add(imv1);
                    C3.add(sp);
                    C3.add(pourcent1);
                    C3.add(like);
                    C3.add(dislike);
                    Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    C4.add(C3);
//                f2.add(img);
                    f2.add(C4);
                    f2.show();

                }
            });
            f.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
                NewsfeedForm h = new NewsfeedForm();
                h.show();
            });
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
