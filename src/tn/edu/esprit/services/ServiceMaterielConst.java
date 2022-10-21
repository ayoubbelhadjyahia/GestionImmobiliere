/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import com.sun.javafx.collections.ElementObservableListDecorator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.edu.esprit.entities.Materiel_const;
import tn.edu.esprit.entities.Matiere_prem_const;

/**
 *
 * @author dell
 */
public  class ServiceMaterielConst implements IService<Materiel_const>{
    
    Connection cnx;

    public ServiceMaterielConst(Connection cnx) {
        this.cnx = cnx;
    }
    
    

    @Override
    public void ajouter(Materiel_const t) {
 try {
            String req = "INSERT INTO `materiel_const`(`nom_materiel_const`, `prix_materiel_const`) VALUES ('"+t.getNom_materiel_const()+"','"+t.getPrix_materiel_const()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Materiel ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Materiel_const t) {
 try {
            String req="update materiel_const SET nom_materiel_const=?,prix_materiel_const=? where id_materiel_const=?" ;
            PreparedStatement st = cnx.prepareStatement(req);
            //Mouch mohem tartib les instruction numero ya3ty indication 3al blasetha fel requete
           st.setString(1, t.getNom_materiel_const());
            st.setInt(2, t.getPrix_materiel_const());
            st.setInt(3,t.getId_materiel_const());
            
            st.executeUpdate();
            System.out.println("Materiel Modifier avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `materiel_const` WHERE `id_materiel_const`="+id+"";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Materiel supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }

    @Override
    public Materiel_const getOne(int id) {
                String rep = "SELECT * FROM `materiel_const` WHERE `id_materiel_const`="+id;
      
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);
            while (rs.next()) {
               //    System.out.println("dddd");
                Materiel_const mc=new Materiel_const();
                mc.setId_materiel_const(rs.getInt(1));
                mc.setNom_materiel_const(rs.getString(2));
                mc.setPrix_materiel_const(rs.getInt(3));
                System.out.println(mc);
                return mc;    
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        return null;
    }

    @Override
    public ObservableList<Materiel_const> getAll() {
        String rep = "SELECT * FROM `materiel_const`";
        ObservableList<Materiel_const> mac = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                Materiel_const mpc = new Materiel_const();
                mpc.setId_materiel_const(rs.getInt(1));
                mpc.setNom_materiel_const(rs.getString(2));
                mpc.setPrix_materiel_const(rs.getInt(3));

                mac.add(mpc);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                System.out.println(mac);

        return mac;  
    
    }


    
}
