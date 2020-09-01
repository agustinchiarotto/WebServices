package com.javatpoint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
 
public class Cliente{
 	public static void main(String[] args) throws Exception {
    if (args.length != 2) {
        System.err.println("Uso: Cliente IPServidor PuertoServidor");
        return;
    }

    String ipServidorCentral = args[0];
    String puertoServidorCentral = args[1];

 	URL url = new URL("http://" + ipServidorCentral + ":" + puertoServidorCentral + "/servidor?wsdl");
 
        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://javatpoint.com/", "CentralImpService");
 
        Service service = Service.create(url, qname);
        Central srv = service.getPort(Central.class);

        try {
            BufferedReader entradaLinea = new BufferedReader(new InputStreamReader(System.in));
            String signoInput;
            String fechaInput;
            System.out.println("\n********************\nBIENVENIDO!\n(dejar ambas entradas vacias para salir)\n********************");

            do {
                System.out.println("\n********************\nINGRESE UN SIGNO\n********************");
                signoInput = entradaLinea.readLine();

                System.out.println("\n********************\nINGRESE UNA FECHA\n********************");
                fechaInput = entradaLinea.readLine();
                
                if (signoInput.length() != 0 || fechaInput.length() != 0) {
                    System.out.println("\n*************\nRESULTADO\n*************\n" + srv.consultaHoroscopoPronostico(signoInput,fechaInput));
                }
            } while (signoInput.length() != 0 || fechaInput.length() != 0);

            System.out.println("\nADIOS!");
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        } catch (Exception e) {
            System.err.println("Excepcion en Cliente:");
            e.printStackTrace();
        }
 
    }
 
}