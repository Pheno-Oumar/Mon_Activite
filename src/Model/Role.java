package Model;

import Enumeration.TypeRole;

public class Role {
    private int id;
    private TypeRole nom;

    public Role() {}

    public Role(int id, TypeRole nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeRole getNom() { return nom; }
    public void setNom(TypeRole nom) { this.nom = nom; }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", nom=" + (nom != null ? nom.name() : "null") + '}';
    }
}