package DTO;

import java.sql.ResultSet;

public class ProductNProviderDTO {
	
	ResultSet rs;

	public ProductNProviderDTO(ResultSet rs) {
		this.rs = rs;
	}
	
	public ResultSet getResultSet() {
		return this.rs;
	}
	
}
