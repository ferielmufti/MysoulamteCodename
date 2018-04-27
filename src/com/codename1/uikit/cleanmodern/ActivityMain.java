package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.sun.java.accessibility.util.AWTEventMonitor;
import entities.Commentaire;
import entities.Publication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ActivityMain extends BaseForm  {
     private String appstoreUrl;
       private static RatingWidget instance;
    Command hideShowCommand;
    private String supportEmail;
     int idd ;
     public int idpublication;
    public static int id_com;
    public ActivityMain(com.codename1.ui.util.Resources resourceObjectInstance) {
              //  idpublication=new NewsfeedForm().idpub;
        System.out.println("mmmmm"+idpublication);
        getToolbar().setTitle("Commentaires");
       
        initGuiBuilderComponents(resourceObjectInstance);
        setBackCommand(new Command("") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().minimizeApplication();
            }
        });
         
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            Commentaire c = new Commentaire();
            new CommentaireForm(c, true, ActivityMain.this).show();
           
        });
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/feriel/Admin/web/app_dev.php/ListerCommentaire/2");
        con.addResponseListener((NetworkEvent evt) -> {
            //            System.out.println(getListEtudiant(new String(con.getResponseData())));
            removeAll();
            ArrayList<Commentaire> listcomments = getListCommentaire(new String(con.getResponseData()));
            System.out.println("eeeeeeee"+listcomments);
           Commentaire.getCommentaires();
            for (Commentaire c : listcomments) {
               addCommentaire(c);
            }
    });
        NetworkManager.getInstance().addToQueue(con);
        
      
    
    
    
   
    
     
         
//      ArrayList<Commentaire> commentaires = Commentaire.getCommentaires();
//        if(commentaires.size() > 0) {
//            removeAll();
//            for(Commentaire c : commentaires) {
//                addCommentaire(c);
//            }
//        }
//        getToolbar().addSearchCommand(e -> rechercher((String)e.getSource()));
        getToolbar().addCommandToOverflowMenu("Backup Notes", null, e -> Log.p("Todo"));
        getToolbar().addCommandToOverflowMenu("Restore Notes", null, e -> Log.p("Todo"));
        getToolbar().addCommandToOverflowMenu("Rate App", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rate();
            }
        });
             }
void Rate()
{
    Preferences.set("alreadyRated", true);
        InteractionDialog id = new InteractionDialog("Please Rate "  + Display.getInstance().getProperty("AppName", "The App"));
        int height = id.getPreferredH();
        Form f = Display.getInstance().getCurrent();
        id.setLayout(new BorderLayout());
       Slider rate = createStarRankSlider();
        Button ok = new Button("OK");
        Button no = new Button("No Thanks");
        id.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(rate)).
                add(BorderLayout.SOUTH, GridLayout.encloseIn(2, no, ok));
        id.show(f.getHeight()  - height - f.getTitleArea().getHeight(), 0, 0, 0);
        no.addActionListener(e -> id.dispose());
        ok.addActionListener(e -> {
            id.dispose();
            if(rate.getProgress() >= 9) {
                if(Dialog.show("Rate On Store", "Would you mind rating us in the appstore?", "Go To Store", "Dismiss")) {
                    Display.getInstance().execute(appstoreUrl);
                }
            } else {
                if(Dialog.show("Tell Us Why?", "Would you mind writing us a short message explaining how we can improve?", "Write", "Dismiss")) {
                    Message m = new Message("Heres how you can improve  " + Display.getInstance().getProperty("AppName", "the app"));
                    Display.getInstance().sendMessage(new String[] {supportEmail}, "Improvement suggestions for " + Display.getInstance().getProperty("AppName", "your app"), m);
                }
            
}
        });
}
public static void bindRatingListener( String appstoreUrl, String supportEmail) {
        if(Preferences.get("alreadyRated", false)) {
            return;
        }
        instance = new RatingWidget();
      
        instance.init(appstoreUrl, supportEmail);
    }
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
private void showStarPickingForm() {
    Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
    hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
    hi.show();
}
    public  String getAppstoreURL() {
    if(Display.getInstance().getPlatformName().equals("ios")) {
        return "https://itunes.apple.com/us/app/kitchen-sink-codename-one/id635048865";
    }
    if(Display.getInstance().getPlatformName().equals("and")) {
        return "https://play.google.com/store/apps/details?id=com.codename1.demos.kitchen";
    }
    return null;
    
}


    
    /**
     * This is invoked from the edit note form to add a new note to the UI
     */
    public void addCommentaire(Commentaire c) {
        // if this is the first note ever added we need to remove the placeholder
        gui_noNotes.remove();
        add(createNoteCnt(c));
     
    }

    /**
     * This is used internally to update the note after it was edited with a new widget
     */
    private Container createNoteCnt(Commentaire c) {
        Button title = new Button(c.getContenu_com());
        title.setUIID("NoteTitle");
        CheckBox star = CheckBox.createToggle("");
        star.setUIID("Notebody");
        FontImage.setMaterialIcon(star, FontImage.MATERIAL_STAR_BORDER, 4);
        star.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR, "NoteTitle", 4));
        star.setSelected(c.isStarred());
        star.setBlockLead(true);
        star.addActionListener(e -> {
            c.setStarred(star.isSelected());
            c.saveCommentaire();
        
        });
         
         
        Container cnt;
        if(!c.isBodyHidden()) {
            TextArea body = new TextArea(c.getContenu_com());
            body.getAllStyles().setBgColor(Integer.parseInt(c.getColor(), 16));
            body.setUIID("NoteBody");
            body.setEditable(false);
            Font fnt = body.getUnselectedStyle().getFont();
            body.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(c.getFontSize()), Font.STYLE_PLAIN));
            cnt = BorderLayout.center(
                        BoxLayout.encloseY(title, body)
                    ).add(BorderLayout.EAST, star);
        } else {
            cnt = BorderLayout.center(title).
                            add(BorderLayout.EAST, star);
        }        
        cnt.setLeadComponent(title);
        cnt.getAllStyles().setBgTransparency(255);
        cnt.getAllStyles().setBgColor(Integer.parseInt(c.getColor(),16));
        //        System.out.println(c.getColor()+"   aaaaaaaaaa");
        Button delete = new Button("");
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 4);
        SwipeableContainer sc = new SwipeableContainer(delete, cnt);
           delete.addActionListener(e -> {
            Dialog d = new Dialog("Suppression Commentaire");
            TextArea popupBody = new TextArea("Etes vous sure de vouloir supprimer Ton Commentaite?", 3, 10);
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            
            Container btnCont = new Container(new GridLayout(1, 2));
            
            
            
            //********BOUTON OUI SUPPRESSION**********//
                
            
           Button oui = new Button("oui");
               System.out.println(" commmnnnnn"+c.getId_com());
            oui.addActionListener((ActionEvent eho) -> {
            ConnectionRequest req = new ConnectionRequest();
            id_com = c.getId_com();
                System.out.println("iii"+c.getId_com());
            Commentaire cc = new Commentaire();
              req.setUrl("http://localhost/feriel/Admin/web/app_dev.php/SupprimerMobileJson/" + id_com + "");
                 req.addResponseListener(new ActionListener<NetworkEvent>()
				{

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                        Dialog.show("Suppression", "suppression effectué!!", "Ok", null);
                        d.dispose();
                        sc.close();
                         sc.remove();
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
                });
            
         
            Button no = new Button("non");
            
            
             no.addActionListener((ActionEvent eh) -> d.dispose());
            
            btnCont.add(oui).add(no);
            
            
            
           d.add(BorderLayout.CENTER, popupBody);
          d.add(BorderLayout.SOUTH, btnCont);
            
            d.showDialog();
        });
    
                       
       /* 
        title.addActionListener(e -> {
            if(!c.isDeleted()) {
                new CommentaireForm(c, false, this).show();
                addShowListener(ee -> {
                    getContentPane().replace(sc, createNoteCnt(c), null);
                    removeAllShowListeners();
                      TextArea body = new TextArea(c.getBody());
                ConnectionRequest req = new ConnectionRequest();
             
req.setUrl("http://localhost/ariyak/ModifierCom.php?commentaire=" + body.getText()+"id="+c.getId() );
                        System.out.println(c.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                            System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Demande modifié avec succés", "Ok", null);
                           
        
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
                
                });
            }
        });*/
        sc.putClientProperty("comments", c);
        return sc;
    }
   
  
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Label gui_noNotes = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setName("ActivityMain");
        addComponent(gui_noNotes);
        gui_noNotes.setText("no_notes_text");
        gui_noNotes.setUIID("AndroidLabel12");
        gui_noNotes.setName("noNotes");
        
        
        
        
        
        
        
    // </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    

   

}
      public ArrayList<Commentaire> getListCommentaire(String json) {
        ArrayList<Commentaire> listCommentaires = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Commentaire e = new Commentaire();
                e.setContenu_com(obj.get("contenu_com").toString());
//                e.setColor(obj.get("color").toString());
          //   id_com=Integer.parseInt(obj.get("id").toString());
            //s id_com=Integer.parseInt(obj.get("id").toString());
    try{           
     id_com=Integer.parseInt(obj.get("id").toString());
     e.setId_com(id_com);
       } catch(NumberFormatException ex){           
                listCommentaires.add(e);

            }
            }
        } catch (IOException ex) {
         }
        return listCommentaires;

    }

      

    
    
 
}
