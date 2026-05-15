package ServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import InterfaceDB.Database;
import Model.Competence;
import ServiceInter.CompetenceServiceInter;

public class CompetenceService implements CompetenceServiceInter {

    // Objet permettant la connexion à la base de données
    private final Database db;

    // Constructeur
    public CompetenceService(Database db) {
        this.db = db;
    }

    public void creerCompetence(Competence comp) {

        String sql = "INSERT INTO competence(nom, description) VALUES (?, ?)";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, comp.getNom());
            ps.setString(2, comp.getDescription());

            ps.executeUpdate();

            System.out.println("Compétence ajoutée avec succès !");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void modifierCompetence(Competence comp) {

        String sql = "UPDATE competence SET nom=?, description=? WHERE id=?";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, comp.getNom());
            ps.setString(2, comp.getDescription());
            ps.setInt(3, comp.getId());

            ps.executeUpdate();

            System.out.println("Compétence modifiée avec succès !");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void supprimerCompetence(int id) {

        String sql = "DELETE FROM competence WHERE id=?";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Compétence supprimée avec succès !");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Competence> trouverTousCompetences() {

        List<Competence> liste = new ArrayList<>();

        String sql = "SELECT * FROM competence";

        try (
                Connection conn = db.connexion();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                Competence comp = new Competence(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                );

                liste.add(comp);
           }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return liste;
    }

    public Optional<Competence> trouverCompetenceParId(int id) {

        String sql = "SELECT * FROM competence WHERE id=?";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Competence comp = new Competence();

                comp.setId(rs.getInt("id"));
                comp.setNom(rs.getString("nom"));
                comp.setDescription(rs.getString("description"));

                return Optional.of(comp);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Optional.empty();
    }
}