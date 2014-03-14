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
public class ZeroMQPullServerWorker extends Thread {

    private Context context;
    private String address;

    public ZeroMQPullServerWorker(Context context, String inAddress) {
        this.context = context;
        this.address = inAddress;
    }

    @Override
    public void run() {
        ZMQ.Socket socket = context.socket(ZMQ.PULL);
        socket.connect(this.address);

        while (true) {
            
            String request = socket.recvStr (0);
            System.out.println ( Thread.currentThread().getName() + " Received request: [" + request + "]");
            
            // Do some 'work'
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

        }
    }
}
