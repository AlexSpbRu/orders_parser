package org.alexkozlov.test;

import java.util.Currency;

public class OutputData {
    int         id;
    double       amount;
    Currency    currency = null;
    String      comment = null;
    String      filename = null;
    int         line;
    String      result = null;

    public void assign(Order order) {
        this.id = order.orderId;
        this.amount = order.amount;
        this.currency = order.currency;
        this.comment = order.comment;
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency=" + currency +
                ", comment='" + comment + '\'' +
                ", filename='" + filename + '\'' +
                ", line=" + line +
                ", result='" + result + '\'' +
                '}';
    }
}
