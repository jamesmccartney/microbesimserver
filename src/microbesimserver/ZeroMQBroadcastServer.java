/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 *
 * @author jmccartney
 */
public class ZeroMQBroadcastServer {

    private Context context;
    //Socket clients;
    private Socket workers;
    private int threads;
    private String address;

    public ZeroMQBroadcastServer(int inThreads, String inAddress) {
        context = ZMQ.context(1);
        Socket workers = context.socket(ZMQ.PUB);
        workers.bind(inAddress);
        this.threads = inThreads;
        this.address = inAddress;
    }
    
    public void setThreads(int inThreads){
        this.threads = inThreads;
    }
    
    public int getThreads(){
        return this.threads;
    }
    
    //start worker threads so that update requests be made
    public void start() {
        for (int thread_nbr = 0; thread_nbr < 5; thread_nbr++) {
            Thread worker = new ZeroMQBroadcastServerWorker(context,this.address);
            worker.start();
        }
         //ZMQ.proxy (clients, workers, null);
    }

    public boolean stop() {
        //clients.close();
        workers.close();
        context.term();
        return true;
    }
}
