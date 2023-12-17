package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Installment {
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate dueDate;
    private Double amount;

    public Installment(LocalDate dueDate, Double amount) {
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return dueDate.format(fmt) + " - " + String.format("%.2f", amount);
    }
}
