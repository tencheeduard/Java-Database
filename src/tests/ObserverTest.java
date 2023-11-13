package src.tests;

import src.classes.base.Observable;
import src.classes.base.Observer;

public class ObserverTest {

    public class TestObserver implements Observer
    {

        public String status = "";

        @Override
        public void update(Object arg) {
            status = "received update from " + arg;
        }

        @Override
        public void update(Object[] args) {
            status = "received update: ";
            for(Object arg: args)
            {
                status += arg;
            }
        }

        @Override
        public void update() {
            status = "received empty update";
        }
    }

    public class TestObservable extends Observable { }

    public void test1()
    {
        TestObserver testObserver = new TestObserver();
        TestObservable testObservable = new TestObservable();

        testObservable.addObserver(testObserver);

        testObservable.notifyObservers();
        assert testObserver.status.equals("received empty update");

        testObservable.notifyObservers(this);
        assert testObserver.status.equals("received update from " + this);

        String[] strings = {"Hel", "lo ", "Wor", "ld!"};
        testObservable.notifyObservers(strings);
        assert testObserver.status.equals("received update: Hello World!");
    }

}
