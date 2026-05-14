package ServiceImpl;


import java.util.List;

import DAOInter.UtilisateurDAO;
import Enumeration.TypeRole;
import Model.Role;
import Model.Utilisateur;
import ServiceInter.IUtilisateurService;

public class UtilisateurService implements IUtilisateurService{

	
	private final UtilisateurDAO utilisateurDAO;
	
	public UtilisateurService(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}
	
	@Override
	public void inscription(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
	if(utilisateur.getNom() == null || utilisateur.getNom().isEmpty()) {
		 System.out.println("Nom obligatoire");
		 return;
		
	}
	
	if(utilisateur.getTelephone() == null || utilisateur.getTelephone().isEmpty() ) {
		 System.out.println("Téléphone obligatoire");
		 return;
	}
	
	if(utilisateur.getMdp() == null || utilisateur.getMdp().length() < 4 ) {
		 System.out.println("Mot de passe trop court");
         return;
	}
	
	Utilisateur existant = utilisateurDAO.trouverParTelephone(utilisateur.getTelephone());
	if(existant !=null) {
		System.out.println("Ce numero existe deja");
		return;
	}
	
	
	  // rôle par défaut = CLIENT
	
	if(utilisateur.getRole() == null) {
		utilisateur.setRole(new Role(1, TypeRole.CLIENT));
	
	}
	
	utilisateurDAO.creer(utilisateur);
	System.out.println("Inscription réussie !");
}
	@Override
	public Utilisateur connexion(String telephone, String mdp) {
		// TODO Auto-generated method stub
		
		Utilisateur u = utilisateurDAO.trouverParTelephone(telephone);
		if(u ==null){
			System.out.println("Utilisateur Introuvable");
			return null;
		}
		if(!u.getMdp().equals(mdp)) {
			System.out.println("Mot de passe incorrect");
            return null;
		}
		
		// Message selon rôle
		
		if(u.getRole() !=null) {
			 switch (u.getRole().getNom()) {

             case ADMIN:
                 System.out.println("Bienvenue Admin");
                 break;

             case AGENT_TERRAIN:
                 System.out.println("Bienvenue Agent Terrain");
                 break;

             case CLIENT:
                 System.out.println("Bienvenue "+u.getPrenom()+" "+u.getNom());
                 break;
         }
     }

		return u;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		Utilisateur existant = utilisateurDAO.trouverParId(utilisateur.getId());
		if(existant == null) {
			System.out.println("Utilisateur introuvable");
			return;
		}
	utilisateurDAO.modifier(utilisateur);
	 System.out.println("Utilisateur modifié avec succès");
	}

	@Override
	public void supprimerUtilisateur(int id) {
		// TODO Auto-generated method stub
		Utilisateur existant = utilisateurDAO.trouverParId(id);
		if (existant == null) {
			System.out.println("Utilisateur introuvable");
			return;
		}
		utilisateurDAO.supprimer(id);
		System.out.println("Utilisateur supprimé avec succès");
	}

	@Override
	public Utilisateur rechercherParId(int id) {
		// TODO Auto-generated method stub
		return utilisateurDAO.trouverParId(id);
	}

	@Override
	
	public List<Utilisateur> afficherTousUtilisateurs() {
		// TODO Auto-generated method stub
		 return utilisateurDAO.trouveTous();
	}

}