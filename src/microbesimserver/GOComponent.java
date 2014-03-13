/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.LinkedList;
import java.util.ListIterator;
/**
 *
 * @author jmccartney
 */
public abstract class GOComponent {
    
    LinkedList componentList;
    GOComponent parent;
    String type;
    int id;
    
    public abstract int countComponents();
    public abstract boolean attach(GOComponent componentToAdd);
    public abstract boolean detach(GOComponent componentToDetach);
    public abstract ListIterator createListIterator();
    public abstract boolean update();
    public abstract boolean updateByType(String type);
    public abstract GOComponent getChild(int id);
    public abstract void print();
    public abstract void printChild(int id);
    public abstract boolean broadcastMsg(GOComponentMsg msgOut);
    public abstract boolean sendMsg(int id, GOComponentMsg msgOut);
    public abstract boolean handleMsg(GOComponentMsg msgIn);
    
    public void setParent(GOComponent parentIn){
        parent = parentIn;
    }
    
    public GOComponent getParent(){
        return parent;
    }
    
    public void setType(String typeIn){
        type = typeIn;
    }
    
    public String getType(){
        return type;
    }
    
    public void setId(int idIn) {
        id = idIn;
    }

    public int getId() {
        return id;
    }
}
