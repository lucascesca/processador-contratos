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
            double intValue = onlinePaymentService.interest(basicValue, months);
            double feeValue = onlinePaymentService.paymentFee(intValue);
            basicValue = basicValue + intValue + feeValue;
            contract.getInstallments().add(new Installment(dueDate, basicValue));
        }
    }
}
