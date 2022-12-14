package com.example.tradi_p.serge;

public class ModelDonnees {

    String id, nom, prenom, codeTradiP, categorie, adresseComplete,
            codePostal, specialite, mail;


    public ModelDonnees() {

    }

    public ModelDonnees(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public ModelDonnees(String id, String nom, String prenom,
                        String codeTradiP, String categorie, String
                   adresseComplete, String codePostal, String
                   specialite, String mail) {


        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.codeTradiP = codeTradiP;
        this.categorie = categorie;
        this.adresseComplete = adresseComplete;
        this.codePostal = codePostal;
        this.specialite = specialite;
        this.mail = mail;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCodeTradP() {
        return codeTradiP;
    }

    public void setCodeTradP(String codeTradP) {
        this.codeTradiP = codeTradP;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAdresseComplete() {
        return adresseComplete;
    }

    public void setAdresseComplete(String adresseComplete) {
        this.adresseComplete = adresseComplete;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}