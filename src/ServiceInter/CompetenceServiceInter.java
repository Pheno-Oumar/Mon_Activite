package ServiceInter;

import java.util.List;
import java.util.Optional;

import Model.Competence;

public interface CompetenceServiceInter {

	
	 void creerCompetence(Competence comp);

	    void modifierCompetence(Competence comp);

	    void supprimerCompetence(int id);

	    List<Competence> trouverTousCompetences();

	    Optional<Competence> trouverCompetenceParId(int id);

}
