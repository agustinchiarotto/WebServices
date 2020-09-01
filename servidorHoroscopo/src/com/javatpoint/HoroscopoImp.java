package com.javatpoint;

import java.util.Random;

import javax.jws.WebService;

@WebService(endpointInterface = "com.javatpoint.Horoscopo")
public class HoroscopoImp implements Horoscopo {

    public static String[] arregloHoroscopo = new String[] {
        "Vas a morir pronto.", "Ganaras mucho dinero.",
        "Perderas tu trabajo.", "Este mes te ascenderan en el trabajo.", "Tienes una gran aventura por delante.",
        "Tendras problemas en el amor.", "Recibiras una visita inesperada.",
        "Te veras mas unido a tu grupo familiar.", "Recibiras una excelente noticia.",
        "Recibiras una noticia horrible.", "Notaras mejoras en tu salud.", "Trataran de estafarte."
    };

    @Override
    public String getHoroscopo(String signo) {
        String re = "";
        boolean signoValido = verificarSigno(signo);
        if (signoValido) {
            Random randomno = new Random();
            int pos = randomno.nextInt(12);
            re = arregloHoroscopo[pos];
        }
        return re;
    }

    private static boolean verificarSigno(String signo) {
        boolean valido = false;
        if (signo.equalsIgnoreCase("Aries") || signo.equalsIgnoreCase("Tauro") || signo.equalsIgnoreCase("Geminis")
                || signo.equalsIgnoreCase("Cancer") || signo.equalsIgnoreCase("Leo") || signo.equalsIgnoreCase("Virgo")
                || signo.equalsIgnoreCase("Libra") || signo.equalsIgnoreCase("Escorpio")
                || signo.equalsIgnoreCase("Sagitario") || signo.equalsIgnoreCase("Capricornio")
                || signo.equalsIgnoreCase("Acuario") || signo.equalsIgnoreCase("Piscis")) {
            valido = true;
        }
        return valido;
    }

  
}