package com.javatpoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.jws.WebService;

@WebService(endpointInterface = "com.javatpoint.Pronostico")
public class PronosticoImp implements Pronostico {

    private static String[] arregloPronostico = new String[] {
        "Nieve.", "Probabilidad de granizo.", "Nublado.", "Lluvia todo el dia.",
        "Soleado todo el dia.", "Cielo despejado.", "Inestable.", "Un huracan se avecina.",
        "Fuertes tormentas.", "Vientos fuertes."
    };

    @Override
    public String getPronostico(String fecha) {
        String re = "";
        boolean fechaValida = verificarFecha(fecha);

        if (fechaValida) {
            Random randomno = new Random();
            int pos = randomno.nextInt(10);
            re = arregloPronostico[pos];
        }
        return re;
    }

    private static boolean verificarFecha(String fecha) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(fecha);
            if (!fecha.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {

        }
        return date != null;
    }
}