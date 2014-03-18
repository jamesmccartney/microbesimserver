/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author jmccartney
 */
public class GameObject {

    GOComponent componentManager;
    GOComponent_Script scriptController;

    public GameObject() {
        this.componentManager = new GOComponentManager(null);
        scriptController = new GOComponent_Script(null);
        this.componentManager.attach(scriptController);
    }

    public boolean initializeGO(String type) {
        if(this.scriptController.initialize(type)){
            return true;
        }else{
            return false;
        }
    }

    public boolean intializeGO(ArrayList type) {
        Iterator it = type.iterator(); //iterator
        boolean flag = true;
        while (it.hasNext()) {
            if(!initializeGO(it.next().toString())){
                flag = false;
            }
        }
        return flag;
    }
}
