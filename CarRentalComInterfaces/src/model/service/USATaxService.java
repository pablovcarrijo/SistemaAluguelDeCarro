package model.service;

public class USATaxService implements TaxService{

	public Double taxCalc(Double amount) {
		if(amount >= 100) {
			return amount * 0.2;
		}
		return amount * 0.15;
	}
	
}
