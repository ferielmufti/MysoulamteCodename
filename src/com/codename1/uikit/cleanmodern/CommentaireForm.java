package com.codename1.uikit.cleanmodern;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import entities.Commentaire;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hand coded edit "form" that allows editing a specific note
 *
 * @author feriel
 */
public class CommentaireForm extends Form {
    private static final String[] COLORS = { 
        "2d00b3", "3900e6", "13004d",
        "cc0099", "666666", "595959",
        "737373", "4d4d4d", "1a1a1a"
    };
    private String appstoreUrl;
     //  private static RatingWidget instance;
    Command hideShowCommand;

    private String supportEmail;
    
   public CommentaireForm(Commentaire c, boolean isNew, ActivityMain parentForm) {
       
        super("", new BorderLayout());
        TextField title = new TextField(c.getContenu_com(), "Title", 20, TextArea.ANY);
       // Label title = new Label();
        TextArea body = new TextArea(c.getContenu_com());
        System.out.println("    bodyyyy"+body.getText());
                ConnectionRequest req=new ConnectionRequest();

        body.setHint("Note");
        title.getHintLabel().setUIID("NoteTitle");
        title.getHintLabel().getAllStyles().setFgColor(0xcccccc);
        add(BorderLayout.NORTH, title);
        add(BorderLayout.CENTER, body);
        title.setUIID("NoteTitle");
        body.setUIID("NoteBody");
        Font fnt = body.getUnselectedStyle().getFont();
     //   body.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(c.getFontSize()), Font.STYLE_PLAIN));
         getContentPane().getUnselectedStyle().setBgTransparency(255);
        getContentPane().getUnselectedStyle();
        
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PALETTE, e -> {
            Dialog colorPicker = new Dialog("dialog_note_colour");
            colorPicker.setDisposeWhenPointerOutOfBounds(true);
            colorPicker.setBackCommand("", null, ee -> colorPicker.dispose());
            colorPicker.setLayout(new GridLayout(3, 3));
            for(int iter = 0 ; iter < COLORS.length ; iter++) {
                Button choose = new Button("");
                Style s = choose.getAllStyles();
                s.setAlignment(Component.CENTER);
                int color = Integer.parseInt(COLORS[iter], 16);
                System.out.println(color);
                s.setBorder(RoundBorder.create().color(color));
                if(color == getContentPane().getUnselectedStyle().getBgColor()) {
                    FontImage.setMaterialIcon(choose, FontImage.MATERIAL_CHECK_CIRCLE, 3.5f);
                }
                choose.addActionListener(ee -> {
                    colorPicker.dispose();
                    getContentPane().getUnselectedStyle();
                    repaint();
                });
                colorPicker.add(choose);
            }
            colorPicker.showPacked(BorderLayout.CENTER, true);
        });
        
        getToolbar().setBackCommand("", e -> {
      c.setContenu_com(title.getText());
            c.setContenu_com(body.getText());
         //   c.setColor(getContentPane().getUnselectedStyle().getBgColor()+"");
            if(isNew) {
                if(Dialog.show("Save Changes", "", "Yes", "No")) {
                    c.saveCommentaire();
                    parentForm.addCommentaire(c);
                }
            } else {
                c.saveCommentaire();
            }
            parentForm.showBack();
        });
        Button ajouter= new Button("ajouter");
        this.addComponent(BorderLayout.SOUTH,ajouter);
        
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            c.setContenu_com(title.getText());
            c.setContenu_com(body.getText());
           // c.setColor(getContentPane().getUnselectedStyle().getBgColor()+"");
            if(isNew) {
                if(Dialog.show("Save Changes", "", "Yes", "No")) {
                   
                req.setUrl("http://localhost/feriel/Admin/web/app_dev.php/ajoutMobile/4?contenu_com=" + body.getText());
                //req.setUrl("http://localhost/feriel/Admin/web/app_dev.php/ajoutMobile/"+ c.getId_pub() +"?contenu_com=" + body.getText());
                c.saveCommentaire();
                   parentForm.addCommentaire(c);
                req.addResponseListener(new ActionListener<NetworkEvent>()
				{

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
                }
            } else {
                c.saveCommentaire();
                
            }
           new ActivityMain(com.codename1.ui.util.Resources.getGlobalResources()).show();
        } 
            });
         
        
          
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_FORMAT_SIZE, e -> {
            Slider s = new Slider();
            s.setMinValue(0);
            s.setMaxValue(50);
         //   s.setProgress(Math.round(c.getFontSize() * 10));
            s.setEditable(true);
            InteractionDialog id = new InteractionDialog();
            id.setUIID("Dialog");
            id.setLayout(new BorderLayout());
            id.add(BorderLayout.CENTER, s);
            s.addDataChangedListener((i, ii) -> {
             //   c.setFontSize(1 + ((float)s.getProgress()) / 10.0f); 
              //  body.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(c.getFontSize()), Font.STYLE_PLAIN));
                body.repaint();
            });
            Button ok = new Button("OK");
            id.add(BorderLayout.EAST, ok);
            ok.addActionListener(ee -> id.dispose());
            id.show(getLayeredPane().getHeight() - ok.getPreferredH() * 2, 0, 0, 0);
        });
        
        addHideShowCommand(c);
    }
    
    void addHideShowCommand(Commentaire n) {
        if(hideShowCommand != null) {
            removeCommand(hideShowCommand);
        }
        if(n.isBodyHidden()) {
            hideShowCommand = getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_VISIBILITY, e -> {
                    n.setBodyHidden(false);
                    addHideShowCommand(n);
                });
        } else {
            hideShowCommand = getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_VISIBILITY_OFF, e -> {
                    n.setBodyHidden(true);
                    addHideShowCommand(n);
                });
        }
        getToolbar().revalidate();
        

    }
    
}
