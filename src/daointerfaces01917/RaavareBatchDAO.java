package daointerfaces01917;

import java.util.List;

import DAO.DALException;
import dto01917.RaavareBatchDTO;

public interface RaavareBatchDAO {
	List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
}

