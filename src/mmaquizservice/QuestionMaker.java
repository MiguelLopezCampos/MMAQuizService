/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmaquizservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Windows 8
 */
public class QuestionMaker extends Thread{
    private Socket socketServicio;
    
    private InputStream inputStream;
    
    private OutputStream outputStream;
    
    private Thread thr;
    
    private boolean conectado = true;
    
    private int id;
    
    public QuestionMaker(Socket socketServicio, int id) throws IOException
    {
        this.socketServicio = socketServicio;
        this.id = id;
        
        thr = new Thread(this, "sender");
    }
    
    public void run()
    {
        
    }
}
