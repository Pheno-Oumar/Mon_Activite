package Console;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import DAOImpl.RoleDAOImpl;
import DAOImpl.UtilisateurDAOImpl;
import Enumeration.TypeRole;
import InterfaceDB.Database;
import Model.Role;
import Model.Utilisateur;

public class AdminConsole {

	private final UtilisateurDAOImpl utilisateurDAO;
	
	private final Scanner scanner;
	
	private final RoleDAOImpl roleDAO ;
	
	public AdminConsole(Database db) {
		
		this.utilisateurDAO = new UtilisateurDAOImpl(db);
		
		this.scanner = new Scanner(System.in);
		
		this.roleDAO = new RoleDAOImpl(db);
	}
	 
		public void menuAdmin() {
			
			int choix;
			
				do {
					
					System.out.println("\n=========== MENU ADMIN ===========");
					
					System.out.println("1. Ajouter agent terrain");
					System.out.println("2. Afficher utilisateurs");
					System.out.println("3. Modifier agent terrain");
					System.out.println("4. Supprimer utilisateur");
					System.out.println("5. Rechercher utilisateur");
					System.out.println("0. Quitter");
					
					System.out.println("Choix : ");
					
					choix = scanner.nextInt();
					scanner.nextLine();
					
						switch (choix) {
						
						case 1:
							ajouterAgentTerrain();
							break;
							
						 case 2:
			                    afficherUtilisateurs();
			                    break;

			                case 3:
			                    modifierAgentTerrain();
			                    break;

			                case 4:
			                    supprimerUtilisateur();
			                    break;

			                case 5:
			                    rechercherUtilisateur();
			                    break;

			                case 0:
			                    System.out.println("Fermeture...");
			                    break;

			                default:
			                    System.out.println("Choix invalide.");
			            }	
					}while (choix != 0);
				}
					private void ajouterAgentTerrain() {
						
						System.out.println("\n=== AJOUT AGENT TERRAIN ===");
						
						System.out.println("Nom :");
						String nom = scanner.nextLine();
						
						System.out.println("Prénom :");
						String prenom = scanner.nextLine();
						
						System.out.println("Téléphone :");
						String telephone = scanner.nextLine();
						
						System.out.println("Mot de passe :");
						String mdp = scanner.nextLine();
						
						Role role = new Role();
						
						Optional<Role> optionalRole = roleDAO.findByNom(TypeRole.AGENT_TERRAIN);
						
						if (optionalRole.isEmpty()) {

					        System.out.println(
					                "Le rôle AGENT_TERRAIN n'existe pas."
					        );

					        return;
					    }
						
						 role = optionalRole.get();
						
						Utilisateur utilisateur = new Utilisateur();
						
						utilisateur.setNom(nom);
						utilisateur.setPrenom(prenom);
						utilisateur.setTelephone(telephone);
						utilisateur.setMdp(mdp);
						utilisateur.setRole(role);
						
						utilisateurDAO.creer(utilisateur);
						
						System.out.println("Agent terrain ajouté.");
						
					}
					
					private void afficherUtilisateurs() {

				        System.out.println("\n=== LISTE UTILISATEURS ===");

				        List<Utilisateur> utilisateurs =
				                utilisateurDAO.trouveTous();

				        if (utilisateurs.isEmpty()) {

				            System.out.println("Aucun utilisateur.");
				            return;
				        }

				        for (Utilisateur utilisateur : utilisateurs) {

				            System.out.println("--------------------------------");

				            System.out.println("ID : "
				                    + utilisateur.getId());

				            System.out.println("Nom : "
				                    + utilisateur.getNom());

				            System.out.println("Prénom : "
				                    + utilisateur.getPrenom());

				            System.out.println("Téléphone : "
				                    + utilisateur.getTelephone());

				            System.out.println("Rôle : "
				                    + utilisateur.getRole().getNom());
				        }
				    }

					private void modifierAgentTerrain() {

					    System.out.print("ID agent terrain : ");

					    int id = scanner.nextInt();
					    scanner.nextLine();

					    Utilisateur utilisateur =
					            utilisateurDAO.trouverParId(id);

					    if (utilisateur == null) {

					        System.out.println("Utilisateur introuvable.");
					        return;
					    }

					    if (utilisateur.getRole().getNom()
					            != TypeRole.AGENT_TERRAIN) {

					        System.out.println(
					                "Modification autorisée uniquement pour les agents terrain."
					        );

					        return;
					    }

					    System.out.print("Nouveau nom : ");
					    utilisateur.setNom(scanner.nextLine());

					    System.out.print("Nouveau prénom : ");
					    utilisateur.setPrenom(scanner.nextLine());

					    System.out.print("Nouveau téléphone : ");
					    utilisateur.setTelephone(scanner.nextLine());

					    System.out.print("Nouveau mot de passe : ");
					    utilisateur.setMdp(scanner.nextLine());

					    utilisateurDAO.modifier(utilisateur);

					    System.out.println("Agent terrain modifié.");
					}
				   
				    	private void rechercherUtilisateur() {

				        System.out.print("ID utilisateur : ");

				        int id = scanner.nextInt();
				        scanner.nextLine();

				        Utilisateur utilisateur =
				                utilisateurDAO.trouverParId(id);

				        if (utilisateur == null) {

				            System.out.println("Utilisateur introuvable.");
				            return;
				        }

				        System.out.println("\n===== UTILISATEUR =====");

				        System.out.println("ID : "
				                + utilisateur.getId());

				        System.out.println("Nom : "
				                + utilisateur.getNom());

				        System.out.println("Prénom : "
				                + utilisateur.getPrenom());

				        System.out.println("Téléphone : "
				                + utilisateur.getTelephone());

				        System.out.println("Rôle : "
				                + utilisateur.getRole().getNom());
				    }
				    	
				    	private void supprimerUtilisateur() {

				    	    System.out.print("ID utilisateur : ");

				    	    int id = scanner.nextInt();
				    	    scanner.nextLine();

				    	    Utilisateur utilisateur =
				    	            utilisateurDAO.trouverParId(id);

				    	    if (utilisateur == null) {

				    	        System.out.println("Utilisateur introuvable.");
				    	        return;
				    	    }
				    	    
				    	    if (utilisateur.getRole().getNom()
				    	            == TypeRole.ADMIN) {

				    	        System.out.println(
				    	                "Impossible de supprimer l'administrateur."
				    	        );

				    	        return;
				    	    }

				    	    utilisateurDAO.supprimer(id);

				    	    System.out.println("Utilisateur supprimé.");
				    	}

	
		}
		


