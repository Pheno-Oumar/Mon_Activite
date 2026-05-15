package Main;

import java.util.Optional;

import DAOInter.RoleDAO;
import Enumeration.TypeRole;
import Model.Role;
import Model.Utilisateur;
import ServiceImpl.AdminServiceImpl;
import ServiceInter.IUtilisateurService;

public class Initialiseur {

	private final RoleDAO roleDAO;
	private final IUtilisateurService utilisateurService;

	public Initialiseur(RoleDAO roleDAO,IUtilisateurService utilisateurService) {
		this.roleDAO = roleDAO;
		this.utilisateurService =  utilisateurService;
	}

	public void init() {
		initRoles();
		initAdmin();
	}

	private void initAdmin() {
		Utilisateur utilisateur  = this.utilisateurService.trouverParTelephone("77777777");
		if(utilisateur == null) {
			Utilisateur newutilisateur = new Utilisateur();
			newutilisateur.setNom("AON");
			newutilisateur.setPrenom("Yatt");
			newutilisateur.setTelephone("77777777");
			newutilisateur.setMdp("12345678");
			utilisateurService.inscription(newutilisateur);
		}
	}

	private void initRoles() {
		for (TypeRole typeRole : TypeRole.values()) {

			Optional<Role> roletrouve = this.roleDAO.findByNom(typeRole);

			if (roletrouve.isEmpty()) {
				Role role = new Role();
				role.setNom(typeRole);
				roleDAO.save(role);
			}
		}
	}
}
