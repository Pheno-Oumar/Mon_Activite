package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOInter.ActiviteCompetenceDAO;
import DAOInter.ActiviteDAO;
import DAOInter.CompetenceInterface;
import Enumeration.TypeZone;
import InterfaceDB.Database;
import Mapper.ActiviteMapper;
import Model.Activite;

public class ActiviteDAOImpl implements ActiviteDAO{

	//private CompetenceInterface comp;
	private ActiviteCompetenceDAO activiteCompetence;
	 private Database db;

	  
//Etablir la connexion a la base de donnees
	    public ActiviteDAOImpl(Database db) {
			super();
			this.db = db;
		}


	  @Override
	  public void ajouter(Activite a) {

	        String sql = "INSERT INTO activite (nom, description, etapes, risques, revenuParMin, revenuParMax, "
	                   + "disponibilite, accesInternet, materiaux, capital, zone) VALUES (?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        //comp.trouverTousCompetences();

	        try {
	        	Connection conn = db.connexion();

	            PreparedStatement ps = conn.prepareStatement(sql);

	            ps.setString(1, a.getNom());
	           // ps.setInt(2, a.getUtilisateur().getId());
	            ps.setString(2, a.getDescription());
	            ps.setString(3, a.getEtapes());
	            ps.setString(4, a.getRisques());
	            ps.setDouble(5, a.getRevenueMin());
	            ps.setDouble(6, a.getRevenueMax());
	            ps.setDouble(7, a.getDisponibilite());
	            ps.setBoolean(8, a.isAccesInternet());
	            ps.setString(9, a.getMateriaux());
	            ps.setDouble(10, a.getCapital());
	            ps.setString(11, a.getZone().name()); //permet de transformer typezone en chaine de caratere et l'envoyer dans la base de donnees

	            ps.executeUpdate();
	            
	            activiteCompetence.creer(a);


	            System.out.println("Activité ajoutée avec succès !");

	        } catch (SQLException e) {
	            System.out.println("Erreur ajout: " + e.getMessage());
	        }
	    }

	  
	  @Override

	    public void modifier(Activite a) {

	        String sql = "UPDATE activite SET nom=?, description=?, etapes=?, risques=?, "
	                   + "revenuParMin=?, revenuParMax=?, disponibilite=?, accesInternet=?, materiaux=?, capital=?, zone=? "
	                   + "WHERE id=?";
	        

	        try {
	        	Connection conn = db.connexion();

	            PreparedStatement ps = conn.prepareStatement(sql);

	            ps.setString(1, a.getNom());
	            ps.setString(2, a.getDescription());
	            ps.setString(3, a.getEtapes());
	            ps.setString(4, a.getRisques());
	            ps.setDouble(5, a.getRevenueMin());
	            ps.setDouble(6, a.getRevenueMax());
	            ps.setDouble(7, a.getDisponibilite());
	            ps.setBoolean(8, a.isAccesInternet());
	            ps.setString(9, a.getMateriaux());
	            ps.setDouble(10, a.getCapital());
	            ps.setString(11, a.getZone().name());
	            ps.setInt(12, a.getId());

	            ps.executeUpdate();

	            
	         
	            System.out.println("Activité modifiée !");

	        } catch (SQLException e) {
	            System.out.println("Erreur modification: " + e.getMessage());
	        }
	    }

	   
	   @Override 
	    public void supprimer(int id) {

	        try {
	        	
	            String sql = "DELETE FROM activite WHERE id=?";
	        	
	            Connection conn = db.connexion();

	            PreparedStatement ps = conn.prepareStatement(sql);
	            
	            ps.setInt(1, id);

	            ps.executeUpdate();

	            System.out.println("Activité supprimée !");

	        } catch (SQLException e) {
	            System.out.println("Erreur suppression: " + e.getMessage());
	        }
	    }

	    @Override
	    public List<Activite>tousList()
		 {

	        List<Activite> liste = new ArrayList<>();

	        String sql = "SELECT * FROM activite";

	        try {
	        	Connection conn = db.connexion();

	            Statement st = conn.createStatement();// permet d'envoyer la requete dans la base de donnees
	            ResultSet rs = st.executeQuery(sql);

	            while (rs.next()) {
	                Activite a = ActiviteMapper.map(rs);
	                a.setCompetences(activiteCompetence.lire(a));
	                liste.add(a);
	            }
	            
	           



	        } catch (SQLException e) {
	            System.out.println("Erreur liste: " + e.getMessage());
	        }

	        return liste;
	    }

	    @Override
	    public Activite lire(int id) {

	        String sql = "SELECT * FROM activite WHERE id=?";

	        try {
	        	Connection conn = db.connexion();

	            PreparedStatement ps = conn.prepareStatement(sql);
	            
	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();


	            if (rs.next()) {
	                return ActiviteMapper.map(rs);
	            }
 

	        } catch (SQLException e) {
	            System.out.println("Erreur lecture: " + e.getMessage());
	        }

	        return null;
	    }

}
