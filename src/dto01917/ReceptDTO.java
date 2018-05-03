package dto01917;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Recept Data Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class ReceptDTO {
	/** Recept nr i omraadet 1-99999999 */
	int receptId;
	/** Receptnavn min. 2 max. 20 karakterer */
	String receptNavn;
	/** liste af kompenenter i recepten */
	private HashMap<Integer, ReceptKompDTO> components = null;

	public ReceptDTO(int receptId, String receptNavn) {
		this.receptId = receptId;
		this.receptNavn = receptNavn;
		components = new HashMap<Integer, ReceptKompDTO>(0);
	}

	public void addComponent(ReceptKompDTO comp) {

			components.put(comp.getRaavareId(), comp);
	}
	
	public List<ReceptKompDTO> getComponents()
	{
		return new ArrayList<ReceptKompDTO>(components.values());
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	public String getReceptNavn() {
		return receptNavn;
	}

	public void setReceptNavn(String receptNavn) {
		this.receptNavn = receptNavn;
	}

	public String toString() {
		return receptId + "\t" + receptNavn;
	}

	public void clearComponents() {
		components.clear();		
	}
}
