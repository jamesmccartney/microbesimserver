/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.ListIterator;

/**
 *
 * @author jmccartney
 */
public class GOComponent_Physics extends GOComponent {

    public GOComponent_Physics(String typeIn) {
        this.setType(typeIn);
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
        return true;
    }

    public GOComponent getChild(int idIn) {
        return this;
    }

    public void print() {
        //Print this
    }

    public void printChild(int idIn) {
        //Do nothing this is a leaf
    }

    public boolean broadcastMsg(GOComponentMsg msgOut) {
        //Logic here
        return true;
    }

    public boolean sendMsg(int id, GOComponentMsg msgOut) {
        //Logic here
        return true;
    }

    public boolean handleMsg(GOComponentMsg msgIn) {
        //Logic here
        return true;
    }
    //End Composite Methods

}
