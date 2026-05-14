package ServiceImpl;

import java.util.List;

import java.util.Optional;

import DAOImpl.CompetenceDAOImpl;
import Model.Competence;

public class CompetenceService {

    private final CompetenceDAOImpl competenceDAO;

    public CompetenceService(CompetenceDAOImpl competenceDAO) {
        this.competenceDAO = competenceDAO;
    }

    // =========================
    // AJOUT
    // =========================
    public Competence creerCompetence(Competence comp) {

        if (comp == null) {
            System.out.println("❌ Erreur : compétence null");
            return null;
        }

        if (comp.getNom() == null || comp.getNom().trim().isEmpty()) {
            System.out.println("❌ Erreur : le nom est obligatoire");
            return null;
        }

        competenceDAO.creerCompetence(comp);
        System.out.println("✅ Compétence ajoutée avec succès");

        return comp;
    }

    // =========================
    // MODIFICATION
    // =========================
    public Optional<Competence> modifierCompetence(Competence comp) {

        if (comp == null) {
            System.out.println("❌ Erreur : compétence null");
            return Optional.empty();
        }

        if (comp.getId() <= 0) {
            System.out.println("❌ Erreur : ID invalide");
            return Optional.empty();
        }

        Optional<Competence> existante =
                competenceDAO.trouverCompetenceParId(comp.getId());

        if (existante.isEmpty()) {
            System.out.println("❌ Erreur : compétence introuvable");
            return Optional.empty();
        }

        competenceDAO.modifierCompetence(comp);
        System.out.println("✅ Compétence modifiée avec succès");

        return Optional.of(comp);
    }

    // =========================
    // SUPPRESSION
    // =========================
    public boolean supprimerCompetence(int id) {

        if (id <= 0) {
            System.out.println("❌ Erreur : ID invalide");
            return false;
        }

        Optional<Competence> existante =
                competenceDAO.trouverCompetenceParId(id);

        if (existante.isEmpty()) {
            System.out.println("❌ Erreur : compétence inexistante");
            return false;
        }

        competenceDAO.supprimerCompetence(id);
        System.out.println("✅ Compétence supprimée avec succès");

        return true;
    }

    // =========================
    // LISTE
    // =========================
    public List<Competence> obtenirToutesCompetences() {

        List<Competence> liste =
                competenceDAO.trouverTousCompetences();

        if (liste.isEmpty()) {
            System.out.println("⚠️ Aucune compétence trouvée");
        }

        return liste;
    }

    // =========================
    // RECHERCHE PAR ID
    // =========================
    public Optional<Competence> obtenirCompetenceParId(int id) {

        if (id <= 0) {
            System.out.println("❌ Erreur : ID invalide");
            return Optional.empty();
        }

        Optional<Competence> comp =
                competenceDAO.trouverCompetenceParId(id);

        if (comp.isEmpty()) {
            System.out.println("❌ Aucune compétence trouvée");
        }

        return comp;
    }
}