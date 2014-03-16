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
public class ZeroMQBroadcastServerWorker extends Thread {

    private Context context;
    private String address;

    public ZeroMQBroadcastServerWorker(Context context, String inAddress) {
        this.context = context;
        this.address = inAddress;
    }

    @Override
    public void run() {
        ZMQ.Socket socket = context.socket(ZMQ.PUB);
        socket.bind(this.address);

        while (true) {

            
            //broadcast to node server
            socket.send("world", 0);
                        

            // Do some 'work'
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

        }
    }
}
