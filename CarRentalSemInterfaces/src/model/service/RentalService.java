package model.service;

import java.time.temporal.ChronoUnit;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;
	
	private BrazilTaxService tax;
	
	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService tax) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.tax = tax;
	}
	
	public void processInvoice(CarRental carRental) {
		long minutes = ChronoUnit.MINUTES.between(carRental.getStart(), carRental.getFinish());
		
		int hours = (int) Math.ceil(minutes / 60.0);
		Double value;
		
		if(hours >= 12) {
			int days = (int)Math.ceil(hours / 24.0);
			value = days * pricePerDay;
		}
		else {
			value = hours * pricePerHour;
		}
		
		carRental.setInvoice(new Invoice(value, tax.tax(value)));
				
	}
	
}
