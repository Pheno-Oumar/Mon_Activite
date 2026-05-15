package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import InterfaceDB.Database;
import Model.Activite;
import Model.Profil;
import Model.Recommandation;

public class RecommandationDAO {

    // Dépendance vers la base de données
    private final Database db;

    // Constructeur : injection de dépendance
    public RecommandationDAO(Database db) {
        this.db = db;
    }

    /**
     * Retourne toutes les recommandations d'un profil donné.
     *
     * @param profilId identifiant du profil
     * @return liste des recommandations
     */
    public List<Recommandation> afficher(int profilId) {

        // Liste qui contiendra les recommandations trouvées
        List<Recommandation> liste = new ArrayList<>();

        // Requête SQL : sélectionner les recommandations du profil
        String sql =
                "SELECT * FROM recommandation WHERE profilId = ?";

        try (Connection conn = db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Remplacement du ? par l'identifiant du profil
            ps.setInt(1, profilId);

            // Exécution de la requête
            try (ResultSet rs = ps.executeQuery()) {

                // DAO nécessaires pour charger les objets complets
                ActiviteDAOImpl activiteDAO = new ActiviteDAOImpl(db);
                ProfilDAO profilDAO = new ProfilDAO(db);

                // Parcours des résultats
                while (rs.next()) {

                    // Création de l'objet Recommandation
                    Recommandation recommandation =
                            new Recommandation();

                    // Récupération des champs simples
                    recommandation.setId(
                            rs.getInt("id"));

                    recommandation.setDateAjout(
                            rs.getDate("dateAjout"));

                    // Lecture des clés étrangères
                    int activiteId =
                            rs.getInt("activiteId");

                    int profilIdBD =
                            rs.getInt("profilId");

                    // Chargement des objets complets via leurs DAO
                    Activite activite =
                            activiteDAO.lire(activiteId);

                    Profil profil =
                            profilDAO.trouverParId(profilIdBD);

                    // Association des objets
                    recommandation.setActivite(activite);
                    recommandation.setProfil(profil);

                    // Ajout à la liste finale
                    liste.add(recommandation);
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Erreur lors de l'affichage des recommandations : "
                            + e.getMessage());
        }

        // Retour de la liste des recommandations
        return liste;
    }

    /**
     * Ajoute une nouvelle recommandation dans la base.
     *
     * @param recommandation objet à enregistrer
     */
    public void ajouter(Recommandation recommandation) {

        String sql =
                "INSERT INTO recommandation " +
                "(activiteId, profilId, dateAjout) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = db.connexion();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            // Remplissage des paramètres
            ps.setInt(1,
                    recommandation.getActivite().getId());

            ps.setInt(2,
                    recommandation.getProfil().getId());

            ps.setDate(3,
                    new java.sql.Date(
                            recommandation
                                    .getDateAjout()
                                    .getTime()
                    ));

            // Exécution de l'insertion
            ps.executeUpdate();

            System.out.println(
                    "Recommandation ajoutée avec succès.");

        } catch (SQLException e) {
            System.out.println(
                    "Erreur lors de l'ajout : "
                            + e.getMessage());
        }
    }

    /**
     * Supprime une recommandation à partir de son identifiant.
     *
     * @param id identifiant de la recommandation
     */
    public void supprimer(int id) {

        String sql =
                "DELETE FROM recommandation WHERE id = ?";

        try (Connection conn = db.connexion();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            // Remplacement du ? par l'identifiant
            ps.setInt(1, id);

            // Exécution de la suppression
            ps.executeUpdate();

            System.out.println(
                    "Recommandation supprimée avec succès.");

        } catch (SQLException e) {
            System.out.println(
                    "Erreur lors de la suppression : "
                            + e.getMessage());
        }
    }
}