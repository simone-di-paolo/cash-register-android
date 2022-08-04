package com.dev.simonedipaolo.cashregister.room;

import androidx.room.*;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Simone Di Paolo on 04/08/2022.
 */
public class Converters {

    @TypeConverter
    public static List<String> stringToList(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String listToString(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        return json;
    }

}
