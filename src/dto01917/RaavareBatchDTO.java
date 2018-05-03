package dto01917;

public class RaavareBatchDTO
{
	int rbId;                     // i omraadet 1-99999999
	int raavareId;             // i omraadet 1-99999999
	double maengde;             // kan vaere negativ 
	String leverandoer;   		// kan vaere lige saa lang den vil

	public RaavareBatchDTO(int rbId, int raavareId, double maengde, String leverandoer)
	{
		this.rbId = rbId;
		this.raavareId = raavareId;
		this.maengde = maengde;
		this.leverandoer = leverandoer;
		
	}
	
	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public int getRaavareId() { return raavareId; }
	public void setRaavareId(int raavareId) { this.raavareId = raavareId; }
	public double getMaengde() { return maengde; }
	public void setMaengde(double maengde) { this.maengde = maengde; }
	public String getLeverandoer(){return leverandoer;}
	public void setLeverandoer(String leverandoer) {this.leverandoer=leverandoer;}
	public String toString() { 
		return rbId + "\t" + raavareId +"\t" + maengde; 
	}
}
