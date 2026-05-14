package ServiceImpl;

import java.util.List;

import DAOInter.ActiviteDAO;
import Model.Activite;

public class ActiviteService {
	
	private ActiviteDAO activiteDAO;

	public ActiviteService(ActiviteDAO activiteDAO) {

		this.activiteDAO = activiteDAO;

	}

	public void ajouter(Activite a) {

	        if ((a.getNom() == null || a.getNom().isEmpty()) 
	        		&& (a.getDescription() == null || a.getDescription().isEmpty())
	        		&& (a.getEtapes() == null || a.getEtapes().isEmpty() )
	        		&& (a.getRisques() == null || a.getRisques().isEmpty()) 
	        		&& (a.getRevenueMin() >=0 )
	        		&&(	a.getRevenueMax() >= 0)
	        		&& (a.getDisponibilite() >= 0 ) 
	        		&& (a.isAccesInternet() != true || a.isAccesInternet() != false) 
	        		&& (a.getMateriaux() == null || a.getMateriaux().isEmpty())
	        		&& (a.getCapital() >= 0 )
	        		&& (a.getZone().name() == null || a.getZone().name().isEmpty())){
	        	
	            System.out.println("Veillez remplir tous les champs !");
	            return;
	        }

	        activiteDAO.ajouter(a);
	    }

	public void modifier(Activite a) {

		if (a.getId() <= 0) {
			System.out.println("ID invalide !");
			return;
		}

		activiteDAO.modifier(a);
	}

	public void supprimer(int id) {

		if (id <= 0) {
			System.out.println("ID invalide !");
			return;
		}

		activiteDAO.supprimer(id);
	}

	public List<Activite>tousList() {
		return activiteDAO.tousList();
	}

	public Activite lire(int id) {

		if (id <= 0) {
			System.out.println("ID invalide !");
			return null;
		}

		return activiteDAO.lire(id);
	}

}
