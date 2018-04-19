package DAOinterfaces;
import java.util.List;

import DAO.DALException;
import dto01917.RaavareBatchDTO;

public interface IDAOViewRaavareBatches {

	public interface RaavareBatchDAO {
		RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
		List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
		List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
		void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
		void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
	}


}
