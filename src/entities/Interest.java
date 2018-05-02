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
public class Interest {
    String interest1;
    String interest2;
    String interest3;
    String interest4;
    String interest5;
    double pourcent1;
     double pourcent2;
      double pourcent3;
       double pourcent4;
        double pourcent5;

    public Interest(String interest1, String interest2, String interest3, String interest4, String interest5, double pourcent1, double pourcent2, double pourcent3, double pourcent4, double pourcent5) {
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.interest4 = interest4;
        this.interest5 = interest5;
        this.pourcent1 = pourcent1;
        this.pourcent2 = pourcent2;
        this.pourcent3 = pourcent3;
        this.pourcent4 = pourcent4;
        this.pourcent5 = pourcent5;
    }

    public Interest() {
    }

    public String getInterest1() {
        return interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public String getInterest4() {
        return interest4;
    }

    public String getInterest5() {
        return interest5;
    }

    public double getPourcent1() {
        return pourcent1;
    }

    public double getPourcent2() {
        return pourcent2;
    }

    public double getPourcent3() {
        return pourcent3;
    }

    public double getPourcent4() {
        return pourcent4;
    }

    public double getPourcent5() {
        return pourcent5;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }

    public void setInterest4(String interest4) {
        this.interest4 = interest4;
    }

    public void setInterest5(String interest5) {
        this.interest5 = interest5;
    }

    public void setPourcent1(double pourcent1) {
        this.pourcent1 = pourcent1;
    }

    public void setPourcent2(double pourcent2) {
        this.pourcent2 = pourcent2;
    }

    public void setPourcent3(double pourcent3) {
        this.pourcent3 = pourcent3;
    }

    public void setPourcent4(double pourcent4) {
        this.pourcent4 = pourcent4;
    }

    public void setPourcent5(double pourcent5) {
        this.pourcent5 = pourcent5;
    }

    @Override
    public String toString() {
        return "interest{" + "interest1=" + interest1 + ", interest2=" + interest2 + ", interest3=" + interest3 + ", interest4=" + interest4 + ", interest5=" + interest5 + ", pourcent1=" + pourcent1 + ", pourcent2=" + pourcent2 + ", pourcent3=" + pourcent3 + ", pourcent4=" + pourcent4 + ", pourcent5=" + pourcent5 + '}';
    }
    
    
    
    
    
}
