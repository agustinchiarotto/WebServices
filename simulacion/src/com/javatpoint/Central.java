package com.javatpoint;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface Central{
 
	@WebMethod String consultaHoroscopoPronostico(String signo, String fecha);
 
}