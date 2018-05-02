/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.Date;
import java.util.List;


  
/**
 *
 * @author hp
 */
public class Fos_user {
    enum Role
{
    admin,utilisateur,internaute,responsableEvent;
    
    
}
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private int locked;
    private int expired;
    private Date expired_at;
    private String confirmation_key;
    private Date password_requested_at;
    private Role roles;
    private int credentials_expired;
    private Date credentials_expire_at;
    private String nom;
    private String prenom;
    private int age;
    private String sexe;
    private int num_tel;
    private String photo_de_profil;
    private Date date_de_naissance;
    private String adresse;
    private String recuperation_token;

    public Fos_user() {
    }

    public String getRecuperation_token() {
        return recuperation_token;
    }

    public void setRecuperation_token(String recuperation_token) {
        this.recuperation_token = recuperation_token;
    }

    /*public Fos_user(String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, int locked, int expired, Date expired_at, String confirmation_key, Date password_requested_at, int credentials_expired, int credentials_expire_at, String nom, String prenom, int age, String sexe, int num_tel, String photo_de_profil, String date_de_naissance, String adresse) {
        this.username = username;
        this.username_canonical = null;
        this.email = email;
        this.email_canonical = null;
        this.enabled = 1;
        this.salt = "";
        this.password = password;
        this.last_login = last_login;
        this.locked = 1;
        this.expired = 0;
        this.expired_at = null;
        this.confirmation_key = null;
        this.password_requested_at = null;
        this.credentials_expired = 0;
        this.credentials_expire_at = null;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.num_tel = num_tel;
        this.photo_de_profil = photo_de_profil;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.listAmi = new ArrayList<Fos_user>();
    }
*/

    public Fos_user(String username, String email, String password, String nom, String prenom, int age, String sexe, int num_tel, String photo_de_profil, Date date_de_naissance, String adresse) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.num_tel = num_tel;
        this.photo_de_profil = photo_de_profil;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
    }

    public Fos_user(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        
      //  this.password = DigestUtils.shaHex(password);
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public int getLocked() {
        return locked;
    }

    
    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public Date getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(Date expired_at) {
        this.expired_at = expired_at;
    }

    public String getConfirmation_key() {
        return confirmation_key;
    }

    public void setConfirmation_key(String confirmation_key) {
        this.confirmation_key = confirmation_key;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public int getCredentials_expired() {
        return credentials_expired;
    }

    public void setCredentials_expired(int credentials_expired) {
        this.credentials_expired = credentials_expired;
    }

    public Date getCredentials_expire_at() {
        return credentials_expire_at;
    }

    public void setCredentials_expire_at(Date credentials_expire_at) {
        this.credentials_expire_at = credentials_expire_at;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getPhoto_de_profil() {
        return photo_de_profil;
    }

    public void setPhoto_de_profil(String photo_de_profil) {
        this.photo_de_profil = photo_de_profil;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", locked=" + locked + ", expired=" + expired + ", expired_at=" + expired_at + ", confirmation_key=" + confirmation_key + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", credentials_expired=" + credentials_expired + ", credentials_expire_at=" + credentials_expire_at + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", sexe=" + sexe + ", num_tel=" + num_tel + ", photo_de_profil=" + photo_de_profil + ", date_de_naissance=" + date_de_naissance + ", adresse=" + adresse+'}';
    }
   
   
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fos_user other = (Fos_user) obj;
        if (this.roles != other.roles) {
            return false;
        }
        return true;
    }

    
}
