package DAOinterfaces;

import java.util.List;

import DTO.ProduktBatchDTO;
import ExceptionHandling.DALException;

public interface IDAOProduktBatch {
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}