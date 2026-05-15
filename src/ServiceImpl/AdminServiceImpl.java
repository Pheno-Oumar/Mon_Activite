package ServiceImpl;

import DAOInter.RoleDAO;
import DAOInter.UtilisateurDAO;
import Enumeration.TypeRole;
import Model.Role;
import Model.Utilisateur;

public class AdminServiceImpl extends UtilisateurService{

	public AdminServiceImpl(UtilisateurDAO utilisateurDAO, RoleDAO roleDAO) {
		super(utilisateurDAO, roleDAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inscription(Utilisateur utilisateur) {
		// Validation basique
		if (utilisateur == null)
			return;

		if (utilisateur.getNom() == null || utilisateur.getNom().isBlank()) {
			System.out.println("Nom obligatoire");
			return;
		}
		if (utilisateur.getTelephone() == null || utilisateur.getTelephone().isBlank()) {
			System.out.println("Téléphone obligatoire");
			return;
		}

		// Nettoyage du téléphone (supprime les espaces accidentels)
		utilisateur.setTelephone(utilisateur.getTelephone().trim());

		Utilisateur existant = utilisateurDAO.trouverParTelephone(utilisateur.getTelephone());
		if (existant != null) {
			System.out.println("Ce numéro existe déjà");
			return;
		}

		Role role = this.roleDAO.findByNom(TypeRole.ADMIN).get();
		utilisateur.setRole(role);
		
		utilisateurDAO.creer(utilisateur);
	}

}
