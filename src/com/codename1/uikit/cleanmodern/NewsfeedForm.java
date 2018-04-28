package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Publication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.image.ImageView;

/**
 * 
 *
 * @author feriel
 */

public class NewsfeedForm extends BaseForm {
 String id;
 public static int idpub;
 Resources theme,theme2;
      String ville;
      ImageViewer image;
        private Form current;

          public NewsfeedForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public NewsfeedForm(Resources res) {
      theme = UIManager.initFirstTheme("/theme");
      theme2 = UIManager.initFirstTheme("/theme_2");
   Map<String, String> v = theme2.getL10N("strings", L10NManager.getInstance().getLanguage());
        if(v == null) {
            v = theme2.getL10N("strings", "en");
        }
        UIManager.getInstance().setBundle(v);
        
        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        Log.bindCrashProtection(true);
        
        Util.register("Note", Note.class);
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
               
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Acceuil", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
       

      
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/feriel/Admin/web/app_dev.php/PublicationMobile");
        
        con.addResponseListener((NetworkEvent evt) -> {
            //System.out.println(getListRando(new String(con.getResponseData())));
            
         ArrayList<Publication>  listguide = getListPub(new String(con.getResponseData()));
            //randoaffichForm.refreshTheme();
System.out.println("ooooooooo"+listguide);
            for (Publication o : listguide) {
                try {
                    add(addItem(o));
                } catch (IOException ex) {
                }
            }
            //form.revalidate();
            //form.refreshTheme();
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    
   public Container addItem(Publication oo) throws IOException {

        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BorderLayout());
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               ImageViewer img = new ImageViewer();
//img=Image.createImage("/Logo.png").fill(170, 100);
      EncodedImage enc = EncodedImage.createFromImage(theme.getImage("dog.jpg"), false);
     img.setImage(URLImage.createToStorage(enc, oo.getImagePublication(), "http://localhost/feriel/Admin/web/images/" + oo.getImagePublication()));
       System.out.println("hhhddhdhddh"+oo.getImagePublication());
       MultiButton mb= new MultiButton();
       mb.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        mb.setPropertyValue("uiid2", "RedLabelRight");
       //
      // img.setImage(image);
         TextArea description = new TextArea(oo.getContenu_pub());
           description.setRows(2);
        description.setColumns(50);
        description.setGrowByContent(false);
        description.setEditable(false);
         description.setUIID("SlightlySmallerFontLabelLeft");
          Button fleche=new Button();
         fleche.setText("");
        fleche.setUIID("Label");
        
         com.codename1.ui.FontImage.setMaterialIcon(fleche,'');
         
         /// ajout pub
           Button fleche2=new Button();
         fleche.setText("");
        fleche.setUIID("Label");
        fleche.addActionListener(new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent evt) {
             idpub=oo.getId_pub();
             ConnectionRequest req = new ConnectionRequest();
           req.setUrl("http://localhost/feriel/Admin/web/app_dev.php/ListerCommentaire/2");

            req.addResponseListener(new ActionListener<NetworkEvent>() {

               @Override
               public void actionPerformed(NetworkEvent evt) {
                   byte[] data = (byte[]) evt.getMetaData();
                   String s = new String(data);

                  if(current != null){
            current.show();
            return;
                  }
                     
             new ActivityMain(theme2).show();                      
              
               }
    
                 
              });
             NetworkManager.getInstance().addToQueue(req);
              }
              });
       /////
        Label v = new Label(ville);
         //C1.add(BorderLayout.EAST,titre);
          Container cc= BoxLayout.encloseY(description,BorderLayout.east(fleche), (fleche2));
          C1.add(BorderLayout.SOUTH,cc); 
         // C1.addComponent(BorderLayout.EAST, fleche);
       // C2.add(titre);
        C1.add(BorderLayout.WEST,img);
       // C3.add(C1);
//        C3.add(desc);
      //  C1.add(BorderLayout.CENTER,C3);
       // C1.add(BorderLayout.SOUTH,);
       // C3.add(v);
       // C3.add(p);
      //  C1.add(BorderLayout.EAST,C3);
         Container cntEspace = new Container();
        cntEspace.setHeight(20);
        //cntEspace.add(new ImageViewer(Image.createImage(20, 20)));
        C0.add(C1);
        C0.add(cntEspace);
        Border line=Border.createCompoundBorder(Border.createLineBorder(1), null, null, null);
 
        C1.getUnselectedStyle().setBorder(line);
       
       
        return C0;
        
                
    
        
   }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
     

   }
    
     public ArrayList<Publication> getListPub(String json) {
        ArrayList<Publication> listPub = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> rando = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("    ggggg"+rando);

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) rando.get("root");
  System.out.println("hhhhh"+list);
            for (Map<String, Object> obj : list) {
                Publication o = new Publication();
            o.setId_pub((int)Float.parseFloat(obj.get("id").toString()));
          
                o.setContenu_pub(obj.get("contenu_pub").toString());
                
                            System.out.println("    ggggg"+o.getContenu_pub());

               o.setImagePublication(obj.get("image").toString());


                listPub.add(o);

            }

        } catch (IOException ex) {
        }
        return listPub;

    } 
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
      
}
