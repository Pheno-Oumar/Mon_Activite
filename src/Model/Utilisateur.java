package Model;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String mdp;
    private Role role;

    public Utilisateur() {}

    // Constructeur pour l'inscription (sans ID, l'ID est auto-incrémenté)
    public Utilisateur(String nom, String prenom, String telephone, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mdp = mdp;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}