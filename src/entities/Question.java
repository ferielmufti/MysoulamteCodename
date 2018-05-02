/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Fatma
 */
public class Question {
    String question;
    String trait;
    String type;
    String reponse1;
    String reponse2;
    String reponse3;
    public Question(){}
    public Question(String question, String type, String trait, String reponse1, String reponse2, String reponse3) {
        this.question = question;
        this.type=type;
        this.trait = trait;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
    }

    public String getQuestion() {
        return question;
    }
    public String getType()
    {
        return type;
    }

    public String getTrait() {
        return trait;
    }

    public String getReponse1() {
        return reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

   public void setType(String type)
   {
       this.type=type;
   }
    public void setTrait(String trait) {
        this.trait = trait;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    @Override
    public String toString() {
        return "Question{" + "question=" + question + ", trait=" + trait + ", type=" + type + ", reponse1=" + reponse1 + ", reponse2=" + reponse2 + ", reponse3=" + reponse3 + "}\n";
    }

    

   
    
    
    
}
