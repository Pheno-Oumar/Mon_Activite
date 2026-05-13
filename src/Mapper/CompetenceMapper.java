package Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Competence;

public class CompetenceMapper {

	
	    public static Competence map(ResultSet rs) throws SQLException {
	        return new Competence(
	            rs.getInt("id"), 
	            rs.getString("nom"),
	            rs.getString("description")
	        );
	    }
	}


