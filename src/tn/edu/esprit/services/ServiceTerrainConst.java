/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Terrain_const;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author dell
 */
public class ServiceTerrainConst implements IService<Terrain_const>{
    
    Connection cnx;


    public ServiceTerrainConst(Connection cnx) {
        this.cnx =DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Terrain_const t) {
         try {

            String req = "INSERT INTO `terrain_const`(`superficie_const`, `address_terrain_const`,`prix_const`) VALUES ('" + t.getSuperficie_const()+ "','" + t.getAddress_terrain_const()+ "','"+t.getPrix_const()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Terrain ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Terrain_const t) {
        try {
            String req="update terrain_const SET superficie_const=?,address_terrain_const=?,prix_const=? where id_terrain_const=?" ;
            PreparedStatement st = cnx.prepareStatement(req);
            //Mouch mohem tartib les instruction numero ya3ty indication 3al blasetha fel requete
           st.setInt(1, t.getSuperficie_const());
            st.setString(2, t.getAddress_terrain_const());
            st.setInt(3,t.getPrix_const());
             st.setInt(4, t.getId_terrain_const());
            
            st.executeUpdate();
            System.out.println("categorie_offre Modifier avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
     try {
            String req = "DELETE FROM terrain_const WHERE id_terrain_const="+id+"";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Terrain supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

 
    @Override
     public List<Terrain_const> getAll() {
        String rep = "SELECT * FROM `terrain_const`";
        ArrayList<Terrain_const> terrain = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                Terrain_const terr = new Terrain_const();
                terr.setId_terrain_const(rs.getInt(1));
                terr.setSuperficie_const(rs.getInt(2));
                terr.setAddress_terrain_const(rs.getString("address_terrain_const"));
                terr.setPrix_const(rs.getInt(4));

                terrain.add(terr);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(terrain+"\n");
        return terrain;
    } 

    @Override
    public Terrain_const getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}