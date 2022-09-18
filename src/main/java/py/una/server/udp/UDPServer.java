package py.una.server.udp;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import py.una.entidad.Datos;
import py.una.entidad.DatosJSON;

public class UDPServer {
	
	
    public static void main(String[] a){
        
        System.out.println("Alumna: Alisha Rotela");
        System.out.println("________________________________________________");

        // Variables
        int puertoServidor = 9876;
        // Arraylist de datos
        List<Datos> datos = new ArrayList<Datos>();
        
        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
			System.out.println("Servidor Sistemas Distribuidos - UDP ");
			
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

			
            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);


                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);
				
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido: " + datoRecibido );
                Datos p = DatosJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println(
                    "Datos Recibidos : " 
                    + p.getCedula() + ", " 
                    + p.getNombre() + " " 
                    + p.getApellido() + " "
                    + p.getChapa() + " "
                    + p.getMarca() + " "
                    + p.getMonto() + " "
                    + p.getOperacion()
                );
                
                if (p.getOperacion() == 1){
                    // Agregamos los datos a la lista
                    datos.add(p);
                    System.out.println("Datos agregados a la lista");
                }else if (p.getOperacion() == 2){
                    // Enviar datos con monto mayor de la lista
                    System.out.println("Enviando datos con monto mayor");
                    // Buscamos el monto mayor
                    Datos mayor = new Datos();
                    for (Datos d : datos){
                        // check if getMonto is null
                        if (mayor.getMonto() == null){
                            mayor = d;

                        } else if (d.getMonto() > mayor.getMonto()){
                            //copy d object to mayor
                            mayor = d;
                        }
                    }
                    // Enviamos el monto mayor
                    // Enviamos la respuesta inmediatamente a ese mismo cliente
                    // Es no bloqueante
                    sendData = DatosJSON.objetoString(mayor).getBytes();
                    DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                }
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

