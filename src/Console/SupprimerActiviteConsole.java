package Console;

import java.util.Scanner;

import ConnexionBD.MySQL;
import DAOImpl.ActiviteDAOImpl;
import DAOInter.ActiviteDAO;
import ServiceImpl.ActiviteService;

public class SupprimerActiviteConsole {
	
	private Scanner scanner;

    public SupprimerActiviteConsole() {
        scanner = new Scanner(System.in);
    }


	public void afficher() {

        System.out.println("===== SUPPRIMER ACTIVITE =====");
        
        System.out.print("Entrer l'ID de l'activite : ");

        int id = scanner.nextInt();

        ActiviteDAO dao = new ActiviteDAOImpl(new MySQL());

        ActiviteService service = new ActiviteService(dao);

        service.supprimer(id);

        System.out.println("Activite supprimee avec succes !");
    }
	
	
    }

