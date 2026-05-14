package Console;

import java.util.Scanner;

import ConnexionBD.MySQL;
import DAOImpl.ActiviteDAOImpl;
import DAOInter.ActiviteDAO;
import Enumeration.TypeZone;
import Model.Activite;
import ServiceImpl.ActiviteService;

public class AjouterActiviteConsole {


    private Scanner scanner;

    public AjouterActiviteConsole() {
        scanner = new Scanner(System.in);
    }

    public void afficher() {

        System.out.println("===== AJOUT ACTIVITE =====");

        Activite a = new Activite();

        System.out.print("Nom : ");
        a.setNom(scanner.nextLine());

        System.out.print("Description : ");
        a.setDescription(scanner.nextLine());

        System.out.print("Disponibilite : ");
        a.setDisponibilite(scanner.nextDouble());

        System.out.print("Acces Internet (true/false) : ");
        a.setAccesInternet(scanner.nextBoolean());

        scanner.nextLine();

        System.out.print("Etapes : ");
        a.setEtapes(scanner.nextLine());

        System.out.print("Risques : ");
        a.setRisques(scanner.nextLine());

        System.out.print("Materiaux : ");
        a.setMateriaux(scanner.nextLine());

        System.out.print("Capital : ");
        a.setCapital(scanner.nextDouble());

        System.out.print("Revenue minimum : ");
        a.setRevenueMin(scanner.nextDouble());

        System.out.print("Revenue maximum : ");
        a.setRevenueMax(scanner.nextDouble());

        scanner.nextLine();

        System.out.print("Zone (Ville/Village) : ");
        String zone = scanner.nextLine();

        a.setZone(TypeZone.valueOf(zone));

        // DAO
        ActiviteDAO dao = new ActiviteDAOImpl(new MySQL());

        // Service
        ActiviteService service = new ActiviteService(dao);

        // ajout
        service.ajouter(a);

        System.out.println("Activite ajoutee avec succes !");
    }
    
    
    }


