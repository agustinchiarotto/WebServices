package com.javatpoint;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class CentralPublisher{

    public static HashMap<Integer, String> cacheH = new HashMap<Integer, String>();
    public static HashMap<Integer, String> cacheP = new HashMap<Integer, String>();
    public static Semaphore semaforoHoroscopo = new Semaphore(1);
    public static Semaphore semaforoPronostico = new Semaphore(1);
    public static String ipServidorHoroscopo;
    public static String portServidorHoroscopo;
    public static String ipServidorPronostico;
    public static String portServidorPronostico;
 
	public static void main(String[] args) {
        if (args.length != 6) {
            System.err.println("Uso: ServidorCentral IPServidorCentral PuertoServidorCentral IPServidorHoroscopo PuertoServidorHoroscopo IPServidorPronostico PuertoServidorPronostico");
            return;
        }
        String ipServidorCentral = args[0];
        String portServidorCentral = args[1];
        ipServidorHoroscopo = args[2];
        portServidorHoroscopo = args[3];
        ipServidorPronostico = args[4];
        portServidorPronostico = args[5];

	    Endpoint.publish("http://" + ipServidorCentral + ":" + portServidorCentral + "/servidor", new CentralImp());
    }
 
}