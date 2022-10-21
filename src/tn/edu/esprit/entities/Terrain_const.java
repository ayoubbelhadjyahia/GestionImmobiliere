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
public class Terrain_const {
    private int id_terrain_const;
    private int superficie_const;
    private String address_terrain_const;
    private int prix_const;

    public Terrain_const() {
    }

   public Terrain_const(int superficie_const, String address_terrain_const, int prix_const) {
        this.superficie_const = superficie_const;
        this.address_terrain_const = address_terrain_const;
        this.prix_const = prix_const;
    }

    public Terrain_const(int id_terrain_const, int superficie_const, String address_terrain_const, int prix_const) {
        this.id_terrain_const = id_terrain_const;
        this.superficie_const = superficie_const;
        this.address_terrain_const = address_terrain_const;
        this.prix_const = prix_const;
    }

    public int getId_terrain_const() {
        return id_terrain_const;
    }

    public int getSuperficie_const() {
        return superficie_const;
    }

    public String getAddress_terrain_const() {
        return address_terrain_const;
    }

    public int getPrix_const() {
        return prix_const;
    }

    public void setId_terrain_const(int id_terrain_const) {
        this.id_terrain_const = id_terrain_const;
    }

    public void setSuperficie_const(int superficie_const) {
        this.superficie_const = superficie_const;
    }

    public void setAddress_terrain_const(String address_terrain_const) {
        this.address_terrain_const = address_terrain_const;
    }

    public void setPrix_const(int prix_const) {
        this.prix_const = prix_const;
    }

    @Override
    public String toString() {
        return "terrain_const{" + "superficie_const=" + superficie_const + ", address_terrain_const=" + address_terrain_const + ", prix_const=" + prix_const + '}'+"\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_terrain_const;
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
        final Terrain_const other = (Terrain_const) obj;
        if (this.id_terrain_const != other.id_terrain_const) {
            return false;
        }
        return true;
    }
    
}
