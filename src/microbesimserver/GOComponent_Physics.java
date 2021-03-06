/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;
//import org.jbox2d.collision.*;
import org.jbox2d.dynamics.*;
//import org.jbox2d.pooling.*;
//import org.jbox2d.callbacks.*;
import org.jbox2d.common.*;


/**
 *
 * @author jmccartney
 */
public class GOComponent_Physics extends GOComponent {
    
    public World world;

    public GOComponent_Physics(String typeIn) {
        componentList = new LinkedList();
        this.setType(typeIn);
        this.id = UUID.randomUUID();
    }

    //Begin Composite Methods
    public int countComponents() {
        int totalComponents = 0;
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            totalComponents += tempComponent.countComponents();
        }
        return totalComponents;
    }

    public boolean attach(GOComponent componentToAdd) {
        componentToAdd.setParent(this);
        return componentList.add(componentToAdd);

    }

    public boolean detach(GOComponent componentToDetach) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent == componentToDetach) {
                listIterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean startChild(GOComponent componentToStart) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent == componentToStart) {
                if (tempComponent.start()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stopChild(GOComponent componentToStop) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent == componentToStop) {
                if (tempComponent.stop()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean start() {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.start()) {
                return true;
            }
        }
        return false;
    }

    public boolean stop() {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.stop()) {
                return true;
            }
        }
        return false;
    }

    public ListIterator createListIterator() {
        ListIterator listIterator = componentList.listIterator();
        return listIterator;
    }

    public boolean update() {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        boolean flag = true;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (!tempComponent.update()) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean updateByType(String type) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        boolean flag = true;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.getType() == type) {
                if (!tempComponent.update()) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean updateChild(UUID idIn) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.getId() == idIn) {
                if (tempComponent.update()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateByType(UUID idIn) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        boolean flag = true;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.getId() == idIn) {
                if (!tempComponent.update()) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public GOComponent getChild(UUID idIn) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.getId() == idIn) {
                return tempComponent;
            }
        }
        return null;
    }

    public void print() {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            tempComponent.print();

        }
    }

    public void printChild(UUID idIn) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.getId() == idIn) {
                tempComponent.print();
            }
        }
    }

    public boolean broadcastMsg(GOComponentMsg msgOut) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        boolean flag = true;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (!tempComponent.handleMsg(msgOut)) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean sendMsg(UUID id, GOComponentMsg msgOut) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        boolean flag = true;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (tempComponent.getId() == id) {
                if (!tempComponent.handleMsg(msgOut)) {
                    return false;
                }
            }
        }
        return flag;
    }

    public boolean handleMsg(GOComponentMsg msgIn) {
        //Logic here
        return true;
    }

    public boolean setStatus(boolean newStatus) {
        ListIterator listIterator = this.createListIterator();
        GOComponent tempComponent;
        boolean flag = true;
        while (listIterator.hasNext()) {
            tempComponent = (GOComponent) listIterator.next();
            if (!tempComponent.setStatus(newStatus)) {
                return false;
            }
        }
        this.status = newStatus;
        return flag;

    }

    public boolean getStatus() {
        return this.status;
    }
    //End Composite Methods
    
    public boolean setWorld(World inWorld){
        world = inWorld;
        return true;
    }
    
    public World getWorld(){
        return world;
    }

}
