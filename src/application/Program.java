package application;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato:");
        System.out.print("Número: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
        System.out.print("Valor do contrato: ");
        double amount = sc.nextDouble();
        System.out.print("Entre com o número de parcelas: ");
        int numberOfInstallments = sc.nextInt();

        Contract contract = new Contract(number, date, amount);

        ContractService service = new ContractService(new PaypalService());

        service.processContract(contract, numberOfInstallments);

        System.out.println("PARCELAS:");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }

        sc.close();
    }
}
