package ServiceInter;

import java.util.List;

import Model.Activite;

public interface ActiviteService {
	
	void ajouter(Activite a);

    void modifier(Activite a);

    void supprimer(int id);

    List<Activite> tousList();

    Activite lire(int id);

}
