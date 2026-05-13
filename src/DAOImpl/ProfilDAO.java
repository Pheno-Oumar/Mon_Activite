package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAOInter.InterfaceProfilDAO;
import Enumeration.TypeZone;
import InterfaceDB.Database;
import Mapper.CompetenceMapper;
import Model.Competence;
import Model.Profil;

public class ProfilDAO implements InterfaceProfilDAO {

    private final Database db;

    public ProfilDAO(Database db) {
        this.db = db;
    }

    @Override
    public void ajouter(Profil profil) {

        String sqlProfil =
                "INSERT INTO profil(utilisateurId, disponibilite, accesInternet, capital, zone) VALUES(?,?,?,?,?)";

        String sqlCompetence =
                "SELECT id FROM competence WHERE nom=?";

        String sqlProfilCompetence =
                "INSERT INTO profil_competence(profilId, competenceId) VALUES(?,?)";

        try (Connection conn = db.connexion();

             PreparedStatement psProfil =
                     conn.prepareStatement(sqlProfil, PreparedStatement.RETURN_GENERATED_KEYS);

             PreparedStatement psCompetence =
                     conn.prepareStatement(sqlCompetence);

             PreparedStatement psProfilCompetence =
                     conn.prepareStatement(sqlProfilCompetence)) {

            psProfil.setInt(1, profil.getUtilisateur().getId());
            psProfil.setDouble(2, profil.getDisponibilite());
            psProfil.setBoolean(3, profil.isAccessInternet());
            psProfil.setDouble(4, profil.getCapital());
            psProfil.setString(5, profil.getZone().name());

            psProfil.executeUpdate();

            ResultSet generatedKeys = psProfil.getGeneratedKeys();

            int profilId = 0;

            if (generatedKeys.next()) {
                profilId = generatedKeys.getInt(1);
            }

            for (Competence competence : profil.getCompetences()) {

                psCompetence.setString(1, competence.getNom());

                ResultSet rsCompetence = psCompetence.executeQuery();

                int competenceId = 0;

                if (rsCompetence.next()) {
                    competenceId = rsCompetence.getInt("id");
                }

                psProfilCompetence.setInt(1, profilId);
                psProfilCompetence.setInt(2, competenceId);

                psProfilCompetence.executeUpdate();
            }

            System.out.println("Profil ajouté avec succès.");

        } catch (SQLException e) {

            System.out.println("Erreur ajout profil : " + e.getMessage());
        }
    }

    @Override
    public Profil trouverParId(int id) {

        String sqlProfil =
                "SELECT * FROM profil WHERE id=?";

        String sqlCompetences =
                "SELECT c.nom " +
                "FROM competence c " +
                "JOIN profil_competence pc " +
                "ON c.id = pc.competenceId " +
                "WHERE pc.profilId=?";

        Profil profil = null;

        try (Connection conn = db.connexion();

             PreparedStatement psProfil =
                     conn.prepareStatement(sqlProfil);

             PreparedStatement psCompetences =
                     conn.prepareStatement(sqlCompetences)) {

            psProfil.setInt(1, id);

            ResultSet rsProfil = psProfil.executeQuery();

            if (rsProfil.next()) {

                profil = new Profil();

                profil.setId(rsProfil.getInt("id"));
                profil.setDisponibilite(
                        rsProfil.getDouble("disponibilite"));

                profil.setAccessInternet(
                        rsProfil.getBoolean("accesInternet"));

                profil.setCapital(
                        rsProfil.getDouble("capital"));

                profil.setZone(
                        TypeZone.valueOf(
                                rsProfil.getString("zone")
                        )
                );

                psCompetences.setInt(1, id);

                ResultSet rsCompetences =
                        psCompetences.executeQuery();

                List<Competence> competences = new ArrayList<>();

             while (rsCompetences.next()) {
               
                 Competence c = CompetenceMapper.map(rsCompetences);
                 
                 competences.add(c);
             }

                profil.setCompetences(competences);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erreur recherche profil : "
                            + e.getMessage());
        }

        return profil;
    }

    @Override
    public void modifier(Profil profil) {

        String sqlUpdateProfil =
                "UPDATE profil SET disponibilite=?, accesInternet=?, capital=?, zone=? WHERE id=?";

        String sqlDeleteCompetences =
                "DELETE FROM profil_competence WHERE profilId=?";

        String sqlCompetence =
                "SELECT id FROM competence WHERE nom=?";

        String sqlInsertCompetence =
                "INSERT INTO profil_competence(profilId, competenceId) VALUES(?,?)";

        try (Connection conn = db.connexion();

             PreparedStatement psUpdate =
                     conn.prepareStatement(sqlUpdateProfil);

             PreparedStatement psDelete =
                     conn.prepareStatement(sqlDeleteCompetences);

             PreparedStatement psCompetence =
                     conn.prepareStatement(sqlCompetence);

             PreparedStatement psInsert =
                     conn.prepareStatement(sqlInsertCompetence)) {

            psUpdate.setDouble(1, profil.getDisponibilite());
            psUpdate.setBoolean(2, profil.isAccessInternet());
            psUpdate.setDouble(3, profil.getCapital());
            psUpdate.setString(4, profil.getZone().name());
            psUpdate.setInt(5, profil.getId());

            psUpdate.executeUpdate();

            psDelete.setInt(1, profil.getId());

            psDelete.executeUpdate();

            for (Competence competence : profil.getCompetences()) {

                psCompetence.setString(1, competence.getNom());

                ResultSet rs = psCompetence.executeQuery();

                int competenceId = 0;

                if (rs.next()) {
                    competenceId = rs.getInt("id");
                }

                psInsert.setInt(1, profil.getId());
                psInsert.setInt(2, competenceId);

                psInsert.executeUpdate();
            }

            System.out.println("Profil modifié.");

        } catch (SQLException e) {

            System.out.println(
                    "Erreur modification : "
                            + e.getMessage());
        }
    }

      @Override
    public void supprimer(int id) {

        String sqlDeleteProfilCompetence =
                "DELETE FROM profil_competence WHERE profilId=?";

        String sqlDeleteProfil =
                "DELETE FROM profil WHERE id=?";

        try (Connection conn = db.connexion();

             PreparedStatement psDeletePC =
                     conn.prepareStatement(sqlDeleteProfilCompetence);

             PreparedStatement psDeleteProfil =
                     conn.prepareStatement(sqlDeleteProfil)) {

            psDeletePC.setInt(1, id);

            psDeletePC.executeUpdate();

            psDeleteProfil.setInt(1, id);

            psDeleteProfil.executeUpdate();

            System.out.println("Profil supprimé.");

        } catch (SQLException e) {

            System.out.println(
                    "Erreur suppression : "
                            + e.getMessage());
        }
    }

    @Override
    public Profil trouverParUtilisateur(int utilisateurId) {

        String sql =
                "SELECT id FROM profil WHERE utilisateurId=?";

        Profil profil = null;

        try (Connection conn = db.connexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, utilisateurId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int profilId = rs.getInt("id");

                profil = trouverParId(profilId);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erreur recherche utilisateur : "
                            + e.getMessage());
        }

        return profil;
    }
}