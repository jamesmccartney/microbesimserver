/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jmccartney
 */
public class GOComponentManager implements GOComponent {

    public GOComponentManager() {

    }

    List<GOComponent> components = new ArrayList<>();

    public void attach(GOComponent component) {
        //component.parent = this;
        components.add(component);
        
    }

    public GOComponent getChild(int i) {
        return components.get(i);
    }

    public void print() {
        Iterator<GOComponent> componentIterator = components.iterator();
        while (componentIterator.hasNext()) {
            GOComponent component = componentIterator.next();
            component.print();
        }
    }

    public void detach(GOComponent component) {
        components.remove(component);
    }
    
    public void update(){
        Iterator<GOComponent> componentIterator = components.iterator();
        while (componentIterator.hasNext()) {
            GOComponent component = componentIterator.next();
            component.update();
        }
    }
    
    public void updateByType(String type){
        
    }
    
    public void broadcastMsg(){
        
    }
    
    public void handleMsg(){
        
    }
    
    
}

