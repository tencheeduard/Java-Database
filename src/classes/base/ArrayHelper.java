package src.classes.base;

import java.lang.reflect.Array;

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

}
