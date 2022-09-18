package py.una.entidad;
import java.util.ArrayList;
import java.util.List;

public class Datos {

	Long cedula;
	String nombre;
	String apellido;
	String marca;
	String chapa;
	Long monto;
	int operacion;

	public Datos(){}

	public Datos(Long pcedula, String pnombre, String papellido, String pmarca, String pchapa, Long pmonto){
		this.cedula = pcedula;
		this.nombre = pnombre;
		this.apellido = papellido;
		this.marca = pmarca;
		this.chapa = pchapa;
		this.monto = pmonto;
	}
	
	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getChapa() {
		return chapa;
	}

	public void setChapa(String chapa) {
		this.chapa = chapa;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public int getOperacion() {
		return operacion;
	}

	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}
	
}
