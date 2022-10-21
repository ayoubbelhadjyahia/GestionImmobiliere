/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.GUI;

import Dto.Dossier_constDto;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Dossier_const;
import tn.edu.esprit.entities.Matiere_prem_const;
import tn.edu.esprit.services.ServiceDossierConst;
import tn.edu.esprit.services.ServiceMatierePremConst;
import tn.edu.esprit.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ConstructionController implements Initializable {

    private Integer[] a = {1, 2, 3};
    //private String[] Roles ={"partnaire","client","coach"};
    @FXML
    private Pane paneCons;
    @FXML
    private Button ajoutDossier;
    @FXML
    private ComboBox<Integer> id_client_const;
    @FXML
    private ComboBox<Integer> id_terrain_const;
    @FXML
    private DatePicker id_date_dossier_const;
    @FXML
    private ComboBox<Matiere_prem_const> id_matiere_prem_const;
    @FXML
    private TableView<Dossier_constDto> tableau_Dossier;
    @FXML
    private TableColumn<Dossier_const, Date> date_creation_t;
    @FXML
    private TableColumn<Dossier_const, Integer> terrain_t;
    @FXML
    private TableColumn<Dossier_const, Integer> nom_client_t;
    @FXML
    private TableColumn<Dossier_const, String> matiriel_p_t;
    @FXML
    private TextField rech_doss_t;
    @FXML
    private Button supprimer_dossier;
    @FXML
    private Label nom_client_aff_css;
    @FXML
    private Label terrain_const_aff_css;
    @FXML
    private Label date_aff_css;
    @FXML
    private Label matiere_prem_aff_css;
    @FXML
    private Label recherche_const_css;
    @FXML
    private Label err_dat;
    @FXML
    private Label err_terr;
    @FXML
    private Label err_cliet;
    @FXML
    private Label err_mat;
    @FXML
    private Button modifier_dossier_page;
    @FXML
    private Button Materiel_dossier_home;
        Connection cnx = DataSource.getInstance().getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// FXMain.d = tableau_Dossier.getSelectionModel().getSelectedItem();
//id_client_const.setValue(FXMain.d.getId_client());
        err_dat.setVisible(false);
        err_terr.setVisible(false);
        err_cliet.setVisible(false);
        err_mat.setVisible(false);

        //  id_terrain_const.getItems().addAll(Roles);
        ServiceDossierConst sd = new ServiceDossierConst(cnx);
        ServiceMatierePremConst sm = new ServiceMatierePremConst(cnx);
        List<Matiere_prem_const> m = sm.getAll();

        id_matiere_prem_const.getItems().addAll(m);

        id_client_const.getItems().addAll(a);
        id_terrain_const.getItems().addAll(a);

        //   int x1 = id_matiere_prem_const.getValue().getId_matiere_const();
        ObservableList<Dossier_const> list = sd.getAll();
        ObservableList<Dossier_constDto> listdto =  FXCollections.observableArrayList();
        for (Dossier_const dossier_const : list) {
            listdto.add(sd.ConvertirFromDossieToDto(dossier_const));
        }
        
        date_creation_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Date>("duree_location_dossier_const"));
        terrain_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_terrain_const"));
        nom_client_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_client"));
        matiriel_p_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, String>("nomMatriel"));
        System.out.println(list);

        tableau_Dossier.setItems(listdto);

        FilteredList<Dossier_constDto> filteredData = new FilteredList<>(listdto, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech_doss_t.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(d -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(d.getId_client()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(d.getId_terrain_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(d.getDuree_location_dossier_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } 
                   else if (d.getNomMatriel().toLowerCase().contains(lowerCaseFilter)) {
					return true;}
                   else {
                    return String.valueOf(d.getId_matiere_const()).contains(lowerCaseFilter); // Does not match.
                }
            });
        });
        SortedList<Dossier_constDto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableau_Dossier.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableau_Dossier.setItems(sortedData);
    }

    private void AffichierDossier(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceDossierConst sd = new ServiceDossierConst(cnx);
    
        //   int x1 = id_matiere_prem_const.getValue().getId_matiere_const();
        ObservableList<Dossier_const> list = sd.getAll();
        ObservableList<Dossier_constDto> listdto =  FXCollections.observableArrayList();
        for (Dossier_const dossier_const : list) {
            listdto.add(sd.ConvertirFromDossieToDto(dossier_const));
        }
        
        date_creation_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Date>("duree_location_dossier_const"));
        terrain_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_terrain_const"));
        nom_client_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_client"));
        matiriel_p_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, String>("nomMatriel"));
        System.out.println(list);

        tableau_Dossier.setItems(listdto);

        FilteredList<Dossier_constDto> filteredData = new FilteredList<>(listdto, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech_doss_t.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(d -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(d.getId_client()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(d.getId_terrain_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(d.getDuree_location_dossier_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } 
                   else if (d.getNomMatriel().toLowerCase().contains(lowerCaseFilter)) {
					return true;}
                   else {
                    return String.valueOf(d.getId_matiere_const()).contains(lowerCaseFilter); // Does not match.
                }
            });
        });
        SortedList<Dossier_constDto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableau_Dossier.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableau_Dossier.setItems(sortedData);
    }

    @FXML
    private void supprimer_doss_t(ActionEvent event) {
        int xx = tableau_Dossier.getSelectionModel().getSelectedItem().getId_dossier_const();
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceDossierConst sd = new ServiceDossierConst(cnx);
        sd.supprimer(xx);
      
        //   int x1 = id_matiere_prem_const.getValue().getId_matiere_const();
        ObservableList<Dossier_const> list = sd.getAll();
        ObservableList<Dossier_constDto> listdto =  FXCollections.observableArrayList();
        for (Dossier_const dossier_const : list) {
            listdto.add(sd.ConvertirFromDossieToDto(dossier_const));
        }
        
        date_creation_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Date>("duree_location_dossier_const"));
        terrain_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_terrain_const"));
        nom_client_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_client"));
        matiriel_p_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, String>("nomMatriel"));
        System.out.println(list);

        tableau_Dossier.setItems(listdto);

        FilteredList<Dossier_constDto> filteredData = new FilteredList<>(listdto, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech_doss_t.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(d -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(d.getId_client()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(d.getId_terrain_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(d.getDuree_location_dossier_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } 
                   else if (d.getNomMatriel().toLowerCase().contains(lowerCaseFilter)) {
					return true;}
                   else {
                    return String.valueOf(d.getId_matiere_const()).contains(lowerCaseFilter); // Does not match.
                }
            });
        });
        SortedList<Dossier_constDto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableau_Dossier.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableau_Dossier.setItems(sortedData);

    }

    @FXML
    private void ajouter_Doss(ActionEvent event) {
      
    err_dat.setVisible(false);
        err_terr.setVisible(false);
        err_cliet.setVisible(false);
        err_mat.setVisible(false);
        if(id_terrain_const.getValue()== null){        err_terr.setVisible(true);
}
             if(id_client_const.getValue()== null){        err_cliet.setVisible(true);
}
                 if(id_matiere_prem_const.getValue()== null){        err_mat.setVisible(true);
}
                                 if(id_date_dossier_const.getValue()== null){        err_dat.setVisible(true);
}
        else
        {
        
        
        
        java.sql.Date date1 = new java.sql.Date(id_date_dossier_const.getValue().getYear() - 1900, id_date_dossier_const.getValue().getMonthValue() - 1, id_date_dossier_const.getValue().getDayOfMonth());
        Dossier_const d = new Dossier_const(date1, id_terrain_const.getValue(), id_client_const.getValue(), id_matiere_prem_const.getValue().getId_matiere_const());
        Connection cnx = DataSource.getInstance().getConnection();
        ServiceDossierConst sd = new ServiceDossierConst(cnx);
        sd.ajouter(d);
        
        //   int x1 = id_matiere_prem_const.getValue().getId_matiere_const();
        ObservableList<Dossier_const> list = sd.getAll();
        ObservableList<Dossier_constDto> listdto =  FXCollections.observableArrayList();
        for (Dossier_const dossier_const : list) {
            listdto.add(sd.ConvertirFromDossieToDto(dossier_const));
        }
        
        date_creation_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Date>("duree_location_dossier_const"));
        terrain_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_terrain_const"));
        nom_client_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, Integer>("id_client"));
        matiriel_p_t.setCellValueFactory(new PropertyValueFactory<Dossier_const, String>("nomMatriel"));
        System.out.println(list);

        tableau_Dossier.setItems(listdto);

        FilteredList<Dossier_constDto> filteredData = new FilteredList<>(listdto, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech_doss_t.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(de -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(de.getId_client()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(de.getId_terrain_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(de.getDuree_location_dossier_const()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } 
                  else if (de.getNomMatriel().toLowerCase().contains(lowerCaseFilter)) {
					return true;}
                  else {
                    return String.valueOf(d.getId_matiere_const()).contains(lowerCaseFilter); // Does not match.
                }
            });
        });
        SortedList<Dossier_constDto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableau_Dossier.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableau_Dossier.setItems(sortedData);
        }

    }



    @FXML
    private void modifier_doss__page(ActionEvent event) throws IOException {
                ServiceDossierConst sd = new ServiceDossierConst(cnx);

         FXMain.d = sd.ConvertirFromDtoToDossie(tableau_Dossier.getSelectionModel().getSelectedItem());
          
                err_cliet.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("Modifier_construction.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void Materiel_dossier_home_act(ActionEvent event) throws IOException {
                        err_cliet.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("Materiel_const.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    }


