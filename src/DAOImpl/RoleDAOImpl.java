package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import DAOInter.RoleDAO;
import Enumeration.TypeRole;
import InterfaceDB.Database;
import Model.Role;

public class RoleDAOImpl implements RoleDAO{

	 private final Database DB;

	    public RoleDAOImpl(Database DB) {
	        this.DB = DB;
	    }

	    @Override
	    public Optional<Role> lire(int roleId) {
	        String query = "SELECT * FROM role WHERE id = ?";

	        try (Connection conn = this.DB.connexion(); PreparedStatement ptmt = conn.prepareStatement(query)) {

	            ptmt.setInt(1, roleId);

	            try (ResultSet rs = ptmt.executeQuery()) {

	                if (rs.next()) {
	                    Role role = new Role();
	                    role.setId(rs.getInt("id"));
	                    role.setNom(TypeRole.valueOf(rs.getString("nom")));
	                    return Optional.of(role);
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Erreur SQL : " + e.getMessage());
	        }
	        return Optional.empty();
	    }

	    @Override
	    public Optional<Role> findByNom(TypeRole typeRole) {
	        String query = "SELECT * FROM role WHERE nom = ?";

	        try (Connection conn = this.DB.connexion(); PreparedStatement ptmt = conn.prepareStatement(query)) {

	            ptmt.setString(1, typeRole.name());

	            try (ResultSet rs = ptmt.executeQuery()) {

	                if (rs.next()) {
	                    Role role = new Role();
	                    role.setId(rs.getInt("id"));
	                    role.setNom(TypeRole.valueOf(rs.getString("nom")));
	                    return Optional.of(role);
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Erreur SQL : " + e.getMessage());
	        }
	        return Optional.empty();
	    }

	    @Override
	    public void save(Role role) {
	        String query = "INSERT INTO role(nom) VALUES(?)";

	        try (Connection conn = this.DB.connexion(); PreparedStatement ptmt = conn.prepareStatement(query)) {

	            ptmt.setString(1, role.getNom().name());
	            ptmt.executeUpdate();

	        } catch (SQLException e) {
	            System.out.println("SQL error " + e.getMessage());
	        }
	    }

}
