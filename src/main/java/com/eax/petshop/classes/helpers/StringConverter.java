package com.eax.petshop.classes.helpers;

import java.sql.Date;

public class StringConverter {

    public static Object convert(String string, Class<?> converted) throws Exception {

        Object result;

        if (converted == Integer.class)
            result = Integer.valueOf(string);
        else if (converted == Date.class)
            result = Date.valueOf(string);
        else
            result = converted.getDeclaredConstructor().newInstance();


        if(result instanceof Integer intResult)
            return intResult;
        else if (result instanceof Date dateResult)
            return dateResult;


        return null;
    }

}
