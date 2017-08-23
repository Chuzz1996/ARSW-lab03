/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{
    
    private LinkedBlockingQueue<Integer> queue;
    
    
    public Consumer(LinkedBlockingQueue<Integer> queue){
        this.queue=queue;        
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (queue.size() > 0) {
                int elem=queue.poll();
                System.out.println("Consumer consumes "+elem);                                
                synchronized(queue){
                    queue.notify();
                }  
            }else{
                synchronized(queue){
                    try{
                        queue.wait();
                    }catch(InterruptedException e){}
                }
            }      
        }
    }
}
