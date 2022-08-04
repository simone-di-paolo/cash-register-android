package com.dev.simonedipaolo.cashregister.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

/**
 * Created by Simone Di Paolo on 04/08/2022.
 */
public class CommonUtils {

    /**
     * Return the receipt closure with number of transaction
     * and with date + time
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getReceiptClosure(int receiptNumber) {
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

}
