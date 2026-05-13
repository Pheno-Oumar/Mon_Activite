package DAOInter;

import java.util.Optional;

import Enumeration.TypeRole;
import Model.Role;

public interface RoleDAO {
	public Optional<Role> lire(int roleId);

	public Optional<Role> findByNom(TypeRole typeRole);

	public void save(Role role);
}
