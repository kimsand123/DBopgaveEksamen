package daointerfaces01917;

import java.util.List;

import DAO.DALException;
import DTO.RaavareBatchDTO;

public interface RaavareBatchDAO {
	List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
}

