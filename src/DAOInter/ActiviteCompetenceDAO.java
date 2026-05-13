package DAOInter;

import Model.Activite;
import Model.Competence;
import java.util.List;
public interface ActiviteCompetenceDAO {

    public void creer(Activite activite);
    public List<Competence> lire(Activite activite);

}
