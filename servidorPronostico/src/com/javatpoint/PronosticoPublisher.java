package com.javatpoint;
import javax.xml.ws.Endpoint;

//Endpoint publisher
public class PronosticoPublisher{
 
	public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: Cliente IPServidor PuertoServidor");
            return;
        }

        String ipServidorPronostico = args[0];
        String puertoServidorPronostico = args[1];

	   Endpoint.publish("http://" + ipServidorPronostico + ":" + puertoServidorPronostico + "/pronostico", new PronosticoImp());
    }
 
}