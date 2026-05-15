package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DAOInter.CompetenceInterface;
import InterfaceDB.Database;
import Model.Competence;

public class CompetenceDAOImpl implements CompetenceInterface {

    // Objet permettant la connexion à la base de données
    private final Database db;

    // Constructeur avec injection de dépendance
    public CompetenceDAOImpl(Database db) {
        this.db = db;
    }

    
    @Override
    public void creerCompetence(Competence comp) {

        // Requête SQL d'insertion
        String sql = "INSERT INTO competence(nom, description) VALUES (?, ?)";

        try (
                // Ouverture de la connexion
                Connection conn = db.connexion();

                // Préparation de la requête SQL
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            // Remplacement des paramètres ?
            ps.setString(1, comp.getNom());
            ps.setString(2, comp.getDescription());

            // Exécution de la requête
            ps.executeUpdate();

            System.out.println("Compétence ajoutée avec succès !");

        } catch (SQLException e) {

            // Affichage des erreurs SQL
            e.printStackTrace();
        }
    }

 
    @Override
    public void modifierCompetence(Competence comp) {

        // Requête SQL de mise à jour
        String sql = "UPDATE competence SET nom=?, description=? WHERE id=?";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            // Remplissage des paramètres
            ps.setString(1, comp.getNom());
            ps.setString(2, comp.getDescription());
            ps.setInt(3, comp.getId());

            // Exécution de la modification
            ps.executeUpdate();

            System.out.println("Compétence modifiée avec succès !");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    
    @Override
    public void supprimerCompetence(int id) {

        String sql = "DELETE FROM competence WHERE id=?";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            // Remplacement du ?
            ps.setInt(1, id);

            // Exécution de la suppression
            ps.executeUpdate();

            System.out.println("Compétence supprimée avec succès !");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

     
    @Override
    public List<Competence> trouverTousCompetences() {

        // Liste qui va contenir les compétences
        List<Competence> liste = new ArrayList<>();

        String sql = "SELECT * FROM competence";

        try (
                Connection conn = db.connexion();
        		//
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {

            // Parcours des résultats SQL
            while (rs.next()) {

                // Création d'un objet Competence
                Competence comp = new Competence(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                );

                // Ajout dans la liste
                liste.add(comp);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return liste;
    }

     
    @Override
    public Optional<Competence> trouverCompetenceParId(int id) {

        String sql = "SELECT * FROM competence WHERE id=?";

        try (
                Connection conn = db.connexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            // Remplacement du paramètre
            ps.setInt(1, id);

            // Exécution de la requête
            ResultSet rs = ps.executeQuery();

            // Vérifie si une ligne existe
            if (rs.next()) {

                Competence comp = new Competence();

                // Récupération des données SQL
                comp.setId(rs.getInt("id"));
                comp.setNom(rs.getString("nom"));
                comp.setDescription(rs.getString("description"));

                return Optional.of(comp);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        // Retourne un Optional vide si aucune compétence trouvée
        return Optional.empty();
    }
}
