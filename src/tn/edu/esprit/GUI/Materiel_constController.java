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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Dossier_const;
import tn.edu.esprit.entities.Materiel_const;
import tn.edu.esprit.services.ServiceDossierConst;
import tn.edu.esprit.services.ServiceMaterielConst;
import tn.edu.esprit.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Materiel_constController implements Initializable {
    
    

    @FXML
    private TextField f_nom_mat_ajout;
    @FXML
    private Button ajouter_mat_const;
   
    @FXML
    private Button supprimer_mat_const;
    @FXML
    private TableView<Materiel_const> tree_mat_const;
    @FXML
    private TableColumn<Materiel_const, String> Nom_mat_t;
    @FXML
    private TableColumn<Materiel_const, Integer> prix_mat_t;
    @FXML
    private TextField rech_materiel_t;
    @FXML
    private Button Dossier_Materiel_retour;
    @FXML
    private Label test_nom_materiel;
    @FXML
    private TextField f_prix_mat_ajouter;
    @FXML
    private Button modifier_materiel__const;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceMaterielConst sm=new ServiceMaterielConst(cnx);
        
        
          ObservableList<Materiel_const>  list  = sm.getAll();
    
          
        Nom_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, String>("nom_materiel_const"));
         prix_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, Integer>("prix_materiel_const"));
         System.out.println(list);
         
         tree_mat_const.setItems(list);
        


        
                FilteredList<Materiel_const> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech_materiel_t.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(d -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(d.getNom_materiel_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return String.valueOf(d.getPrix_materiel_const()).contains(lowerCaseFilter); // Does not match.
                }
            });
        });
        SortedList<Materiel_const> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tree_mat_const.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tree_mat_const.setItems(sortedData);
        
        
        
        
        

        
    }
        
    @FXML
    private void Ajouter_materiel(ActionEvent event) {
        
        //java.sql.Date date1 = new java.sql.Date(id_date_dossier_const.getValue().getYear() - 1900, id_date_dossier_const.getValue().getMonthValue() - 1, id_date_dossier_const.getValue().getDayOfMonth());
       Materiel_const mc=new Materiel_const(f_nom_mat_ajout.getText(),Integer.parseInt(f_prix_mat_ajouter.getText()));
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceMaterielConst smc = new ServiceMaterielConst(cnx);
        smc.ajouter(mc);
        ObservableList<Materiel_const> list = smc.getAll();
        
        
                Nom_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, String>("nom_materiel_const"));
        prix_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, Integer>("prix_materiel_const"));

        System.out.println(list);

        tree_mat_const.setItems(list);
        
    }

    @FXML
    private void supprimer_mat_const(ActionEvent event) {
        int xx = tree_mat_const.getSelectionModel().getSelectedItem().getId_materiel_const();
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceMaterielConst sd = new ServiceMaterielConst(cnx);
        sd.supprimer(xx);
             ObservableList<Materiel_const> list=sd.getAll();
        Nom_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, String>("nom_materiel_const"));
        prix_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, Integer>("prix_materiel_const"));
 
        System.out.println(list);

        tree_mat_const.setItems(list);

    }
           private void AffichierMateriel(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceMaterielConst sd = new ServiceMaterielConst(cnx);
        ObservableList<Materiel_const> list = sd.getAll();

        Nom_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, String>("nom_materiel_const"));
        prix_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, Integer>("prix_materiel_const"));

        System.out.println(list);

        tree_mat_const.setItems(list);
        
        
       }

    @FXML
    private void Dossier_materiel_home_act(ActionEvent event) throws IOException {
        test_nom_materiel.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("Construction.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void modifier_materiel_act(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceMaterielConst sd = new ServiceMaterielConst(cnx);
        
        
        FXMain.mc = tree_mat_const.getSelectionModel().getSelectedItem();
        
         try{       
Materiel_const mc=new Materiel_const(FXMain.mc.getId_materiel_const(),f_nom_mat_ajout.getText(),Integer.parseInt( f_prix_mat_ajouter.getText()));
        System.out.println(mc);
             sd.modifier(mc);  
          ObservableList<Materiel_const>  list  = sd.getAll();
    
          
        Nom_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, String>("nom_materiel_const"));
         prix_mat_t.setCellValueFactory(new PropertyValueFactory<Materiel_const, Integer>("prix_materiel_const"));
         System.out.println(list);
         
         tree_mat_const.setItems(list);
        


        
                FilteredList<Materiel_const> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech_materiel_t.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(d -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(d.getNom_materiel_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return String.valueOf(d.getPrix_materiel_const()).contains(lowerCaseFilter); // Does not match.
                }
            });
        });
        SortedList<Materiel_const> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tree_mat_const.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tree_mat_const.setItems(sortedData);
        
        
        
        
        
         } catch (Exception e) {
             System.out.println(e.getMessage());
             
        }

    }
    

    @FXML
    private void Row_clicked_materiel(MouseEvent event) {
        try{
        Materiel_const clicked_offre=tree_mat_const.getSelectionModel().getSelectedItem();
        f_nom_mat_ajout.setText(String.valueOf(clicked_offre.getNom_materiel_const()));
        f_prix_mat_ajouter.setText(String.valueOf(clicked_offre.getPrix_materiel_const()));
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
                
                
          
    }
    
}
