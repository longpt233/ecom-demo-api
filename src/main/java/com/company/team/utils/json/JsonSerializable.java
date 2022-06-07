package com.company.team.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class JsonSerializable {

    private static final Gson GSON = new GsonBuilder().
            registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                if (src == src.longValue()) {
                    return new JsonPrimitive(src.longValue());
                }
                return new JsonPrimitive(src);
            }).create();

    public static <T> T deserialize(final String data, final Class<T> clazz)  {
        return GSON.fromJson(data, clazz);
    }

    public static String serialize(final Object model) {
        return GSON.toJson(model);
    }

}
