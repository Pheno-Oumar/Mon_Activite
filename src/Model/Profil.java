package Model;

import java.util.ArrayList;
import java.util.List;

import Enumeration.TypeZone;

public class Profil {

	private int id;
	private double disponibilite;
	private boolean accessInternet;
	private double capital;
	private TypeZone zone;
	private List<Competence> competences;
	private Utilisateur utilisateur;
	
	public Profil() {
		this.competences = new ArrayList<>();
	}
	
	
	public Profil( int id, double disponibilite, double capital, TypeZone zone, List<Competence> competences, Utilisateur utilisateur ) {
		 
		this.id = id;
		this.disponibilite = disponibilite ;
		this.capital = capital;
		this.zone = zone;
		this.competences = competences;
		this.utilisateur = utilisateur;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(double disponibilite) {
		this.disponibilite = disponibilite;
	}

	public boolean isAccessInternet() {
		return accessInternet;
	}

	public void setAccessInternet(boolean accessInternet) {
		this.accessInternet = accessInternet;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public TypeZone getZone() {
		return zone;
	}

	public void setZone(TypeZone zone) {
		this.zone = zone;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
