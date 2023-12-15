package com.eax.petshop.classes.base;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayHelper {

    public static <S extends Object> S[] addElement(S[] array, S object, int index)
    {
        Class<?> clazz = object.getClass();



        S[] result = (S[]) Array.newInstance(clazz, array.length+1);

        int passed = 0;
        for(int i = 0; i < result.length; i++)
        {
            if(i == index)
            {
                result[i] = object;
                passed = 1;
            }
            else
                result[i] = array[i-passed];
        }

        return result;
    }

    public static <S extends Object> S[] removeElement(S[] array, int index)
    {
        Class<?> clazz = array.getClass().getComponentType();

        S[] result = (S[]) Array.newInstance(clazz, array.length-1);

        int idx = 0;
        for(int i = 0; i < array.length; i++)
        {
            if(idx == result.length)
                break;
            result[idx] = array[i];
            if(i != index)
                idx++;
        }

        return result;
    }

    public static <S extends Object> S[] addElement(S[] array, S object)
    {
        return addElement(array, object, array.length);
    }

    public static <S extends Object> boolean contains(S[] array, S object)
    {
        for(S el: array)
            if(el.equals(object))
                return true;
        return false;
    }

    public static <S extends Object> S[] clone(S[] array, int startFrom, int EndAt)
    {
        Class<?> clazz = array.getClass().getComponentType();

        if(startFrom < 0)
            startFrom = 0;
        else if(startFrom > array.length - 1)
            return (S[]) Array.newInstance(clazz, 0);

        if(EndAt > array.length - 1)
            EndAt = array.length - 1;

        S[] result = (S[]) Array.newInstance(clazz, EndAt-startFrom+1);

        int index = 0;
        for(int i = startFrom; i <= EndAt; i++)
            result[index++] = array[i];

        return result;
    }

    public static <S extends Object> S[] clone(S[] array, int startFrom)
    {
        int endAt = array.length;
        return clone(array, startFrom, endAt);
    }

    public static <S extends Object> S[] except(S[] array, S[] array2)
    {
        Class<?> clazz = array.getClass().getComponentType();

        S[] result = (S[]) Array.newInstance(clazz, 0);

        S[] larger;
        S[] smaller;
        if(array.length > array2.length) {
            larger = array;
            smaller = array2;
        }
        else
        {
            larger = array2;
            smaller = array;
        }

        for(int i = 0; i < larger.length; i++)
        {
            if (!contains(smaller, larger[i]))
                result = addElement(result, larger[i]);
        }

        return result;
    }

    public static <S extends Object> S[] concatenate(S[] array, S[] array2)
    {
        S[] result = ArrayHelper.clone(array, 0);

        for(int i = 0; i < array2.length; i++)
            result = ArrayHelper.addElement(result, array2[i]);

        return result;
    }

}
