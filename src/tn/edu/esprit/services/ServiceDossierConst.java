/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import Dto.Dossier_constDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javax.mail.Session;
import tn.edu.esprit.entities.Dossier_const;
import tn.edu.esprit.entities.Materiel_const;
import tn.edu.esprit.tools.DataSource;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dell
 */
public class ServiceDossierConst implements IService<Dossier_const>{
    public Label sentBoolValue;
    Connection cnx;
    
        public ServiceDossierConst(Connection cnx) {
        this.cnx = DataSource.getInstance().getConnection();
        
        
    }

    public ServiceDossierConst() {
    }

        
                
    public Materiel_const GetMaterielById(int id){
       
        
          String rep = "SELECT * FROM `materiel_const` WHERE `id_materiel_const`="+id;
      
        Statement stm;
        try {
            
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
                
                //   System.out.println("dddd");
                Materiel_const Mat = new Materiel_const();
                Mat.setId_materiel_const(rs.getInt(1));
                Mat.setNom_materiel_const(rs.getString(2));
                Mat.setPrix_materiel_const(rs.getInt(3));
                System.out.println(Mat); 
                return Mat;
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        return null;
    }
    
    
     public Dossier_const GetDossierById(int id){
       
    //    String s=String.valueOf(id);  
        
        
          String rep = "SELECT * FROM `dossier_const` WHERE `id_dossier_const`="+id;
      
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);
            while (rs.next()) {
               //    System.out.println("dddd");
                Dossier_const ds = new Dossier_const();
                ds.setId_dossier_const(rs.getInt(1));
                ds.setDuree_location_dossier_const(rs.getDate(2));
                ds.setId_terrain_const(rs.getInt(3));
                ds.setId_client(rs.getInt(4));
                ds.setId_matiere_const(rs.getInt(5));

                return ds;
                
            }
            
          

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        return null;
    }
        
    public List<Materiel_const> GetMaterielDossier(int id){
        List<Materiel_const> l = new ArrayList<Materiel_const>();
   
          String rep = "SELECT * FROM `dossier_const_materiel_const` WHERE `id_dossier_const`="+id;
          if(GetDossierById(id)!=null)
        System.out.println(GetDossierById(id));
          
          else {
              System.out.println("Dossier non trouvale");
              
                      
          }
        Statement stm;
        try {
           
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);
            while (rs.next()) {
       
               Materiel_const Mat = new Materiel_const();
               Mat=GetMaterielById(rs.getInt("id_materiel_const"));

               l.add(Mat);
               System.out.println(Mat);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
        return l;
        
    }
    
    
       public void affecterMatrielDossier(int idDossier,int idMatriel) {
        if(GetDossierById(idDossier)!=null && GetMaterielById(idMatriel)!=null)        
        {        
         try {
            String req =  "INSERT INTO `dossier_const_materiel_const`(`id_materiel_const`, `id_dossier_const`) VALUES ('"+idMatriel+"','"+idDossier+"')";
           
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Affecter avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
            
        }
        else {
          System.out.println("Doosier ou mateilr n'exist pas");  
        }
        
    }
    
    
    @Override
    public void ajouter(Dossier_const t) {
         try {
            // t.setId_materiel_const(1);
            String req = "INSERT INTO `dossier_const`(`duree_location_dossier_const`,`id_terrain_const`,`user_id`,`id_matiere_const`) VALUES ('" + t.getDuree_location_dossier_const()+ "','"+t.getId_terrain_const()+"','"+t.getId_client()+"','"+t.getId_matiere_const()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Dossier ajouté avec succés");
            try{
               sendEmail("fares.amal@esprit.tn","ajout dossier","dossier cree avec succés :" + t);
            }
            catch(Exception e){}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    
 
    
    @Override
    public void modifier(Dossier_const t) {
       try {
            String req="update dossier_const SET duree_location_dossier_const=?,id_terrain_const=?,user_id=?,id_matiere_const=? where id_dossier_const=?" ;
            PreparedStatement st = cnx.prepareStatement(req);
           st.setDate(1, t.getDuree_location_dossier_const());
             st.setInt(2, t.getId_terrain_const());
             st.setInt(3, t.getId_client());
             st.setInt(4, t.getId_matiere_const());
             st.setInt(5, t.getId_dossier_const());
             
            st.executeUpdate();
            System.out.println("dossier Modifier avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
     try {
            String req = "DELETE FROM dossier_const WHERE id_dossier_const="+id+"";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("dossier supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }

    

    @Override
    public ObservableList<Dossier_const> getAll() {
        
        
          String rep = "SELECT * FROM `dossier_const` ";
      ObservableList<Dossier_const> l = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
               //    System.out.println("dddd");
                Dossier_const ds = new Dossier_const();
                ds.setId_dossier_const(rs.getInt(1));
                ds.setDuree_location_dossier_const(rs.getDate(2));
                ds.setId_terrain_const(rs.getInt(3));
                ds.setId_client(rs.getInt(4));
                ds.setId_matiere_const(rs.getInt(5));

                l.add(ds);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println(l);
        
        return l; 
    }

    @Override
    public Dossier_const getOne(int id) {
         String rep = "SELECT * FROM `dossier_const` WHERE `id_dossier_const`="+id;
      
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(rep);

            while (rs.next()) {
               //    System.out.println("dddd");
                Dossier_const ds = new Dossier_const();
                ds.setId_dossier_const(rs.getInt(1));
                ds.setDuree_location_dossier_const(rs.getDate(2));
                ds.setId_client(rs.getInt(3));
                ds.setId_matiere_const(rs.getInt(4));
                System.out.println(ds);
                return ds;    
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        return null;
    }
    
    
     public void sendEmail(String to , String sub , String body){
        //String to = emailToField.getText();
        //String from = emailFromField.getText();
        String host = "smtp.gmail.com";
       // final String username = emailFromField.getText();
       // final String password = emailPasswordField.getText();
//  from =username="sabri.krima@esprit.tn";
// password="203JMT2383";
        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayoub.belhadjyahia@esprit.tn", "213JMT8699");
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress("ayoub.belhadjyahia@esprit.tn"));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);
            m.setText(body);

            //send mail

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }
      public Dossier_constDto ConvertirFromDossieToDto(Dossier_const d)
      {
          Dossier_constDto dc = new Dossier_constDto();
          dc.setId_dossier_const(d.getId_dossier_const());
          dc.setId_client(d.getId_client());
          dc.setDuree_location_dossier_const(d.getDuree_location_dossier_const());
          dc.setId_matiere_const(d.getId_matiere_const());
          dc.setId_terrain_const(d.getId_terrain_const());
          
          ServiceMatierePremConst ce = new ServiceMatierePremConst(cnx);
          dc.setNomMatriel(ce.getOne(d.getId_matiere_const()).getType_matiere_const());
          
      return dc;
      }
          public Dossier_const ConvertirFromDtoToDossie(Dossier_constDto d)
      {
          Dossier_const dc = new Dossier_const();
          dc.setId_dossier_const(d.getId_dossier_const());
          dc.setId_client(d.getId_client());
          dc.setDuree_location_dossier_const(d.getDuree_location_dossier_const());
          dc.setId_matiere_const(d.getId_matiere_const());
          dc.setId_terrain_const(d.getId_terrain_const());
          
         
          
      return dc;
      }
     
    
}
