package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAO.MySQLReceptDAO;
import DTO.RaavareDTO;
import DTO.ReceptDTO;
import ExceptionHandling.DALException;
import tools.KeyValue;

/**
 * Added by Frederik on 02-05-2018 19:50:10
 */
public class ReceptController {

	public static KeyValue<Integer, String> DEJ = new KeyValue<Integer, String>(1, "dej");
	public static KeyValue<Integer, String> TOMAT = new KeyValue<Integer, String>(2, "tomat");
	public static KeyValue<Integer, String> OST = new KeyValue<Integer, String>(5, "ost");
	public static KeyValue<Integer, String> SKINKE = new KeyValue<Integer, String>(6, "skinke");
	public static KeyValue<Integer, String> CHAMPIGNON = new KeyValue<Integer, String>(7, "champignon");

	MySQLReceptDAO dao = null;

	public ReceptController() {
		dao = new MySQLReceptDAO();
	}

	public void updateRecept(ReceptDTO recept) {
		try {
			dao.updateRecept(recept);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	public RaavareDTO getRaavare(int id) throws DALException {

		for (RaavareDTO item : this.getRaavareList()) {

			if (item.getRaavareId() == id)
				return item;
		}

		return null;

	}

	public List<RaavareDTO> getRaavareList() throws DALException {

		return dao.getRaavareList();
	}

	public List<ReceptDTO> getRecepter() {

		try {
			return dao.getReceptList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return new ArrayList<ReceptDTO>(0);
	}

	public ReceptDTO getRecept(int id) {
		for (ReceptDTO item : this.getRecepter()) {
			if (item.getReceptId() == id)
				return item;
		}

		return null;
	}
}
