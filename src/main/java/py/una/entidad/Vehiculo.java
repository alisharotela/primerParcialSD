package py.una.entidad;
import java.util.ArrayList;
import java.util.List;

public class Vehiculo {

	String marca;
	String chapa;
	Long monto;

	public Vehiculo(){}
	
	public Vehiculo(String pmarca, String pchapa, Long pmonto){
		this.marca = pmarca;
		this.chapa = pchapa;
		this.monto = pmonto;
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
}
