package com.example.naikkuy.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatHelper {
    public static String rupiah(int value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return format.format(value).replace(",00", "");
    }
}
