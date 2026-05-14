package ServiceInter;

import Model.Profil;

public interface ProfilServiceInt {
	
	    void creerProfil(Profil profil);

	    Profil obtenirProfilParId(int id);

	    Profil obtenirProfilParUtilisateur(int utilisateurId);

	    void modifierProfil(Profil profil);

	    void supprimerProfil(int id);
	}

