/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;
/**
 * 
 * Abstract class for Composite pattern used by game object components.  Meant to be used in conjunction with GOCompnentManger.
 *
 * @author jmccartney
 */
public abstract class GOComponent {
    
    LinkedList componentList;
    GOComponent parent;
    String type;
    UUID id;
    
    public boolean status = false;
    
    public abstract int countComponents();
    
    public abstract boolean attach(GOComponent componentToAdd);
    public abstract boolean detach(GOComponent componentToDetach);
    
    public abstract boolean start();
    public abstract boolean startChild(GOComponent componentToStart);
    public abstract boolean stop();
    public abstract boolean stopChild(GOComponent componentToStop);
    
    public abstract ListIterator createListIterator();
    
    public abstract boolean update();
    public abstract boolean updateByType(String type);
    public abstract boolean updateChild(UUID id);
    public abstract GOComponent getChild(UUID id);
    
    public abstract void print();
    public abstract void printChild(UUID id);
    
    public abstract boolean broadcastMsg(GOComponentMsg msgOut);
    public abstract boolean sendMsg(UUID id, GOComponentMsg msgOut);
    public abstract boolean handleMsg(GOComponentMsg msgIn);
    
    public abstract boolean setStatus(boolean newStatus);
    public abstract boolean getStatus();
    
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
    
    public void setId(UUID idIn) {
        id = idIn;
    }

    public UUID getId() {
        return id;
    }

}
