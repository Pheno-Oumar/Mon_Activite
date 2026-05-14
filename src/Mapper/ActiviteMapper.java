package Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import Enumeration.TypeZone;
import Model.Activite;

public class ActiviteMapper {

	public static Activite map(ResultSet rs) throws SQLException {

        Activite a = new Activite();

        a.setId(rs.getInt("id"));
        a.setNom(rs.getString("nom"));
        a.setDescription(rs.getString("description"));
        a.setEtapes(rs.getString("etapes"));
        a.setRisques(rs.getString("risques"));
        a.setRevenueMin(rs.getDouble("revenuParMin"));
        a.setRevenueMax(rs.getDouble("revenuParMax"));
        a.setDisponibilite(rs.getDouble("disponibilite"));
        a.setAccesInternet(rs.getBoolean("accesInternet"));
        a.setMateriaux(rs.getString("materiaux"));
        a.setCapital(rs.getDouble("capital"));

        String zone = rs.getString("zone");
        if (zone != null) {
            a.setZone(TypeZone.valueOf(zone));
        }

        return a;
    }


}
