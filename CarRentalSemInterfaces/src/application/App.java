package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String modelCar = sc.nextLine();
		
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		
		System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		CarRental carRental = new CarRental(start, finish, new Vehicle(modelCar));
		
		System.out.print("Entre com o preço por hora: ");
		Double pricePerHour = sc.nextDouble();
		
		System.out.print("Entre com o preço por dia: ");
		Double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(carRental);
		
		System.out.println("Fatura: ");
		System.out.printf("Pagamento básico: R$%.2f\n", carRental.getInvoice().getBasicPayment());
		System.out.printf("Tax: R$%.2f\n", carRental.getInvoice().getTax());
		System.out.printf("Total: R$%.2f\n", carRental.getInvoice().totalPayment());		
		
		sc.close();
	}

}
