/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.ListIterator;
import java.util.UUID;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.*;
//import org.jbox2d.pooling.*;
//import org.jbox2d.callbacks.*;
import org.jbox2d.common.*;

/**
 *
 * @author jmccartney
 */
public class GOComponent_Physics_Circle extends GOComponent {

    private BodyDef bd;
    private float x, y;
    public CircleShape shape;
    private FixtureDef fd;

    public GOComponent_Physics_Circle(String typeIn) {
        this.setType(typeIn);

        this.id = UUID.randomUUID();

        this.bd = new BodyDef();
        this.fd = new FixtureDef();
        this.fd.shape = shape;
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

    //Getter/Setter
    public void setX(float inX) {
        this.x = inX;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float inY) {
        this.y = inY;
    }

    public float getY() {
        return this.y;
    }

    public void setRadius(float inRadius) {
        this.shape.m_radius = inRadius;
    }

    public float getRadius() {
        return this.shape.m_radius;
    }

    public void setDensity(float inDensity) {
        this.fd.density = inDensity;
    }

    public float getDensity() {
        return this.fd.density;
    }

    public void setFriction(float inFriction) {
        this.fd.friction = inFriction;
    }

    public float getFriction() {
        return this.fd.friction;
    }

    public void setRestitution(float inRestitution) {
        this.fd.restitution = inRestitution;
    }

    public float getRestitution() {
        return this.fd.restitution;
    }

}
