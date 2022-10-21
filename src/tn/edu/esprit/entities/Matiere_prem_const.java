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
public class Matiere_prem_const {
    private int id_matiere_const ;
    private String type_matiere_const;

    public Matiere_prem_const() {
    }

    public Matiere_prem_const(String type_matiere_const) {
        this.type_matiere_const = type_matiere_const;
    }

    public Matiere_prem_const(int id_matiere_const, String type_matiere_const) {
        this.id_matiere_const = id_matiere_const;
        this.type_matiere_const = type_matiere_const;
    }

    public int getId_matiere_const() {
        return id_matiere_const;
    }

    public String getType_matiere_const() {
        return type_matiere_const;
    }

    public void setId_matiere_const(int id_matiere_const) {
        this.id_matiere_const = id_matiere_const;
    }

    public void setType_matiere_const(String type_matiere_const) {
        this.type_matiere_const = type_matiere_const;
    }

    @Override
    public String toString() {
        return  type_matiere_const +"\n";
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_matiere_const;
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
        final Matiere_prem_const other = (Matiere_prem_const) obj;
        if (this.id_matiere_const != other.id_matiere_const) {
            return false;
        }
        return true;
    }
    
}
