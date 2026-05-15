package Console;

import Model.Utilisateur;

public class ProfilAgentConsole {
	
	private Utilisateur utilisateur;

    public ProfilAgentConsole(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void afficher() {

        System.out.println("\n===== PROFIL UTILISATEUR =====");

        System.out.println("ID : " + utilisateur.getId());
        System.out.println("Nom : " + utilisateur.getNom());
        System.out.println("Prenom : " + utilisateur.getPrenom());
        System.out.println("Telephone : " + utilisateur.getTelephone());
        System.out.println("Role : " + utilisateur.getRole());
    }

}
