package src.classes.base;

import java.util.Dictionary;

public class DatabaseProxy implements Observer {


    public CacheTriplet[] cachedData;

    public DatabaseProxy()
    {
        cachedData = new CacheTriplet[0];
    }


    @Override
    public void update(Object arg) {
        if(arg instanceof Table table)
        {
            for(int i = 0; i < cachedData.length; i++)
                if(cachedData[i].table.getClass() == table.getClass())
                    cachedData = ArrayHelper.removeElement(cachedData, i--);
        }
    }

    @Override
    public void update(Object[] args) {

    }

    @Override
    public void update() {

    }

}
