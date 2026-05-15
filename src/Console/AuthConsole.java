package Console;

import java.util.Scanner;

import Enumeration.TypeRole;
import Model.Utilisateur;
import ServiceInter.IUtilisateurService;

public class AuthConsole {
private final IUtilisateurService utilisateurService;
private final Scanner scanner;

public AuthConsole(IUtilisateurService utilisateurService) {
	this.utilisateurService = utilisateurService;
	this.scanner = new Scanner(System.in);
	}

public Utilisateur lancer() {
	Utilisateur utilisateurConnecte = null ;
	while(utilisateurConnecte == null){
		System.out.println("\\n===== AUTHENTIFICATION =====");
		System.out.println("1. Inscription");
		 System.out.println("2. Connexion");
         System.out.println("0. Quitter");
         
         System.out.println("Votre choix :");
         int choixAuth = scanner.nextInt();
         scanner.nextLine();
         
         
         switch(choixAuth) {
         
         case 1:
        	 
        	 inscription();
        	 break;
        	 
         case 2:
        	 
        	 utilisateurConnecte = connexion();
        	 break;
        	 
         case 0:
        	 
        	 System.out.println("Au revoir");
        	 System.exit(0);
        	 break;
        	
        default:
        	System.out.println("Choix invalide.");
        	 
         
         }
         
         
		
		
	}
	return utilisateurConnecte;
}

private void inscription() {
	
	System.out.println("\n===== INSCRIPTION =====");
	
	String nom;
	
	do {
		System.out.println("Nom: ");
		nom = scanner.nextLine().trim();
		if(nom.isEmpty()) {
			System.out.println("Nom obligatoire !");
		}
	} while (nom.isEmpty());
	
	
	String prenom;
	do {
		System.out.println("Prénom: ");
		prenom = scanner.nextLine().trim();
		if(prenom.isEmpty()) {
			System.out.println("Prénom obligatoire !");
		}
	} while (prenom.isEmpty());
	
	String telephone;
	do {
		System.out.println("Téléphone: ");
		telephone = scanner.nextLine().trim();
		if(telephone.isEmpty()) {
			System.out.println("Téléphone obligatoire !");
		}
	} while (telephone.isEmpty());
	
	
	String mdp;
	do {
		System.out.println("Mot de passe: ");
		mdp = scanner.nextLine().trim();
		if(mdp.isEmpty()) {
			System.out.println("Mot de passe obligatoire !");
		}
		else if(mdp.length() < 4) {
			System.out.println( "Mot de passe trop court (minimum 4 caractères)");
		}
		
		
	} while (mdp.isEmpty() || mdp.length() < 4);
	
	Utilisateur utilisateur = new Utilisateur(
			nom,
			prenom,
			telephone,
			mdp
			);
	
	utilisateurService.inscription(utilisateur);
			
}


private Utilisateur connexion() {
	System.out.println("\n===== CONNEXION =====");
	System.out.println("Téléphone: ");
	String phone = scanner.nextLine();
	
	System.out.println("Mot de passe: ");
	String pass = scanner.nextLine();
	
	Utilisateur utilisateur = utilisateurService.connexion(phone, pass);
	
	if (connexion() == null) {
		System.out.println("Connexion echouée");
		return null;
	} 
		System.out.println("Connexion reussi");
		
		return utilisateur;

}

public void RedirectionMenu(Utilisateur utilisateur) {
	TypeRole role = utilisateur.getRole().getNom();
	
	switch (role) {
	case CLIENT:
		new ClientConsole(utilisateur).menuClient();
		break;
	case ADMIN:
		new AdminConsole(utilisateur).AfficherMenu();
	case AGENT_TERRAIN:
		new AgentTerrainConsole(utilisateur).afficherMenu();

	default:
		System.out.println("Role inconnu");
	}
}
}
