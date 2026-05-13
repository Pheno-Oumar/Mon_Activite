package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recommandation {
	private int id;
	private Activite activite;
	private Profil profil;
	private Date dateAjout;
	
	public Recommandation() {
		// TODO Auto-generated constructor stub
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Activite getActivite() {
		return activite;
	}


	public void setActivite(Activite activite) {
		this.activite = activite;
	}


	public Profil getProfil() {
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
	}


	public Date getDateAjout() {
		return dateAjout;
	}


	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}


	public Activite creer() {
		Activite a =new Activite();
		 
		 a.setNom("hjghds");
		 Competence com1 = new Competence();
		 com1.setNom("hjvhd");
		 com1.setDescription("hbdjh");
		 List<Competence> competences = new ArrayList<>();
		 competences.add(com1);
		 
		
		 a.setCompetences(competences);
		 return a;
	}





	 
}
