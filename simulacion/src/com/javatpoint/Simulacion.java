package com.javatpoint;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

class Usuario extends Thread {
    private String[] signos = {"Aries","Aries","Aries","Leo","Sagitario","Libra","ASD","ASDF","WWW","USB","123","Aries","Aries"};
    private String[] fechas = {"01/01/2018","01/01/2018","01/01/2018","01/01/2018","08/10/2019","08/10/2019","ASD","ASDF","WWW","USB","123","12/04/1999","12/04/1999"};
    private int id;
    private String ipServidorCentral;
    private String puertoServidorCentral;

    public Usuario(int id, String ipSC, String puertoSC) {
        this.id = id;
        this.ipServidorCentral = ipSC;
        this.puertoServidorCentral = puertoSC;
    }
    @Override
    public void run() {
        

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            Random randomno = new Random();
            int pos1 = randomno.nextInt(13);
            int pos2 = randomno.nextInt(13);
            String signo = signos[pos1];
            String fecha = fechas[pos2];
            System.out.println("\n********************\n** ENVIO **\nUSUARIO: " + id + "\nSIGNO:" + signo + "\nFECHA:" + fecha + "\n********************");
            URL url = new URL("http://" + this.ipServidorCentral + ":" + this.puertoServidorCentral + "/servidor?wsdl");
            
            //1st argument service URI, refer to wsdl document above
    	    //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://javatpoint.com/", "CentralImpService");
     
            Service service = Service.create(url, qname);
            Central srv = service.getPort(Central.class);
            
            String respuesta="";
            respuesta = srv.consultaHoroscopoPronostico(signo, fecha);
            System.out.println("\n********************\n** RECIBE **\nUSUARIO: " + id + "\nRESPUESTA:" + respuesta + "\n********************");

        } catch (Exception e) {
            System.err.println("Excepcion en Usuario:");
            e.printStackTrace();
        }
    }
}

public class Simulacion {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: Simulacion IPServidor PuertoServidor");
            return;
        }
        
        String ipServidorCentral = args[0];
        String puertoServidorCentral = args[1];

        ArrayList<Thread> usuarios = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            usuarios.add(new Usuario(i, ipServidorCentral, puertoServidorCentral));
        }
        for (Thread usuario : usuarios) {
            usuario.start();
        }
    }
}