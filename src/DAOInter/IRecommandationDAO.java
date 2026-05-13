package DAOInter;

import java.util.List;
import Model.Recommandation;

public interface IRecommandationDAO {

    List<Recommandation> afficher(int profilId);

    void ajouter(Recommandation r);

    void supprimer(int id);


    
}