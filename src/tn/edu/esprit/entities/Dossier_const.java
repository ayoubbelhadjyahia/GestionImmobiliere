/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.sql.Date;


/**
 *
 * @author dell
 */
public class Dossier_const {
    private int id_dossier_const;
    private Date duree_location_dossier_const;
  //  private int id_materiel_const;
    private int id_terrain_const;
    private int id_client;
    private int id_matiere_const; 

    public Dossier_const() {
    }

    public Dossier_const(Date duree_location_dossier_const, int id_terrain_const, int id_client, int id_matiere_const) {
        this.duree_location_dossier_const = duree_location_dossier_const;
        this.id_terrain_const = id_terrain_const;
        this.id_client = id_client;
        this.id_matiere_const = id_matiere_const;
    }

    public Dossier_const(int id_dossier_const, Date duree_location_dossier_const, int id_terrain_const, int id_client, int id_matiere_const) {
        this.id_dossier_const = id_dossier_const;
        this.duree_location_dossier_const = duree_location_dossier_const;
        this.id_terrain_const = id_terrain_const;
        this.id_client = id_client;
        this.id_matiere_const = id_matiere_const;
    }
    
    
    

    public int getId_dossier_const() {
        return id_dossier_const;
    }

    public Date getDuree_location_dossier_const() {
        return duree_location_dossier_const;
    }

    public int getId_terrain_const() {
        return id_terrain_const;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_matiere_const() {
        return id_matiere_const;
    }

    public void setId_dossier_const(int id_dossier_const) {
        this.id_dossier_const = id_dossier_const;
    }

    public void setDuree_location_dossier_const(Date duree_location_dossier_const) {
        this.duree_location_dossier_const = duree_location_dossier_const;
    }

    public void setId_terrain_const(int id_terrain_const) {
        this.id_terrain_const = id_terrain_const;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_matiere_const(int id_matiere_const) {
        this.id_matiere_const = id_matiere_const;
    }

    @Override
    public String toString() {
        return "Dossier_const{" + "duree_location_dossier_const=" + duree_location_dossier_const + ", id_terrain_const=" + id_terrain_const + ", id_client=" + id_client + ", id_matiere_const=" + id_matiere_const + '}'+"\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id_dossier_const;
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
        final Dossier_const other = (Dossier_const) obj;
        if (this.id_dossier_const != other.id_dossier_const) {
            return false;
        }
        return true;
    }


    
    
}
