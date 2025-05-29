package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.RentalService;
import model.service.USATaxService;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel: ");
		System.out.print("Modelo do carro: ");
		String vehicle = sc.nextLine();
		
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");		
		LocalDateTime startTime = LocalDateTime.parse(sc.nextLine(), fmt);

		System.out.print("Entrega (dd/MM/yyyy hh:mm): ");		
		LocalDateTime finishTime = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental carModel = new CarRental(startTime, finishTime, new Vehicle(vehicle));
		
		System.out.print("Entre com o preço por hora: ");
		Double pricePerHour = sc.nextDouble();
		
		System.out.print("Entre com o preço por dia: ");
		Double pricePerDay = sc.nextDouble();
		
		RentalService process = new RentalService(pricePerHour, pricePerDay, new USATaxService());
		
		process.processInvoice(carModel);
		
		System.out.println("Fatura: ");
		
		System.out.printf("Pagamento básico: R$%.2f\n", carModel.getInvoice().getBasicPayment());
		System.out.printf("Imposto: R$%.2f\n", carModel.getInvoice().getTax());
		System.out.printf("Pagamento total: R$%.2f\n", carModel.getInvoice().totalPayment());
	}
	
}
