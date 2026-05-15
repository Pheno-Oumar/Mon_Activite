package Console;

import java.util.Scanner;

import Enumeration.TypeRole;
import Model.Role;
import Model.Utilisateur;
import ServiceImpl.UtilisateurService;
import ServiceInter.IUtilisateurService;
import ServiceInter.ProfilServiceInt;

public class AuthConsole {
	private final IUtilisateurService utilisateurService;
	private final Scanner scanner;
	private ProfilServiceInt profilService;

	public AuthConsole(UtilisateurService utilisateurService, ProfilServiceInt profilService) {

		this.utilisateurService = utilisateurService;
		this.profilService = profilService;
		this.scanner = new Scanner(System.in);
	}

	public void lancer() {
		Utilisateur utilisateurConnecte = null;
		while (utilisateurConnecte == null) {
			System.out.println("===== AUTHENTIFICATION =====");
			System.out.println("1. Inscription");
			System.out.println("2. Connexion");
			System.out.println("0. Quitter");

			System.out.println("Votre choix :");
			int choixAuth = scanner.nextInt();
			scanner.nextLine();

			switch (choixAuth) {

			case 1:

				inscription();
				break;

			case 2:

				 connexion();
				break;

			case 0:

				System.out.println("Au revoir");
				System.exit(0);
				break;

			default:
				System.out.println("Choix invalide.");

			}

		}
	}

	private void inscription() {

		System.out.println("\n===== INSCRIPTION =====");

		String nom;

		do {
			System.out.println("Nom: ");
			nom = scanner.nextLine().trim();
			if (nom.isEmpty()) {
				System.out.println("Nom obligatoire !");
			}
		} while (nom.isEmpty());

		String prenom;
		do {
			System.out.println("Prénom: ");
			prenom = scanner.nextLine().trim();
			if (prenom.isEmpty()) {
				System.out.println("Prénom obligatoire !");
			}
		} while (prenom.isEmpty());

		String telephone;
		do {
			System.out.println("Téléphone: ");
			telephone = scanner.nextLine().trim();
			if (telephone.isEmpty()) {
				System.out.println("Téléphone obligatoire !");
			}
		} while (telephone.isEmpty());

		String mdp;
		do {
			System.out.println("Mot de passe: ");
			mdp = scanner.nextLine().trim();
			if (mdp.isEmpty()) {
				System.out.println("Mot de passe obligatoire !");
			} else if (mdp.length() < 4) {
				System.out.println("Mot de passe trop court (minimum 4 caractères)");
			}

		} while (mdp.isEmpty() || mdp.length() < 4);

		// Création utilisateur
		Utilisateur utilisateur = new Utilisateur(nom, prenom, telephone, mdp);

		// ====================== RÔLE CLIENT PAR DÉFAUT ======================
		Role roleClient = new Role();
		roleClient.setId(1); // ← Change cet ID selon ta base
		roleClient.setNom(TypeRole.CLIENT);

		utilisateur.setRole(roleClient);
		// ===================================================================

		try {
			utilisateurService.inscription(utilisateur);
			System.out.println("✅ Inscription réussie ! Vous êtes maintenant un CLIENT.");
		} catch (Exception e) {
			System.out.println("❌ Erreur création utilisateur : " + e.getMessage());
		}

	}

	private void connexion() {
		System.out.println("\n===== CONNEXION =====");
		System.out.println("Téléphone: ");
		String phone = scanner.nextLine();

		System.out.println("Mot de passe: ");
		String pass = scanner.nextLine();

		Utilisateur utilisateur = utilisateurService.connexion(phone, pass);

		if (utilisateur == null) {
			System.out.println("Connexion echouée");
		} else {
			System.out.println("Connexion reussi");

			redirectionMenu(utilisateur);
		}

	}

	public void redirectionMenu(Utilisateur utilisateur) {

		if (utilisateur == null) {
			System.out.println("Erreur : Aucun utilisateur connecté !");
			return;
		}

		if (utilisateur.getRole() == null) {
			System.out.println("Erreur : Rôle non défini pour cet utilisateur !");
			return;
		}

		TypeRole role = utilisateur.getRole().getNom();

		switch (role) {
		case CLIENT:
			new ClientConsole(scanner, profilService).menuClient(utilisateur);
			break;

		/*
		 * case ADMIN: // new AdminConsole(...).afficherMenu(); break;
		 * 
		 * case AGENT_TERRAIN: // new AgentTerrainConsole(...).afficherMenu(); break;
		 */

		default:
			System.out.println("Rôle non géré pour le moment : " + role);
		}
	}
}
