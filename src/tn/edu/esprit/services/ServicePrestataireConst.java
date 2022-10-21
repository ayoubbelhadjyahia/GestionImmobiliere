/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Matiere_prem_const;
import tn.edu.esprit.entities.Prestataire_const;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author dell
 */
public class ServicePrestataireConst implements IService<Prestataire_const>{

    
    Connection cnx;

    public ServicePrestataireConst(Connection cnx) {
        this.cnx =DataSource.getInstance().getConnection();
    }
    
    
    
    @Override
    public void ajouter(Prestataire_const t) {
         try {
            String req = "INSERT INTO `prestataire_const`(`cin_prestataire_const`, `nom_prestataire_const`,`prenom_prestataire_const`,`mail_prestataire_const`,`id_matiere_const`) VALUES ('" + t.getCin_prestataire_const() + "','" + t.getNom_prestataire_const() + "','"+t.getPrenom_prestataire_const() +"','"+t.getMail_prestataire_const()+"','"+t.getId_matiere_const()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Prestataire ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Prestataire_const t) {
        try {
            String req="UPDATE `matiere_const` SET `cin_prestataire_user`='"+t.getCin_prestataire_const()+"',`nom_prestataire_user`='"+t.getNom_prestataire_const()+"',`prenom_prestataire_user`='"+t.getPrenom_prestataire_const()+"',`mail_prestataire_user`='"+t.getMail_prestataire_const()+"',`id_matiere_const`='"+t.getId_matiere_const()+"'WHERE `id_matiere_const`="+t.getId_matiere_const()+"" ;
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
            String req = "DELETE FROM prestataire_const WHERE id_prestataire_user="+id+"";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Prestataire supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   
    @Override
    public List<Prestataire_const> getAll() {
        String rep = "SELECT * FROM `prestataire_const`";
        ArrayList<Prestataire_const> prestataire = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                Prestataire_const pres = new Prestataire_const();
                pres.setId_matiere_const(rs.getInt(1));
                pres.setCin_prestataire_const(rs.getInt(2));
                pres.setNom_prestataire_const(rs.getString("nom_prestataire_const"));
                pres.setPrenom_prestataire_const(rs.getString("prenom_prestataire_const"));
                pres.setMail_prestataire_const(rs.getString("mail_prestataire_const"));
                pres.setId_matiere_const(6);

                prestataire.add(pres);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
            System.out.println(prestataire);

        return prestataire;
    }

    @Override
    public Prestataire_const getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
