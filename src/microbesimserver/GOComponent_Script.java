/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

/**
 *
 * @author jmccartney
 */
public class GOComponent_Script implements GOComponent {
    
    protected GOComponent_Script parent;

    public GOComponent_Script() {

    }
    
    //Begin Composite Methods

    public void attach(GOComponent component) {
        //nothing to do here - leaf node
    }

    public GOComponent getChild(int i) {
       //nothing to do here - leaf node
        return null;
    }

    public void print() {
        System.out.println("-------------");

    }

    public void detach(GOComponent component) {
        //nothing to do here - leaf node
    }
    
    public void update(){
        
    }
    
    public void updateByType(String type){
        
    }
    
    public void broadcastMsg(){
        
    }
    
    public void handleMsg(){
        
    }
    
    //End Composite Methods
}
