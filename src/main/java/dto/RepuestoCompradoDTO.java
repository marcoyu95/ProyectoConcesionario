package dto;

public class RepuestoCompradoDTO {
	
	private int cantidad;
	private RepuestoDTO repuesto;
	private Integer idRepuestoComprado;


	public void setCantRequerida(int cant) {
		this.cantidad = cant;
	}

	public void setRepuesto(RepuestoDTO repuesto) {
		this.repuesto = repuesto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public RepuestoDTO getRepuesto() {
		return repuesto;
	}

	public Integer getIdRepuestoComprado() {
		return idRepuestoComprado;
	}

	public void setIdRepuestoComprado(Integer idRepuestoComprado) {
		this.idRepuestoComprado = idRepuestoComprado;
	}

	
	
}