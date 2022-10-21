/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionimmobiliere;

import java.sql.Connection;
import java.sql.Date;
import tn.edu.esprit.entities.*;
import tn.edu.esprit.services.*;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author dell
 */
public class GestionImmobiliere {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Connection cnx = DataSource.getInstance().getConnection();
        //////////test/////////
        Connection cnx2 = DataSource.getInstance().getConnection();
        ServiceTerrainConst st = new ServiceTerrainConst(cnx);
        Date date1 = new Date(2027, 4 - 1, 7);
        /////////////////////////////////dossier///////////////  
        ServiceDossierConst sd = new ServiceDossierConst(cnx);
        Dossier_const dc = new Dossier_const(date1, 2, 1, 3);

        Dossier_const dcc = new Dossier_const(date1, 1, 1, 2);

        Dossier_const dc1 = new Dossier_const(14, date1, 1, 1, 1);

        //   sd.ajouter(dc);    
         sd.modifier(dc1);
        //   sd.supprimer(4);
        //  sd.getAll();
        //  sd.getOne(3);
        //sd.GetMaterielById(1);
        //  sd.affecterMatrielDossier(3, 1);
        // sd.GetMaterielDossier(3);
        //   System.out.println(sd.GetMaterielDossier(3));
        //  sd.affecterMatrielDossier(2 ,2);
//////////////////////////////matiere//////////////////
        ServiceMatierePremConst sm = new ServiceMatierePremConst(cnx);
        Matiere_prem_const mc = new Matiere_prem_const("conteneur");
        Matiere_prem_const mc1 = new Matiere_prem_const(1, "préfabriqué");
        // sm.ajouter(mc);
        // sm.modifier(mc1);
        // sm.supprimer(2);
        //sm.getAll();
        //  sm.getOne(1);

////////////////////////////materiel//////////////////
        ServiceMaterielConst smc = new ServiceMaterielConst(cnx);
        Materiel_const mcc = new Materiel_const("beton mixer", 700);
        Materiel_const mcc1 = new Materiel_const(3, "tracteur", 40);
        //smc.ajouter(mcc);
        //smc.modifier(mcc1);
        //smc.supprimer(3);
        //smc.getAll();
        //smc.getOne(2);
     

    }
}
