package InterfaceDB;

import java.sql.Connection;

public interface Database {

		public Connection connexion();
		public void deconnexion(Connection conn);
}
