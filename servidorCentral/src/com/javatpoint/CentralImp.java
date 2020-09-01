package com.javatpoint;
import javax.jws.WebService;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@WebService(endpointInterface = "com.javatpoint.Central")
public class CentralImp implements Central {

    @Override
    public String consultaHoroscopoPronostico(String signo, String fecha) throws MalformedURLException, InterruptedException {
        String re;
        int signoKey = 0;
        int fechaKey = 0;
        String horoscopo = "";
        String pronostico = "";
        String horoscopoEnCache = "";
        String pronosticoEnCache = "";

        // OBTENER KEY DE HASH
        signo = signo.toLowerCase();
        signoKey = signo.hashCode();
        fechaKey = fecha.hashCode();       

        // COSULTAR HOROSCOPO
        CentralPublisher.semaforoHoroscopo.acquire();
        horoscopoEnCache = CentralPublisher.cacheH.get(signoKey);
        if (horoscopoEnCache == null) {
            // CONECTAR CON HOROSCOPO
            URL urlHoroscopo = new URL("http://" +  CentralPublisher.ipServidorHoroscopo + ":" + CentralPublisher.portServidorHoroscopo + "/horoscopo?wsdl");
            QName qnameHoroscopo = new QName("http://javatpoint.com/", "HoroscopoImpService");

            Service serviceHoroscopo = Service.create(urlHoroscopo, qnameHoroscopo);
            Horoscopo srvHoroscopo = serviceHoroscopo.getPort(Horoscopo.class);
            horoscopo = srvHoroscopo.getHoroscopo(signo);
            if (horoscopo.length() != 0) {
                CentralPublisher.cacheH.put(signoKey, horoscopo);
            } else {
                horoscopo = "Signo incorrecto!";
            }
        } else {
            horoscopo = horoscopoEnCache;
        }
        CentralPublisher.semaforoHoroscopo.release();

        // CONSULTAR PRONOSTICO
        CentralPublisher.semaforoPronostico.acquire();
        pronosticoEnCache = CentralPublisher.cacheP.get(fechaKey);
        if (pronosticoEnCache == null) {
            // CONECTAR CON HOROSCOPO
            URL urlPronostico = new URL("http://" +  CentralPublisher.ipServidorPronostico + ":" + CentralPublisher.portServidorPronostico + "/pronostico?wsdl");
            QName qnamePronostico = new QName("http://javatpoint.com/", "PronosticoImpService");

            Service servicePronostico = Service.create(urlPronostico, qnamePronostico);
            Pronostico srvPronostico = servicePronostico.getPort(Pronostico.class);
            pronostico = srvPronostico.getPronostico(fecha);
            if (pronostico.length() != 0) {
                CentralPublisher.cacheP.put(fechaKey, pronostico);
            } else {
                pronostico = "Fecha incorrecta!";
            }
        } else {
            pronostico = pronosticoEnCache;
        }
        CentralPublisher.semaforoPronostico.release();
        
        // CONCATENAR RESULTADOS Y RETORNARLO
        re = "HOROSCOPO: " + horoscopo + " PRONOSTICO: " + pronostico;

        return re;
    }
}