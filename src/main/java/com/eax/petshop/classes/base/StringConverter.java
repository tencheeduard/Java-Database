package com.eax.petshop.classes.base;

public class StringConverter {

    public static Object convert(String string, Class<?> converted) throws Exception {

        Object result;

        if (converted == Integer.class)
            result = 0;
        else
            result = converted.getDeclaredConstructor().newInstance();


        if(result instanceof Integer intResult)
        {
            intResult = Integer.valueOf(string);
            return intResult;
        }


        return null;
    }

}
