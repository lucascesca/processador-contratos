package services;

import entities.Contract;
import entities.Installment;

import java.time.LocalDate;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months) {
        double basicValue = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);
            double interestValue = onlinePaymentService.interest(basicValue, i);
            double feeValue = onlinePaymentService.paymentFee(basicValue + interestValue);
            contract.getInstallments().add(new Installment(dueDate, basicValue + interestValue + feeValue));
        }
    }
}
