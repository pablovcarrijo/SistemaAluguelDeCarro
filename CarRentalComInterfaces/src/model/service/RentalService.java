package model.service;

import java.time.temporal.ChronoUnit;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;
	private TaxService tax;
	
	public RentalService(Double pricePerHour, Double pricePerDay, TaxService tax) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.tax = tax;
	}

	
	public void processInvoice(CarRental carRental) {
		long duration = ChronoUnit.HOURS.between(carRental.getStart(), carRental.getFinish());
		Double price;
		int days, hours;
		
		if(duration >= 12) {
			days = (int) Math.ceil(duration / 24.0);
			price = days * pricePerDay;
		}
		else {
			hours = (int) Math.ceil(duration / 60.0);
			price = hours * pricePerHour;
		}
		
		carRental.setInvoice(new Invoice(price, tax.taxCalc(price)));	
		
	}
	
}
