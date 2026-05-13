package Main;

import java.util.Optional;

import DAOInter.RoleDAO;
import Enumeration.TypeRole;
import Model.Role;

public class Initialiseur {

	private final RoleDAO roleDAO;

	public Initialiseur(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void init() {
		initRoles();
		// initAdmin();
	}

	private void initRoles() {
		for (TypeRole typeRole : TypeRole.values()) {

			Optional<Role> roletrouve = this.roleDAO.findByNom(typeRole);

			if (roletrouve.isEmpty()) {
				Role role = new Role();
				role.setNom(typeRole);
				roleDAO.save(role);
			}
		}
	}
}
