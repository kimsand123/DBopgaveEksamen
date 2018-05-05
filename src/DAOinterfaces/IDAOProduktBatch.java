package DAOinterfaces;

import java.util.List;

import DAO.DALException;
import DTO.ProduktBatchDTO;

public interface IDAOProduktBatch {
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}