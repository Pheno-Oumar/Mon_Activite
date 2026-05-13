package DAOInter;

import java.util.List;

import Model.Activite;


public interface ActiviteDAO {
	
	void ajouter(Activite a);
	
	void modifier(Activite a);

	void supprimer(int id);
	
	List<Activite>tousList();
	
	Activite lire (int id);


}
