package Main;

import ConnexionBD.MySQL;
import DAOImpl.RoleDAOImpl;
import InterfaceDB.Database;
import DAOInter.RoleDAO;

public class Main {

	public static void main(String[] pheno) {
		initt();
		
	}

	private static void initt() {
		Database mysql = new MySQL();
		RoleDAO roleDAO = new RoleDAOImpl(mysql);
		Initialiseur init = new Initialiseur(roleDAO);
		init.init();
	}
}
