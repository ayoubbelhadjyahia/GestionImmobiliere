/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.ResultSet;

import tn.edu.esprit.tools.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Dossier_const;
import tn.edu.esprit.entities.Matiere_prem_const;

/**
 *
 * @author dell
 */
public class ServiceMatierePremConst implements IService<Matiere_prem_const> {

    Connection cnx;

    public ServiceMatierePremConst(Connection cnx) {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Matiere_prem_const t) {
        try {
            String req = "INSERT INTO `matiere_prem_const`(`Type_matiere_const`) VALUES ('" + t.getType_matiere_const() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Matiere ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Matiere_prem_const t) {
        try {
            String req="UPDATE `matiere_prem_const` SET `type_matiere_const`='"+t.getType_matiere_const()+"'WHERE `id_matiere_const`="+t.getId_matiere_const()+"" ;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Matiere Modifier avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `matiere_prem_const` WHERE `id_matiere_const`="+id+"";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Matiere supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   
    @Override
    public List<Matiere_prem_const> getAll() {
        String rep = "SELECT * FROM `matiere_prem_const`";
        ArrayList<Matiere_prem_const> mat = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                Matiere_prem_const m = new Matiere_prem_const();
                m.setId_matiere_const(rs.getInt(1));
                m.setType_matiere_const(rs.getString("type_matiere_const"));

                mat.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                System.out.println(mat);

        return mat;
    }

    @Override
    public Matiere_prem_const getOne(int id) {
                String rep = "SELECT * FROM `matiere_prem_const` WHERE `id_matiere_const`="+id;
      
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
               //    System.out.println("dddd");
                Matiere_prem_const mp=new Matiere_prem_const();
                mp.setId_matiere_const(rs.getInt(1));
                mp.setType_matiere_const(rs.getString(2));
                System.out.println(mp);
                return mp;    
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        return null;
    }
    
    }
