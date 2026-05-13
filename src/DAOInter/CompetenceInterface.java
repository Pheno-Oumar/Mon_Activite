package DAOInter;

import java.util.List;
import java.util.Optional;

import Model.Competence;

public interface CompetenceInterface {
	
    void creerCompetence(Competence comp);

    void modifierCompetence(Competence comp);

    void supprimerCompetence(int id);

    List<Competence> trouverTousCompetences();

    Optional<Competence> trouverCompetenceParId(int id);

	 

	 

}

