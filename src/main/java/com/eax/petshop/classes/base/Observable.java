package com.eax.petshop.classes.base;

import com.eax.petshop.classes.helpers.ArrayHelper;

public abstract class Observable {

    public Observer[] observers = new Observer[0];

    public void addObserver(Observer observer)
    {
        observers = ArrayHelper.addElement(observers, observer);
    }

    public void notifyObservers()
    {
        for(Observer observer: observers)
            observer.update();
    }

    public void notifyObservers(Object arg)
    {
        for(Observer observer: observers)
            observer.update(arg);
    }

    public void notifyObservers(Object[] args)
    {
        for(Observer observer: observers)
            observer.update(args);
    }


}
