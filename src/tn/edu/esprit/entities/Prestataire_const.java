/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author dell
 */
public class Prestataire_const {
    private int id_prestataire_const ;
    private int cin_prestataire_const;
    private String nom_prestataire_const;
    private String prenom_prestataire_const;
    private String mail_prestataire_const;
    private int id_matiere_const;

    public Prestataire_const() {
    }

    public Prestataire_const(int cin_prestataire_const, String nom_prestataire_const, String prenom_prestataire_const, String mail_prestataire_const, int id_matiere_const) {
        this.cin_prestataire_const = cin_prestataire_const;
        this.nom_prestataire_const = nom_prestataire_const;
        this.prenom_prestataire_const = prenom_prestataire_const;
        this.mail_prestataire_const = mail_prestataire_const;
        this.id_matiere_const = id_matiere_const;
    }

    public Prestataire_const(int id_prestataire_const, int cin_prestataire_const, String nom_prestataire_const, String prenom_prestataire_const, String mail_prestataire_const, int id_matiere_const) {
        this.id_prestataire_const = id_prestataire_const;
        this.cin_prestataire_const = cin_prestataire_const;
        this.nom_prestataire_const = nom_prestataire_const;
        this.prenom_prestataire_const = prenom_prestataire_const;
        this.mail_prestataire_const = mail_prestataire_const;
        this.id_matiere_const = id_matiere_const;
    }

    public int getId_prestataire_const() {
        return id_prestataire_const;
    }

    public int getCin_prestataire_const() {
        return cin_prestataire_const;
    }

    public String getNom_prestataire_const() {
        return nom_prestataire_const;
    }

    public String getPrenom_prestataire_const() {
        return prenom_prestataire_const;
    }

    public String getMail_prestataire_const() {
        return mail_prestataire_const;
    }

    public int getId_matiere_const() {
        return id_matiere_const;
    }

    public void setId_prestataire_const(int id_prestataire_const) {
        this.id_prestataire_const = id_prestataire_const;
    }

    public void setCin_prestataire_const(int cin_prestataire_const) {
        this.cin_prestataire_const = cin_prestataire_const;
    }

    public void setNom_prestataire_const(String nom_prestataire_const) {
        this.nom_prestataire_const = nom_prestataire_const;
    }

    public void setPrenom_prestataire_const(String prenom_prestataire_const) {
        this.prenom_prestataire_const = prenom_prestataire_const;
    }

    public void setMail_prestataire_const(String mail_prestataire_const) {
        this.mail_prestataire_const = mail_prestataire_const;
    }

    public void setId_matiere_const(int id_matiere_const) {
        this.id_matiere_const = id_matiere_const;
    }

    @Override
    public String toString() {
        return "Prestataire_const{" + "cin_prestataire_const=" + cin_prestataire_const + ", nom_prestataire_const=" + nom_prestataire_const + ", prenom_prestataire_const=" + prenom_prestataire_const + ", mail_prestataire_const=" + mail_prestataire_const + ", id_matiere_const=" + id_matiere_const + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_prestataire_const;
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
        final Prestataire_const other = (Prestataire_const) obj;
        if (this.id_prestataire_const != other.id_prestataire_const) {
            return false;
        }
        return true;
    }
    
    
    
    
}
