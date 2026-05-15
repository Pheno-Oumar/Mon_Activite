package ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import DAOImpl.ProfilDAO;
import DAOInter.ActiviteDAO;
import DAOInter.IRecommandationDAO;
import Model.Activite;
import Model.Competence;
import Model.Profil;
import Model.Recommandation;

public class RecommandationService {

    // Dépendance vers la couche DAO
    private final IRecommandationDAO recommandationDAO;
    
    private final ActiviteDAO activiteDAO;
    private final ProfilDAO profilDAO;
    
    /**
     * Constructeur.
     * Reçoit le DAO par injection de dépendance.
     */
    public RecommandationService(IRecommandationDAO recommandationDAO,ActiviteDAO activiteDAO,ProfilDAO profilDAO) {

        if (recommandationDAO == null) {
            throw new IllegalArgumentException("Le DAO est obligatoire.");
        }

        this.recommandationDAO = recommandationDAO;
        this.activiteDAO = activiteDAO;
        this.profilDAO = profilDAO;
    }

 // =========================
 // GENERER RECOMMANDATIONS
 // =========================
 public List<Recommandation> genererRecommandations(int profilId) {

     if (profilId <= 0) {
         throw new IllegalArgumentException("Profil invalide");
     }

     // 1. Charger le profil
     Profil profil = profilDAO.trouverParId(profilId);

     if (profil == null) {
         throw new IllegalArgumentException("Profil introuvable.");
     }

     // 2. Charger toutes les activités
     List<Activite> activites = activiteDAO.tousList();

     // 3. Liste des recommandations générées
     List<Recommandation> resultats = new ArrayList<>();

     // 4. Parcourir toutes les activités
     for (Activite activite : activites) {

         // 5. Vérifier la compatibilité :
         //    - même zone (Ville / Village)
         //    - accès Internet compatible
         //    - au moins une compétence commune
         if (estCompatible(profil, activite)) {

             // 6. Créer la recommandation
             Recommandation recommandation = new Recommandation();
             recommandation.setProfil(profil);
             recommandation.setActivite(activite);
             recommandation.setDateAjout(new Date(profilId));

             // 7. Sauvegarder en base de données
             recommandationDAO.ajouter(recommandation);

             // 8. Ajouter à la liste des résultats
             resultats.add(recommandation);
         }
     }

     // 9. Retourner les recommandations générées
     return resultats;
 }


 // =========================
 // VERIFIER LA COMPATIBILITE
 // =========================
 private boolean estCompatible(Profil profil, Activite activite) {

     // 1. Même zone (Ville / Village)
     if (profil.getZone() != activite.getZone()) {
         return false;
     }

     // 2. Si l'activité nécessite Internet,
     //    le profil doit avoir accès à Internet
     if (activite.isAccesInternet() && !profil.isAccessInternet()) {
         return false;
     }

     // 3. Au moins une compétence commune
     if (profil.getCompetences() == null
             || activite.getCompetences() == null) {
         return false;
     }

     for (Competence competenceProfil : profil.getCompetences()) {
         for (Competence competenceActivite : activite.getCompetences()) {

             if (competenceProfil.getNom()
                     .equalsIgnoreCase(competenceActivite.getNom())) {
                 return true;
             }
         }
     }

     // Aucune compétence commune trouvée
     return false;
 }
}