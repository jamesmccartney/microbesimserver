/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.ListIterator;
import java.util.UUID;

/**
 * Props are like decorations but can be touched.
 *
 * @author jmccartney
 */
public class GOComponent_Script_Prop extends GOComponent {

    public GOComponent_Script_Prop(String typeIn) {
        this.setType(typeIn);
        this.id = UUID.randomUUID();
    }

    //Begin Composite Methods
    public int countComponents() {
        return 1;
    }

    public boolean attach(GOComponent componentToAdd) {
        //Do nothing this is a leaf
        return false;
    }

    public boolean detach(GOComponent componentToDetach) {
        //Do nothing this is a leaf
        return false;
    }

    public boolean start() {
        //Logic here
        return true;
    }

    public boolean startChild(GOComponent componentToStart) {
        //Do nothing this is a leaf
        return false;
    }

    public boolean stop() {
        //Logic here
        return true;
    }

    public boolean stopChild(GOComponent componentToStop) {
        //Do nothing this is a leaf
        return false;
    }

    public ListIterator createListIterator() {
        //Do nothing this is a leaf
        return null;
    }

    public boolean update() {
        //Logic here
        return true;
    }

    public boolean updateByType(String type) {
        //Do nothing this is a leaf
        return false;
    }

    public boolean updateChild(UUID idIn) {
        //Do nothing this is a leaf
        return false;
    }

    public GOComponent getChild(UUID idIn) {
        return this;
    }

    public void print() {
        //Print this
    }

    public void printChild(UUID idIn) {
        //Do nothing this is a leaf
    }

    public boolean broadcastMsg(GOComponentMsg msgOut) {
        //Logic here
        return true;
    }

    public boolean sendMsg(UUID id, GOComponentMsg msgOut) {
        //Logic here
        return true;
    }

    public boolean handleMsg(GOComponentMsg msgIn) {
        //Logic here
        return true;
    }

    public boolean setStatus(boolean newStatus) {
        this.status = newStatus;
        return true;

    }

    public boolean getStatus() {
        return this.status;
    }
    //End Composite Methods
}
