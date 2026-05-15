package Main;

import ConnexionBD.MySQL;
import Console.AuthConsole;
import DAOImpl.RoleDAOImpl;
import DAOImpl.UtilisateurDAOImpl;
import InterfaceDB.Database;
import Model.Utilisateur;
import ServiceImpl.UtilisateurService;

public class Main {

	public static void main(String[] pheno) {
		initt();
		Database db = new MySQL();
		
		UtilisateurDAOImpl utilisateurDAO = 
				new UtilisateurDAOImpl(db);
		
		UtilisateurService utilisateurService =
				new UtilisateurService(utilisateurDAO);
		
		 AuthConsole authConsole =
	                new AuthConsole(utilisateurService);
		 
		 Utilisateur utilisateurConnecte =
	                authConsole.lancer(); 
		 
		 authConsole.redirectionMenu(utilisateurConnecte);
		
	}

	private static void initt() {
		MySQL mysql = new MySQL();
		RoleDAOImpl roleDAO = new RoleDAOImpl(mysql);
		Initialiseur init = new Initialiseur(roleDAO);
		init.init();
	}
}
