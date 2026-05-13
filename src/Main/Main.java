package Main;

import ConnexionBD.MySQL;
import DAOImpl.RoleDAOImpl;

public class Main {

	public static void main(String[] pheno) {
		initt();
		
	}

	private static void initt() {
		MySQL mysql = new MySQL();
		RoleDAOImpl roleDAO = new RoleDAOImpl(mysql);
		Initialiseur init = new Initialiseur(roleDAO);
		init.init();
	}
}
