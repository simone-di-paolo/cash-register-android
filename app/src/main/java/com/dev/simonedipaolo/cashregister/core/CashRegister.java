package com.dev.simonedipaolo.cashregister.core;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

/**
 * Created by Simone Di Paolo on 28/07/2022.
 */
public class CashRegister {

    private int receiptNumber = 0;

    private double total;
    private String receipt;

    public CashRegister() {
        total = 0;
        receipt = "";
        receiptNumber++;
    }

    /**
     * Return the receipt closure with number of transaction
     * and with date + time
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getReceiptClosure() {
        String closure = "";
        closure = "#" + (++receiptNumber);

        LocalDateTime localDateTime = LocalDateTime.now();

        String actualDate = "" + localDateTime.getDayOfMonth() + "/"
                + localDateTime.getMonthValue() + "/" + localDateTime.getYear()
                + " " + localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" +
                localDateTime.getSecond();

        closure += " - " + actualDate;
        return closure;
    }

    /**
     * Increase the receipt number
     */
    public void incrementReceiptNumber() {
        receiptNumber++;
    }

    // getters
    public double getTotal() {
        return total;
    }

    public String getReceipt() {
        return receipt;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

}
