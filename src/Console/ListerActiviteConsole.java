package Console;

import java.util.List;

import ConnexionBD.MySQL;
import DAOImpl.ActiviteDAOImpl;
import DAOInter.ActiviteDAO;
import Model.Activite;
import ServiceImpl.ActiviteService;

public class ListerActiviteConsole {


    public void afficher() {

        System.out.println("===== LISTE DES ACTIVITES =====");
        

        ActiviteDAO dao = new ActiviteDAOImpl(new MySQL());

        ActiviteService service = new ActiviteService(dao);

        List<Activite> liste = service.tousList();

        for (Activite a : liste) {

            System.out.println("----------------------------");

            System.out.println("ID : " + a.getId());
            System.out.println("Nom : " + a.getNom());
            System.out.println("Description : " + a.getDescription());
            System.out.println("Disponibilite : " + a.getDisponibilite());
            System.out.println("Acces internet : " + a.isAccesInternet());
            System.out.println("Etapes : " + a.getEtapes());
            System.out.println("Risques : " + a.getRisques());
            System.out.println("Materiaux : " + a.getMateriaux());
            System.out.println("Capital : " + a.getCapital());
            System.out.println("Revenue Min : " + a.getRevenueMin());
            System.out.println("Revenue Max : " + a.getRevenueMax());
            System.out.println("Zone : " + a.getZone());
        }
    }
    
    
    }

