package Console;


import java.util.Scanner;
import Model.Utilisateur;
import ServiceInter.ProfilServiceInt;

public class ClientConsole {

    private final Scanner sc;
    private final ProfilServiceInt profilService;

    public ClientConsole(Scanner sc,
    		    ProfilServiceInt  profilService) {

        this.sc = sc;
        this.profilService = profilService;
    }

    public void menuClient(Utilisateur utilisateurConnecte) {

        int choix;

        do {

            System.out.println("\n===== MENU CLIENT =====");
            System.out.println("1. Compléter profil");
            System.out.println("2. Voir mon profil");
            System.out.println("0. Déconnexion");

            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {

                case 1:
                    System.out.println(
                        "Compléter profil..."
                    );
                    break;

                case 2:
                    System.out.println(
                        "Voir profil..."
                    );
                    break;

                case 0:
                    System.out.println(
                        "Déconnexion..."
                    );
                    break;

                default:
                    System.out.println(
                        "Choix invalide"
                    );
            }

        } while (choix != 0);
    }
}