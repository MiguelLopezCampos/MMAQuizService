/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmaquizservice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

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
    
    private final String directory = "";
    
    private Question xmlParser() throws ParserConfigurationException, SAXException, IOException{
        Question q;
        
        
        File input = new File(directory);
        
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();        
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(input);
        doc.getDocumentElement().normalize();
        //Obtengo la pregunta
        Element eElement = (Element) doc.getElementsByTagName("question").item(0);
        String question = eElement.getElementsByTagName("q").item(0).getTextContent();
        String A = eElement.getElementsByTagName("A").item(0).getTextContent();
        String B = eElement.getElementsByTagName("B").item(0).getTextContent();
        String C = eElement.getElementsByTagName("C").item(0).getTextContent();
        String correct_answer = eElement.getElementsByTagName("correct_answer").item(0).getTextContent();
        
        //Creo la nueva pregunta
        q= new Question(question, A, B, C, correct_answer);
       
        return q;
        
    }

    public QuestionMaker(Socket socketServicio, int id) throws IOException
    {
        this.socketServicio = socketServicio;
        this.id = id;
        
        thr = new Thread(this, "sender");
    }
    
    public void run()
    {
        byte [] datosRecibidos=new byte[1024];
        int bytesRecibidos=0;

        // Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
        byte [] datosEnviar;


        try {
            // Obtiene los flujos de escritura/lectura
            inputStream=socketServicio.getInputStream();
           
            ////////////////////////////////////////////////////////
            
           // lector = new LectorChat(chat, socketServicio, id);
           
            while (conectado)
            {
                byte [] mensajeRecibido = new byte[2048];
                int bytesRecibidosMensaje;
                bytesRecibidosMensaje = inputStream.read(mensajeRecibido);

               // while(chat.todosHanLeido() == false){}
                                		
                String mensaje = new String (mensajeRecibido, 0, bytesRecibidosMensaje);

                if(mensaje.compareTo("\r\r\rexit") == 0){ 
                    socketServicio.close(); 
                    inputStream.close(); 
                    outputStream.close(); 
                    conectado=false;
                 }
                else{
               //System.out.println(mensaje);
                }
            }

        } catch (IOException e) {
                System.err.println("Error al obtener los flujso de entrada/salida.");
        }
    }
}
