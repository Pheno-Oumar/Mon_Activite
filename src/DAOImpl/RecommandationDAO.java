package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import InterfaceDB.Database;
import Model.Recommandation;

public class RecommandationDAO {

    private Database db;

    // Constructeur conseillé
    public RecommandationDAO(Database db) {
        this.db = db;
    }

    public List<Recommandation> afficher(int profilId) {

        List<Recommandation> liste = new ArrayList<>();
        String sql = "SELECT * FROM recommandation WHERE profilId = ?";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Remplacement du ? par l'identifiant du profil
            ps.setInt(1, profilId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Recommandation r = new Recommandation();

                    r.setId(rs.getInt("id"));
                    r.setDateAjout(rs.getDate("dateAjout"));

                    liste.add(r);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }
    public void ajouter(Recommandation r) {

        String sql = "INSERT INTO recommandation(activiteId, profilId, dateAjout) VALUES (?, ?, ?)";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getActivite().getId());
            ps.setInt(2, r.getProfil().getId());
            ps.setDate(3, new java.sql.Date(r.getDateAjout().getTime()));

            ps.executeUpdate();

            System.out.println("Ajout réussi");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {

        String sql = "DELETE FROM recommandation WHERE id = ?";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Suppression réussie");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}