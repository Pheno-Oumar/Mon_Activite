package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAOInter.UtilisateurDAO;
import Enumeration.TypeRole;
import InterfaceDB.Database;
import Model.Role;
import Model.Utilisateur;

public class UtilisateurDAOImpl	 implements UtilisateurDAO {

    private final Database db;

    public UtilisateurDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public void creer(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur(nom, prenom, telephone, mdp, roleId) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = db.connexion();
            PreparedStatement pr = conn.prepareStatement(sql)
        ) {

            pr.setString(1, utilisateur.getNom());
            pr.setString(2, utilisateur.getPrenom());
            pr.setString(3, utilisateur.getTelephone());
            pr.setString(4, utilisateur.getMdp());
            pr.setInt(5, utilisateur.getRole().getId());

            int rows = pr.executeUpdate();

            if (rows > 0) {
                System.out.println("Utilisateur créé avec succès !");
            } else {
                System.out.println("Aucun utilisateur créé.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur création utilisateur : " + e.getMessage());
        }
    }

    @Override
    public Utilisateur trouverParId(int id) {
        String sql = "SELECT * FROM utilisateur WHERE id = ?";

        try (
            Connection conn = db.connexion();
            PreparedStatement pr = conn.prepareStatement(sql)
        ) {

            pr.setInt(1, id);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
            }

        } catch (SQLException e) {
            System.err.println("Erreur recherche utilisateur : " + e.getMessage());
        }

        return null;
    }
    
    

    // Méthode supplémentaire pour la connexion
    public Utilisateur trouverParTelephone(String telephone) {
    	String sql = """
    	        SELECT u.*, r.nom as role_nom 
            FROM utilisateur u 
            LEFT JOIN role r ON u.roleId = r.id 
    	        WHERE u.telephone = ?
    	        """;

        try (
            Connection conn = db.connexion();
            PreparedStatement pr = conn.prepareStatement(sql)
        ) {

            pr.setString(1, telephone);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
                
            }

        } catch (SQLException e) {
            System.err.println("Erreur recherche téléphone : " + e.getMessage());
        }

        return null;
    }

    @Override
    public void modifier(Utilisateur utilisateur) {
        String sql = """
                UPDATE utilisateur
                SET nom = ?, prenom = ?, telephone = ?, mdp = ?, roleId = ?
                WHERE id = ?
                """;

        try (
            Connection conn = db.connexion();
            PreparedStatement pr = conn.prepareStatement(sql)
        ) {

            pr.setString(1, utilisateur.getNom());
            pr.setString(2, utilisateur.getPrenom());
            pr.setString(3, utilisateur.getTelephone());
            pr.setString(4, utilisateur.getMdp());
            pr.setInt(5, utilisateur.getRole().getId());
            pr.setInt(6, utilisateur.getId());

            int rows = pr.executeUpdate();

            if (rows > 0) {
                System.out.println("Utilisateur modifié avec succès.");
            } else {
                System.out.println("Aucune modification effectuée.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur modification : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM utilisateur WHERE id = ?";

        try (
            Connection conn = db.connexion();
            PreparedStatement pr = conn.prepareStatement(sql)
        ) {

            pr.setInt(1, id);

            int rows = pr.executeUpdate();

            if (rows > 0) {
                System.out.println("Utilisateur supprimé avec succès.");
            } else {
                System.out.println("Aucun utilisateur supprimé.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur suppression : " + e.getMessage());
        }
    }

    @Override
    public List<Utilisateur> trouveTous() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        String sql = "SELECT * FROM utilisateur ORDER BY nom";

        try (
            Connection conn = db.connexion();
            PreparedStatement pr = conn.prepareStatement(sql)
        ) {

            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                utilisateurs.add(mapResultSetToUtilisateur(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erreur récupération utilisateurs : " + e.getMessage());
        }

        return utilisateurs;
    }

    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur u = new Utilisateur();
        u.setId(rs.getInt("id"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        u.setTelephone(rs.getString("telephone"));
        u.setMdp(rs.getString("mdp"));

        // === CORRECTION ICI : "roleId" au lieu de "role_id" ===
        // On utilise roleId car c'est le nom de la colonne dans ta table 'utilisateur'
        // ou l'alias que tu as défini dans ta requête SQL (r.id as roleId)
        int roleId = rs.getInt("roleId"); 

        if (!rs.wasNull()) {
            String roleNomStr = rs.getString("role_nom");
            TypeRole typeRole = null;
            
            if (roleNomStr != null) {
                try {
                    typeRole = TypeRole.valueOf(roleNomStr.trim().toUpperCase());
                } catch (Exception e) {
                    System.out.println("Erreur conversion rôle: " + roleNomStr);
                }
            }
            
            Role role = new Role(roleId, typeRole);
            u.setRole(role);
        }

        return u;
    }
}