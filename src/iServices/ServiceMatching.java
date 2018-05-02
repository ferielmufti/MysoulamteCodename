/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iServices;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Fos_user;
import entities.Interest;
import entities.Matching;
import entities.Personnalite;
import entities.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Fatma
 */
public class ServiceMatching {

    public ArrayList<Matching> getList2(int id) {
        ArrayList<Matching> listMatchings = new ArrayList<>();
        ArrayList<Matching> listUsers = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/listMatchs/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("Matchings: "+matchings);
                    //System.out.println(matchings);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Matching matching = new Matching();
                        float id1 = Float.parseFloat(obj.get("idUser1").toString());
                        float id2 = Float.parseFloat(obj.get("idUser2").toString());
                        float pourcentage = Float.parseFloat(obj.get("pourcentage").toString());
                        matching.setIdUser1((int) id1);
                        matching.setIdUser2((int) id2);
                        matching.setPourcentage((int) pourcentage);
                        listMatchings.add(matching);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMatchings;
    }

    public Fos_user getUserByMatch(int id) {

        ArrayList<Fos_user> listUsers = new ArrayList<>();
        Fos_user user = new Fos_user();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/find/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> users = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("Users: " + users);
                    //System.out.println(matchings);

                    List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
                    for (Map<String, Object> obj : list) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        float id = Float.parseFloat(obj.get("id").toString());

                        user.setId((int) id);
                        user.setUsername(obj.get("username").toString());
                        user.setEmail(obj.get("email").toString());
                        user.setNom(obj.get("nom").toString());
                        user.setPrenom(obj.get("prenom").toString());
                        try {
                            user.setDate_de_naissance(format.parse(obj.get("date_de_naissance").toString()));
                        } catch (ParseException ex) {
                            System.out.println("no date");
                        }
                        user.setSexe(obj.get("sexe").toString());
                        user.setAdresse(obj.get("adresse").toString());
                        user.setPhoto_de_profil(obj.get("photo_de_profil").toString());

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //System.out.println("user="+user);
        return user;
    }

    public void ajouterMatchs(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/ajoutermatchs/" + id);

        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Matchs ajoutés!");

    }

    public ArrayList<Question> TestPerso() {
        ArrayList<Question> listQuestion = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/testperso");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> questions = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) questions.get("root");
                    for (Map<String, Object> obj : list) {
                        Question question = new Question();

                        question.setTrait(obj.get("trait").toString());
                        question.setType(obj.get("type").toString());
                        question.setQuestion(obj.get("question").toString());
                        question.setReponse1(obj.get("reponse1").toString());
                        question.setReponse2(obj.get("reponse2").toString());
                        question.setReponse3(obj.get("reponse3").toString());
                        listQuestion.add(question);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("listq=" + listQuestion);

        return listQuestion;
    }

    public ArrayList<Question> TestPref() {
        ArrayList<Question> listQuestion = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/testpref");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> questions = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) questions.get("root");
                    for (Map<String, Object> obj : list) {
                        Question question = new Question();

                        question.setTrait(obj.get("trait").toString());
                        question.setType(obj.get("type").toString());
                        question.setQuestion(obj.get("question").toString());
                        question.setReponse1(obj.get("reponse1").toString());
                        question.setReponse2(obj.get("reponse2").toString());
                        question.setReponse3(obj.get("reponse3").toString());
                        listQuestion.add(question);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("listq=" + listQuestion);

        return listQuestion;
    }

    public void ajouterPourcentagePerso(int id, String trait, int reponse) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/ajouterpoucentageperso/" + id + "/" + trait + "/" + reponse);

        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Pourcentage ajouté perso!");

    }

    public void ajouterPourcentagePref(int id, String trait, int reponse) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/ajouterpoucentagepref/" + id + "/" + trait + "/" + reponse);

        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Pourcentage ajouté pref!");

    }

    public void ajouterPersoVide(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/emptyperso/" + id);

        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Empty perso ajouté!");

    }

    public void ajouterPrefVide(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/emptypref/" + id);

        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Emtpy pref ajouté!");

    }

    public double getPourcentagePerso(int id, String trait) {
        System.out.println("poucentage perso");
        double p;
List<Double> liste= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/getpourcentageperso/" + id+"/"+trait);
        System.out.println("url="+con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> persos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(matchings);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) persos.get("root");
                    for (Map<String, Object> obj : list) {

                        float i = Float.parseFloat(obj.get(trait).toString());
                        System.out.println("i="+i);
                     liste.add((double)i);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //System.out.println("user="+user);
        System.out.println("liste="+liste);
        return liste.get(0);
    }
    public Interest getInterestsPerso(int id) {
        Interest interest= new Interest();
        

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/interestsperso/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("Matchings: "+matchings);
                    System.out.println(matchings);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    System.out.println("list="+list);
                    for (Map<String, Object> obj : list) {
                     List<String> keys = new ArrayList(obj.keySet());
                        
                        interest.setInterest1((keys.get(0)));
                        interest.setInterest2(keys.get(1));
                        interest.setInterest3(keys.get(2));
                        interest.setInterest4(keys.get(3));
                        interest.setInterest5(keys.get(4));
                        float p1 = Float.parseFloat(obj.get(keys.get(0)).toString());
                        float p2 = Float.parseFloat(obj.get(keys.get(1)).toString());
                        float p3 = Float.parseFloat(obj.get(keys.get(2)).toString());
                        float p4 = Float.parseFloat(obj.get(keys.get(3)).toString());
                        float p5 = Float.parseFloat(obj.get(keys.get(4)).toString());
                        interest.setPourcent1((double) p1);
                        interest.setPourcent2((double) p2);
                        interest.setPourcent3((double) p3);
                        interest.setPourcent4((double) p4);
                        interest.setPourcent5((double) p5);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("interest="+interest);
        return interest;
    }

    public Interest getInterestsPref(int id) {
 Interest interest= new Interest();
        

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/interestspref/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("Matchings: "+matchings);
                    System.out.println(matchings);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    System.out.println("list="+list);
                    for (Map<String, Object> obj : list) {
                     List<String> keys = new ArrayList(obj.keySet());
                        
                        interest.setInterest1((keys.get(0)));
                        interest.setInterest2(keys.get(1));
                        interest.setInterest3(keys.get(2));
                        interest.setInterest4(keys.get(3));
                        interest.setInterest5(keys.get(4));
                        float p1 = Float.parseFloat(obj.get(keys.get(0)).toString());
                        float p2 = Float.parseFloat(obj.get(keys.get(1)).toString());
                        float p3 = Float.parseFloat(obj.get(keys.get(2)).toString());
                        float p4 = Float.parseFloat(obj.get(keys.get(3)).toString());
                        float p5 = Float.parseFloat(obj.get(keys.get(4)).toString());
                        interest.setPourcent1((double) p1);
                        interest.setPourcent2((double) p2);
                        interest.setPourcent3((double) p3);
                        interest.setPourcent4((double) p4);
                        interest.setPourcent5((double) p5);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("interest="+interest);
        return interest;    }
}
