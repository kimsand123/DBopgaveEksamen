package DAOinterfaces;

import java.util.List;

import DAO.DALException;
import DTO.ReceptDTO;

public interface IDAORecept {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
}
