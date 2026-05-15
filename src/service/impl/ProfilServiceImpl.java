package service.impl;

import DAOImpl.ProfilDAO;
import Model.Profil;
import ServiceInter.ProfilServiceInt;

public class ProfilServiceImpl implements ProfilServiceInt {

    private ProfilDAO profilDAO;

    public ProfilServiceImpl(ProfilDAO profilDAO) {
        this.profilDAO = profilDAO;
    }

    public void creerProfil(Profil profil) {

        if (profil.getDisponibilite() <= 0) {
            System.out.println("Disponibilité invalide.");
            return;
        }

        if (profil.getCapital() < 0) {
            System.out.println("Capital invalide.");
            return;
        }

        if (profil.getZone() == null) {

            System.out.println("Zone obligatoire.");
            return;
        }

        profilDAO.ajouter(profil);

        System.out.println("Profil créé avec succès.");
    }

    public Profil obtenirProfilParId(int id) {

        if (id <= 0) {
            System.out.println("ID invalide.");
            return null;
        }

        return profilDAO.trouverParId(id);
    }

    public Profil obtenirProfilParUtilisateur(int utilisateurId) {

        if (utilisateurId <= 0) {
            System.out.println("Utilisateur invalide.");
            return null;
        }

        return profilDAO.trouverParUtilisateur(utilisateurId);
    }

    public void modifierProfil(Profil profil) {

        if (profil == null) {
            System.out.println("Profil introuvable.");
            return;
        }

        profilDAO.modifier(profil);

        System.out.println("Profil modifié.");
    }

    public void supprimerProfil(int id) {

        if (id <= 0) {
            System.out.println("ID invalide.");
            return;
        }

        profilDAO.supprimer(id);

        System.out.println("Profil supprimé.");
    }
}