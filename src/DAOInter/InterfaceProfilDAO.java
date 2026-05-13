package DAOInter;

import Model.Profil;

public interface InterfaceProfilDAO {
	
	void ajouter(Profil profil);
	
	Profil trouverParId(int id);
	
	Profil trouverParUtilisateur(int UtilisateurId);
	
	void modifier(Profil profil);
	
	void supprimer(int id);

}
