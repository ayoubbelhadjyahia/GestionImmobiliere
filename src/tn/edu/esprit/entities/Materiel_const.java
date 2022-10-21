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
public class Materiel_const {
    private int id_materiel_const;
    private String nom_materiel_const;
    private int prix_materiel_const;

    public Materiel_const() {
    }

    public Materiel_const(String nom_materiel_const, int prix_materiel_const) {
        this.nom_materiel_const = nom_materiel_const;
        this.prix_materiel_const = prix_materiel_const;
    }

    public Materiel_const(int id_materiel_const, String nom_materiel_const, int prix_materiel_const) {
        this.id_materiel_const = id_materiel_const;
        this.nom_materiel_const = nom_materiel_const;
        this.prix_materiel_const = prix_materiel_const;
    }

    public int getId_materiel_const() {
        return id_materiel_const;
    }



    public int getPrix_materiel_const() {
        return prix_materiel_const;
    }

    public void setId_materiel_const(int id_materiel_const) {
        this.id_materiel_const = id_materiel_const;
    }

    public String getNom_materiel_const() {
        return nom_materiel_const;
    }

    public void setNom_materiel_const(String nom_materiel_const) {
        this.nom_materiel_const = nom_materiel_const;
    }



    public void setPrix_materiel_const(int prix_materiel_const) {
        this.prix_materiel_const = prix_materiel_const;
    }

    @Override
    public String toString() {
        return "Materiel_const{" + "nom_materiel_const=" + nom_materiel_const + ", prix_materiel_const=" + prix_materiel_const + '}'+"\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id_materiel_const;
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
        final Materiel_const other = (Materiel_const) obj;
        if (this.id_materiel_const != other.id_materiel_const) {
            return false;
        }
        return true;
    }
    
}
