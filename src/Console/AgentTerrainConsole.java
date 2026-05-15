package Console;

import java.util.Scanner;

import ConnexionBD.MySQL;
import DAOImpl.CompetenceDAOImpl;
import Model.Utilisateur;


public class AgentTerrainConsole extends Utilisateur {

	private Utilisateur utilisateur;
	private Scanner scanner;

	public AgentTerrainConsole() {

        scanner = new Scanner(System.in);
		
	}
	
	 public void afficherMenu() {

	        int choix;

	        do {

	            System.out.println("\n===== MENU AGENT TERRAIN =====");

	            System.out.println("1 - Ajouter activité");
	            System.out.println("2 - Modifier activité");
	            System.out.println("3 - Supprimer activité");
	            System.out.println("4 - Lister activités");
	            System.out.println("5 - Liste des clients");
	            System.out.println("6 - Voir profil");
	            System.out.println("7 - Modifier mot de passe");
	            System.out.println("0 - Quitter");

	            System.out.print("Votre choix : ");
	            choix = scanner.nextInt();

	            switch (choix) {

	                case 1:
	                    new AjouterActiviteConsole(new CompetenceDAOImpl(new MySQL())).afficher();
	                    break;

	                case 2:
	                    new ModifierActiviteConsole().afficher();
	                    break;

	                case 3:
	                    new SupprimerActiviteConsole().afficher();
	                    break;

	                    
	                case 4:
	                    new ListerActiviteConsole().afficher();
	                    break;

	                case 5:
	                    new ProfilAgentConsole(utilisateur).afficher();
	                    break;

//	                case 6:
//	                    new ListeUtilisateurConsole().afficher();
//	                    break;

//	                case 7:
//	                    new ModifierMotPasseConsole().afficher();
//	                    break;

	                case 0:
	                    System.out.println("Au revoir !");
	                    break;

	                default:
	                    System.out.println("Choix invalide !");
	            }

	        } while (choix != 0);
	    }
	
	
	
	
}
