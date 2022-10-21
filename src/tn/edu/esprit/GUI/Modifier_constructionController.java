/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.GUI;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Dossier_const;
import tn.edu.esprit.services.ServiceDossierConst;
import tn.edu.esprit.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Modifier_constructionController implements Initializable {
     Connection cnx = DataSource.getInstance().getConnection();

ServiceDossierConst sd = new ServiceDossierConst(cnx);
    @FXML
    private TextField id_date_dossier_const_modi;
    @FXML
    private TextField id_terrain_const_modi;
    @FXML
    private TextField id_client_const_modi;
    @FXML
    private TextField id_matiere_prem_const_modi;
    @FXML
    private Button modifier_dossier_modifier;
    @FXML
    private Label modification_tesssssst;
    @FXML
    private Button annuler_modification;
    @FXML
    private Label err_date_de_creation_mod;
    @FXML
    private Label err_terrain_mod1;
    @FXML
    private Label err_nom_client_mod11;
    @FXML
    private Label err_Materiel_prem_mod111;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                    err_date_de_creation_mod.setVisible(false);
        err_terrain_mod1.setVisible(false);
        err_nom_client_mod11.setVisible(false);
        err_Materiel_prem_mod111.setVisible(false);
        
        
        try{
      id_date_dossier_const_modi.setText(FXMain.d.getDuree_location_dossier_const().toString());
       id_terrain_const_modi.setText(String.valueOf(FXMain.d.getId_terrain_const()));
       id_client_const_modi.setText(String.valueOf(FXMain.d.getId_client()));
       id_matiere_prem_const_modi.setText(String.valueOf(FXMain.d.getId_matiere_const()));
       
      
            
        }
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
    }    

    @FXML
    private void Mod_doss_modifi_act(ActionEvent event) throws IOException {
                  err_date_de_creation_mod.setVisible(false);
        err_terrain_mod1.setVisible(false);
        err_nom_client_mod11.setVisible(false);
        err_Materiel_prem_mod111.setVisible(false);
        
        if(id_date_dossier_const_modi.getText().isEmpty()){
             err_date_de_creation_mod.setVisible(true);
        }
        if(id_terrain_const_modi.getText().isEmpty()){
            err_terrain_mod1.setVisible(true);
        }
        if(id_client_const_modi.getText().isEmpty()){
           err_nom_client_mod11.setVisible(true); 
        }
            if(id_matiere_prem_const_modi.getText().isEmpty()){
           err_Materiel_prem_mod111.setVisible(true); 
        }
        else{
        
        
        try{
               
Dossier_const dd=new Dossier_const(FXMain.d.getId_dossier_const(), FXMain.d.getDuree_location_dossier_const(),Integer.parseInt( id_terrain_const_modi.getText()) ,parseInt( id_client_const_modi.getText()),parseInt(id_matiere_prem_const_modi.getText()));
        System.out.println(dd);
            sd.modifier(dd);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        modification_tesssssst.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("Construction.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
    }}

    @FXML
    private void annuler_mod_act(ActionEvent event) throws IOException {
                modification_tesssssst.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("Construction.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
        
    }
    
}
