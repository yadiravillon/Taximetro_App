package ec.edu.upse.taximetro_app.modelo;

public class Tarifa {
	private int id;
	private String descripcion; 
	private Double arranque_tarifa;
	private Double km_recorrido;
	private Double min_espera;
	private Double carrera_min;
	private String estado;
	public Tarifa(int id, String descripcion, Double arranque_tarifa,
			Double km_recorrido, Double min_espera, Double carrera_min,
			String estado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.arranque_tarifa = arranque_tarifa;
		this.km_recorrido = km_recorrido;
		this.min_espera = min_espera;
		this.carrera_min = carrera_min;
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getArranque_tarifa() {
		return arranque_tarifa;
	}
	public void setArranque_tarifa(Double arranque_tarifa) {
		this.arranque_tarifa = arranque_tarifa;
	}
	public Double getKm_recorrido() {
		return km_recorrido;
	}
	public void setKm_recorrido(Double km_recorrido) {
		this.km_recorrido = km_recorrido;
	}
	public Double getMin_espera() {
		return min_espera;
	}
	public void setMin_espera(Double min_espera) {
		this.min_espera = min_espera;
	}
	public Double getCarrera_min() {
		return carrera_min;
	}
	public void setCarrera_min(Double carrera_min) {
		this.carrera_min = carrera_min;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}

