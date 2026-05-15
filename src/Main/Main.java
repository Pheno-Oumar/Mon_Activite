package Main;

import ConnexionBD.MySQL;
import Console.AgentTerrainConsole;
import Console.AuthConsole;
import DAOImpl.ProfilDAO;
import DAOImpl.RoleDAOImpl;
import DAOImpl.UtilisateurDAOImpl;
import InterfaceDB.Database;
import ServiceImpl.ProfilServiceImpl;
import ServiceImpl.UtilisateurService;
import ServiceInter.ProfilServiceInt;

public class Main {
    
    public static void main(String[] args) {
        initt();
        
//        AgentTerrainConsole console = new AgentTerrainConsole();
//        console.afficherMenu();
	
        
        Database db = new MySQL();
        
        // DAO
        UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl(db);
        ProfilDAO profilDAO = new ProfilDAO(db);

        // Services
        UtilisateurService utilisateurService = new UtilisateurService(utilisateurDAO);
        ProfilServiceInt profilService = new ProfilServiceImpl(profilDAO);

     

        // Console auth
        AuthConsole authConsole = new AuthConsole(utilisateurService, profilService);

        // Lancer auth
       authConsole.lancer();

        // Redirection menu → UN SEUL PARAMÈTRE maintenant
    }

    private static void initt() {
        MySQL mysql = new MySQL();
        RoleDAOImpl roleDAO = new RoleDAOImpl(mysql);
        Initialiseur init = new Initialiseur(roleDAO);
        init.init();
    }
}