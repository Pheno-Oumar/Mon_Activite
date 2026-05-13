package Model;

import java.util.ArrayList;
import java.util.List;

import Enumeration.TypeZone;

public class Activite {

	private int id;
	private String nom;
	private String description;
	private double disponibilite;
	private boolean accesInternet;
	private String etapes;
	private String risques;
	private String materiaux;
	private double capital;
	private List<Competence> competences;
	private TypeZone zone;
	private double revenueMin;
	private double revenueMax;

	public Activite() {
		this.competences = new ArrayList<>();
	}
	
	/*public Activite(String nom, String description ) {
		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
		
	}*/
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(double disponibilite) {
		this.disponibilite = disponibilite;
	}
	
	

	public boolean isAccesInternet() {
		return accesInternet;
	}

	public void setAccesInternet(boolean accesInternet) {
		this.accesInternet = accesInternet;
	}

	public String getEtapes() {
		return etapes;
	}

	public void setEtapes(String etapes) {
		this.etapes = etapes;
	}

	public String getRisques() {
		return risques;
	}

	public void setRisques(String risques) {
		this.risques = risques;
	}

	public String getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(String materiaux) {
		this.materiaux = materiaux;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public TypeZone getZone() {
		return zone;
	}

	public void setZone(TypeZone zone) {
		this.zone = zone;
	}

	public double getRevenueMin() {
		return revenueMin;
	}

	public void setRevenueMin(double revenueMin) {
		this.revenueMin = revenueMin;
	}

	public double getRevenueMax() {
		return revenueMax;
	}

	public void setRevenueMax(double revenueMax) {
		this.revenueMax = revenueMax;
	}


	

}
