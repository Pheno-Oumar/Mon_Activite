


package ServiceInter;

import java.util.List;
import Model.Recommandation;

public interface IRecommandationService {

    // Générer les recommandations pour un profil
    List<Recommandation> genererRecommandations(int profilId);

    // Afficher les recommandations d’un profil
    List<Recommandation> afficherParProfil(int profilId);

    // Ajouter une recommandation
    void ajouterRecommandation(Recommandation r);

    // Supprimer une recommandation
    void supprimerRecommandation(int id);
}