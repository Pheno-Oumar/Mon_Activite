package Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ConnexionBD.MySQL;
import DAOImpl.ActiviteCompetenceDAOImpl;
import DAOImpl.ActiviteDAOImpl;
import DAOInter.ActiviteCompetenceDAO;
import DAOInter.ActiviteDAO;
import DAOInter.CompetenceInterface;
import Enumeration.TypeZone;
import Model.Activite;
import Model.Competence;
import Model.Utilisateur;
import ServiceImpl.ActiviteService;

public class AjouterActiviteConsole {

    private final CompetenceInterface comp;

    private Scanner scanner;

    public AjouterActiviteConsole(CompetenceInterface comp) {
    	
        this.comp = comp;
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

        System.out.print("Acces Internet (Oui/Non) : ");
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

        List<Competence> list = comp.trouverTousCompetences();
        
        for (Competence c : list) {
            System.out.println(c.getId() + " - " + c.getNom());
        }

        System.out.print("Choisir compétences (ex: 1,2,3) : ");
        String input = scanner.nextLine();

        List<Competence> selection = new ArrayList<>();

        String[] ids = input.split(",");

        for (String id : ids) {

            Competence c = new Competence();
            c.setId(Integer.parseInt(id.trim()));

            selection.add(c);
        }
        
//        System.out.print("Choisir ID compétence : ");
//        int idComp = scanner.nextInt();
        
        ActiviteDAO dao = new ActiviteDAOImpl(new MySQL());

        ActiviteService service = new ActiviteService(dao);

        service.ajouter(a);

    }
    
    
    }


 