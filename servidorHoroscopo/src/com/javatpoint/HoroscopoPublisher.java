package com.javatpoint;
import javax.xml.ws.Endpoint;

//Endpoint publisher
public class HoroscopoPublisher{
 
	public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: Cliente IPServidor PuertoServidor");
            return;
        }

        String ipServidorHoroscopo = args[0];
        String puertoServidorHoroscopo = args[1];

	   Endpoint.publish("http://" + ipServidorHoroscopo + ":" + puertoServidorHoroscopo + "/horoscopo", new HoroscopoImp());
    }
 
}