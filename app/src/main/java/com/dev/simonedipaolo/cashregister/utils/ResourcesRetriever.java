package com.dev.simonedipaolo.cashregister.utils;

import android.content.Context;

import com.dev.simonedipaolo.cashregister.R;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class ResourcesRetriever {

    private Context context;

    private static int repartiImages[] = {R.drawable.ic_baseline_fastfood_24, R.drawable.ic_wine_24,R.drawable.ic_drink_24, R.drawable.ic_baseline_attach_money_24};
    private static int editIcon = R.drawable.ic_baseline_edit_24;

    private static String repartiCiboNames[];
    private static String repartiBibiteNames[];
    private static String repartiCocktailNames[];
    private static String repartiAltroNames[];

    public static String[] getRepartiCiboNames(Context context) {
        repartiCiboNames = context.getResources().getStringArray(R.array.reparto_cibo_default_string_name);
        return repartiCiboNames;
    }

    public static String[] getRepartiBibiteNames(Context context) {
        repartiBibiteNames = context.getResources().getStringArray(R.array.reparto_bibite_default_string_name);
        return repartiBibiteNames;
    }

    public static String[] getRepartiCocktailNames(Context context) {
        repartiCocktailNames = context.getResources().getStringArray(R.array.reparto_cocktail_default_string_name);
        return repartiCocktailNames;
    }

    public static String[] getRepartiAltroNames(Context context) {
        repartiAltroNames = context.getResources().getStringArray(R.array.reparto_altro_default_string_name);
        return repartiAltroNames;
    }

    public static int[] getRepartiImages() {
        return repartiImages;
    }

    public static int getEditIcon() {
        return editIcon;
    }

}
