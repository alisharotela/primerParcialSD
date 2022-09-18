package py.una.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DatosJSON {


    public static void main(String[] args) throws Exception {
    	DatosJSON representacion = new DatosJSON();
    	
    	System.out.println("Ejemplo de uso 1: pasar de objeto a string");
    	Datos p = new Datos();
    	p.setNombre("Maria");
    	p.setApellido("Gomez");
    	p.setCedula(123456L);
        p.setChapa("ABC123");
        p.setMarca("Toyota");
        p.setMonto(1000L);

    	String r1 = representacion.objetoString(p);
    	System.out.println(r1);
    	
    	
    	System.out.println("\n*************************************************************************");
    	System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
    	String un_string = "{\"cedula\":123123,\"nombre\":\"Ana\",\"apellido\":\"Perez\"}";
    	
    	Datos r2 = representacion.stringObjeto(un_string);
    	System.out.println(r2.nombre + " " + r2.apellido + " " +r2.cedula );
    }
    
    public static String objetoString(Datos p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("cedula", p.getCedula());
        obj.put("nombre", p.getNombre());
        obj.put("apellido", p.getApellido());
        obj.put("marca", p.getMarca());
        obj.put("chapa", p.getChapa());
        obj.put("monto", p.getMonto());
        obj.put("operacion", p.getOperacion());

        return obj.toJSONString();
    }
    
    
    public static Datos stringObjeto(String str) throws Exception {
    	Datos p = new Datos();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long cedula = (Long) jsonObject.get("cedula");
        p.setCedula(cedula);
        p.setNombre((String)jsonObject.get("nombre"));
        p.setApellido((String)jsonObject.get("apellido"));
        p.setMarca((String)jsonObject.get("marca"));
        p.setChapa((String)jsonObject.get("chapa"));
        p.setMonto((Long)jsonObject.get("monto"));
        System.out.println("uehtoanu");
        p.setOperacion(Integer.parseInt(jsonObject.get("operacion").toString()));
        return p;
	}

}
