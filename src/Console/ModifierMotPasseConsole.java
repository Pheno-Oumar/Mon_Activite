package Console;

import java.util.Scanner;

import Model.Utilisateur;
import ServiceImpl.UtilisateurService;

public class ModifierMotPasseConsole {

	

    private Scanner scanner;
    private Utilisateur utilisateur;
    private UtilisateurService utilisateurService;

    public ModifierMotPasseConsole(Utilisateur utilisateur, UtilisateurService utilisateurService) {

        this.scanner = new Scanner(System.in);
        this.utilisateur = utilisateur;
        this.utilisateurService = utilisateurService;
    }

    public void afficher() {

        System.out.println("===== MODIFIER MOT DE PASSE =====");
        
        System.out.print("Ancien mot de passe : ");
        String ancien = scanner.nextLine();

        System.out.print("Nouveau mot de passe : ");
        String nouveau = scanner.nextLine();


//        utilisateurService.modifierMotDePasse(utilisateur, ancien, nouveau);
    }
}
