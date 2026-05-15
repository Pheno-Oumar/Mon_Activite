package ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAOImpl.ProfilDAO;
import DAOInter.IRecommandationDAO;
import DAOInter.ActiviteDAO;

import Model.Recommandation;
import Model.Profil;
import Model.Activite;
import Model.Competence;

public class RecommandationService {

    private final IRecommandationDAO recommandationDAO;
    private final ProfilDAO profilDAO;
    private final ActiviteDAO activiteDAO;

    // Constructeur
    public RecommandationService(
            IRecommandationDAO recommandationDAO,
            ProfilDAO profilDAO,
            ActiviteDAO activiteDAO) {

        if (recommandationDAO == null || profilDAO == null || activiteDAO == null) {
            throw new IllegalArgumentException("Les DAO sont obligatoires.");
        }

        this.recommandationDAO = recommandationDAO;
        this.profilDAO = profilDAO;
        this.activiteDAO = activiteDAO;
    }

    // =========================
    // GENERER RECOMMANDATIONS
    // =========================
    public List<Recommandation> genererRecommandations(int profilId) {

        if (profilId <= 0) {
            throw new IllegalArgumentException("Profil invalide");
        }

        Profil profil = profilDAO.trouverParId(profilId);
        List<Activite> activites = activiteDAO.tousList();

        List<Recommandation> resultats = new ArrayList<>();

        for (Activite activite : activites) {

            if (estCompatible(profil, activite)) {

                Recommandation r = new Recommandation();
                r.setProfil(profil);
                r.setActivite(activite);
                r.setDateAjout(new Date());

                recommandationDAO.ajouter(r);
                resultats.add(r);
            }
        }

        return resultats;
    }

    // =========================
    // LOGIQUE DE COMPATIBILITÉ
    // =========================
    private boolean estCompatible(Profil profil, Activite activite) {

        // 1. Zone (Ville / Village)
        if (profil.getZone() != activite.getZone()) {
            return false;
        }

        // 2. Capital
        if (profil.getCapital() < activite.getCapital()) {
            return false;
        }

        // 3. Internet
        if (activite.isAccesInternet() && !profil.isAccessInternet()) {
            return false;
        }

        // 4. Compétences (au moins une correspondance)
        boolean competenceOk = false;

        if (profil.getCompetences() != null && activite.getCompetences() != null) {

            for (Competence cp : profil.getCompetences()) {
                for (Competence ca : activite.getCompetences()) {

                    if (cp.getNom().equalsIgnoreCase(ca.getNom())) {
                        competenceOk = true;
                        break;
                    }
                }
                if (competenceOk) break;
            }
        }

        return competenceOk;
    }

    // =========================
    // AFFICHER PAR PROFIL
    // =========================
    public List<Recommandation> afficher(int profilId) {

        if (profilId <= 0) {
            throw new IllegalArgumentException("Profil invalide.");
        }

        return recommandationDAO.afficher(profilId);
    }

    // =========================
    // AJOUT MANUEL
    // =========================
    public void ajouter(Recommandation recommandation) {

        if (recommandation == null
                || recommandation.getActivite() == null
                || recommandation.getProfil() == null
                || recommandation.getDateAjout() == null) {

            throw new IllegalArgumentException("Recommandation invalide.");
        }

        recommandationDAO.ajouter(recommandation);
    }

    // =========================
    // SUPPRESSION
    // =========================
    public void supprimer(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID invalide.");
        }

        recommandationDAO.supprimer(id);
    }
}