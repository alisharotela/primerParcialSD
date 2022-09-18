package py.una.server.udp;


import java.io.*;
import java.net.*;

import py.una.entidad.Datos;
import py.una.entidad.DatosJSON;

public class UDPClient {

    public static void main(String a[]) throws Exception  {

        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;
        
        try {

            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            
            System.out.println("Ingrese la operacion que sea realizar");
            System.out.println("1 Enviar datos");
            System.out.print("2 Recibir datos: ");
            
            int operacion = Integer.parseInt(inFromUser.readLine());
            
            if (operacion == 1){
                
                byte[] sendData = new byte[1024];
                System.out.print("Ingrese el número de cédula (debe ser numérico): ");
                String strcedula = inFromUser.readLine();
                Long cedula = 0L;
                try {
                    cedula = Long.parseLong(strcedula);
                }catch(Exception e1) {
                    
                }
                
                System.out.print("Ingrese el nombre: ");
                String nombre = inFromUser.readLine();
                System.out.print("Ingrese el apellido: ");
                String apellido = inFromUser.readLine();
                
                System.out.println("Ingrese la marca del vehículo: ");
                String marca = inFromUser.readLine();
                System.out.println("Ingrese la chapa del vehículo: ");
                String chapa = inFromUser.readLine();
                System.out.println("Ingrese el monto del vehículo: ");
                String strmonto = inFromUser.readLine();
                
                Datos d = new Datos(cedula, nombre, apellido, marca, chapa, Long.parseLong(strmonto));
                d.setOperacion(1);
                String datoPaquete = DatosJSON.objetoString(d);
                
                sendData = datoPaquete.getBytes();
                
                DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);
                
                clientSocket.send(sendPacket);
            }
            if(operacion == 2) {

                // send data.operacion = 2
                byte[] sendData = new byte[1024];
                Datos d = new Datos();
                d.setOperacion(2);
                String datoPaquete = DatosJSON.objetoString(d);
                sendData = datoPaquete.getBytes();
                DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                clientSocket.send(sendPacket);

                byte[] receiveData = new byte[1024];
    
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);
    
                System.out.println("Esperamos si viene la respuesta.");
    
                //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                clientSocket.setSoTimeout(10000);
    
                try {
                    // ESPERAMOS LA RESPUESTA, BLOQUENTE
                    clientSocket.receive(receivePacket);
    
                    String respuesta = new String(receivePacket.getData());
                    Datos presp = DatosJSON.stringObjeto(respuesta.trim());
                    
                    InetAddress returnIPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
    
                    System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                    
                    System.out.println("El vehículo de monto mayor es:" + presp.getMarca() + " " + presp.getChapa() + " " + presp.getMonto());
                    
    
                } catch (SocketTimeoutException ste) {
    
                    System.out.println("TimeOut: El paquete udp se asume perdido.");
                }
            }


            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
} 

