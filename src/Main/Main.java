package Main;

import ConnexionBD.MySQL;
import DAOInter.*;
import Console.AgentTerrainConsole;
import Console.AuthConsole;
import DAOImpl.ProfilDAO;
import DAOImpl.RoleDAOImpl;
import DAOImpl.UtilisateurDAOImpl;
import InterfaceDB.Database;
import ServiceInter.IUtilisateurService;
import ServiceInter.ProfilServiceInt;
import ServiceImpl.*;

public class Main {
    
    public static void main(String[] args) {
        initt();
        
//        AgentTerrainConsole console = new AgentTerrainConsole();
//        console.afficherMenu();
	
        
        Database db = new MySQL();
        
        // DAO
        UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl(db);
        RoleDAO roleDAO = new RoleDAOImpl(db);
        ProfilDAO profilDAO = new ProfilDAO(db);

        // Services
        UtilisateurService utilisateurService = new ClientServiceImpl(utilisateurDAO,roleDAO);
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
        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl(mysql);
        IUtilisateurService utilisateurService = new AdminServiceImpl(utilisateurDAO, roleDAO);
        Initialiseur init = new Initialiseur(roleDAO,utilisateurService);
        init.init();
    }
}