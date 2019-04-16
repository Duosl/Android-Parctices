package com.duoshilin.java_design_patter.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by duoshilin on 2019/2/13.
 * 被观察者
 */
public class Subject {

    private Vector<Observe> observes = new Vector<>();
    private int value=0;

    public void attach(Observe observe){
        observes.add(observe);
    }

    public void disattach(Observe observe){
        observes.remove(observe);
    }

    private void notifyAllObservers(){
        Enumeration<Observe> elements = observes.elements();
        while (elements.hasMoreElements()){
            elements.nextElement().update();
        }
    }

    public void setValue(int value){
        if (this.value != value){
            System.out.println("----- This change is "+value+"-----");
            this.value = value;
            notifyAllObservers();
        }
    }

    public int getValue(){
        return this.value;
    }
}
