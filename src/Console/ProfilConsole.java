package Console;

import java.util.Scanner;

import Enumeration.TypeZone;
import Model.Profil;
import ServiceImpl.ProfilServiceImpl;

public class ProfilConsole {

    private Scanner scanner;
    private ProfilServiceImpl profilService;

    public ProfilConsole(Scanner scanner,
                         ProfilServiceImpl profilService) {

        this.scanner = scanner;
        this.profilService = profilService;
    }

    public void demarrer() {

        int choix;

        do {

            System.out.println("\n====== MENU PROFIL ======");

            System.out.println("1. Créer profil");
            System.out.println("2. Voir profil par ID");
            System.out.println("3. Voir profil utilisateur");
            System.out.println("4. Modifier profil");
            System.out.println("5. Supprimer profil");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");

            choix = scanner.nextInt();

            switch (choix) {

                case 1:
                    creerProfil();
                    break;

                case 2:
                    afficherProfilParId();
                    break;

                case 3:
                    afficherProfilParUtilisateur();
                    break;

                case 4:
                    modifierProfil();
                    break;

                case 5:
                    supprimerProfil();
                    break;

                case 0:
                    System.out.println("Fin programme.");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);
    }

    public void creerProfil() {

        System.out.println("\n====== CREATION PROFIL ======");

        System.out.print("Disponibilité : ");
        double disponibilite = scanner.nextDouble();

        System.out.print("Accès internet (true/false) : ");
        boolean accesInternet = scanner.nextBoolean();

        System.out.print("Capital : ");
        double capital = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Zone (VILLE/VILLAGE) : ");

        String zoneSaisie =
                scanner.nextLine().toUpperCase();

        TypeZone zone;

        try {

            zone = TypeZone.valueOf(zoneSaisie);

        } catch (IllegalArgumentException e) {

            System.out.println("Zone invalide.");
            return;
        }

        Profil profil = new Profil();

        profil.setDisponibilite(disponibilite);
        profil.setAccessInternet(accesInternet);
        profil.setCapital(capital);
        profil.setZone(zone);

        profilService.creerProfil(profil);
    }

    public void afficherProfilParId() {

        System.out.println("\n====== RECHERCHE PROFIL ======");

        System.out.print("ID profil : ");

        int id = scanner.nextInt();

        Profil profil =
                profilService.obtenirProfilParId(id);

        if (profil == null) {

            System.out.println("Profil introuvable.");
            return;
        }

        afficherProfil(profil);
    }

    public void afficherProfilParUtilisateur() {

        System.out.println("\n====== PROFIL UTILISATEUR ======");

        System.out.print("ID utilisateur : ");

        int utilisateurId = scanner.nextInt();

        Profil profil =
                profilService.obtenirProfilParUtilisateur(utilisateurId);

        if (profil == null) {

            System.out.println("Profil introuvable.");
            return;
        }

        afficherProfil(profil);
    }

    public void modifierProfil() {

        System.out.println("\n====== MODIFICATION PROFIL ======");

        System.out.print("ID profil : ");

        int id = scanner.nextInt();

        Profil profil =
                profilService.obtenirProfilParId(id);

        if (profil == null) {

            System.out.println("Profil introuvable.");
            return;
        }

        System.out.print("Nouvelle disponibilité : ");

        double disponibilite = scanner.nextDouble();

        System.out.print("Accès internet (true/false) : ");

        boolean accesInternet = scanner.nextBoolean();

        System.out.print("Nouveau capital : ");

        double capital = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Nouvelle zone (VILLE/VILLAGE) : ");

        String zoneSaisie =
                scanner.nextLine().toUpperCase();

        TypeZone zone;

        try {

            zone = TypeZone.valueOf(zoneSaisie);

        } catch (IllegalArgumentException e) {

            System.out.println("Zone invalide.");
            return;
        }

        profil.setDisponibilite(disponibilite);
        profil.setAccessInternet(accesInternet);
        profil.setCapital(capital);
        profil.setZone(zone);

        profilService.modifierProfil(profil);
    }

    public void supprimerProfil() {

        System.out.println("\n====== SUPPRESSION PROFIL ======");

        System.out.print("ID profil : ");

        int id = scanner.nextInt();

        profilService.supprimerProfil(id);
    }

    private void afficherProfil(Profil profil) {

        System.out.println("\n====== INFORMATIONS PROFIL ======");

        System.out.println("ID : "
                + profil.getId());

        System.out.println("Disponibilité : "
                + profil.getDisponibilite());

        System.out.println("Accès internet : "
                + profil.isAccessInternet());

        System.out.println("Capital : "
                + profil.getCapital());

        System.out.println("Zone : "
                + profil.getZone());
    }
}