package controllers;

import java.util.ArrayList;
import java.util.List;

import DAO.DALException;
import daoimpl01917.MySQLReceptDAO;
import dto01917.ReceptDTO;

/**
 * Added by Frederik on 02-05-2018 19:50:10
 */
public class ReceptController {

	public List<ReceptDTO> getRecepter() {

		MySQLReceptDAO dao = new MySQLReceptDAO();

		try {
			return dao.getReceptList();
		} catch (DALException e) {			
			e.printStackTrace();
		}

		return new ArrayList<ReceptDTO>(0);
	}
}
