package py.una.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VehiculoJSON {


    public static void main(String[] args) throws Exception {
    	VehiculoJSON representacion = new VehiculoJSON();
    	
    	System.out.println("Ejemplo de uso 1: pasar de objeto a string");
    	Vehiculo p = new Vehiculo();
    	p.setMarca("Toyota");
    	p.setChapa("AAA111");
    	p.setMonto(60000L);
    	
    	String r1 = representacion.objetoString(p);
    	System.out.println(r1);
    	
    	
    	System.out.println("\n*************************************************************************");
    	System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
    	String un_string = "{\"monto\":123123,\"marca\":\"Mercedes\",\"chapa\":\"aaa114\"}";
    	
    	Vehiculo r2 = representacion.stringObjeto(un_string);
    	System.out.println(r2.marca + " " + r2.chapa + " " +r2.monto );
    }
    
    public static String objetoString(Vehiculo p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("monto", p.getMonto());
        obj.put("marca", p.getMarca());
        obj.put("chapa", p.getChapa());

        return obj.toJSONString();
    }
    
    
    public static Vehiculo stringObjeto(String str) throws Exception {
    	Vehiculo p = new Vehiculo();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long monto = (Long) jsonObject.get("monto");
        p.setMonto(monto);
        p.setMarca((String)jsonObject.get("marca"));
        p.setChapa((String)jsonObject.get("chapa"));
        return p;
	}

}
