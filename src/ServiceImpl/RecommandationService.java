package ServiceImpl;

import java.util.List;

import DAOImpl.RecommandationDAO;
import Model.Recommandation;

public class RecommandationService {

    // Dépendance vers la couche DAO
    private final RecommandationDAO recommandationDAO;

    /**
     * Constructeur.
     * Reçoit le DAO par injection de dépendance.
     */
    public RecommandationService(RecommandationDAO recommandationDAO) {

        if (recommandationDAO == null) {
            throw new IllegalArgumentException("Le DAO est obligatoire.");
        }

        this.recommandationDAO = recommandationDAO;
    }

    /**
     * Retourne toutes les recommandations d'un profil.
     *
     * @param profilId identifiant du profil
     * @return liste des recommandations
     */
    public List<Recommandation> afficher(int profilId) {

        if (profilId <= 0) {
            throw new IllegalArgumentException("Profil invalide.");
        }

        return recommandationDAO.afficher(profilId);
    }

    /**
     * Ajoute une nouvelle recommandation.
     *
     * @param recommandation objet à enregistrer
     */
    public void ajouter(Recommandation recommandation) {

        if (recommandation == null) {
            throw new IllegalArgumentException("La recommandation est obligatoire.");
        }

        if (recommandation.getActivite() == null) {
            throw new IllegalArgumentException("L'activité est obligatoire.");
        }

        if (recommandation.getProfil() == null) {
            throw new IllegalArgumentException("Le profil est obligatoire.");
        }

        if (recommandation.getDateAjout() == null) {
            throw new IllegalArgumentException("La date d'ajout est obligatoire.");
        }

        recommandationDAO.ajouter(recommandation);
    }

    /**
     * Supprime une recommandation par son identifiant.
     *
     * @param id identifiant de la recommandation
     */
    public void supprimer(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID invalide.");
        }

        recommandationDAO.supprimer(id);
    }
}