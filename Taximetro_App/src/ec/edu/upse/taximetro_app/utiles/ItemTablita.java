package ec.edu.upse.taximetro_app.utiles;

public class ItemTablita {
	private int id;
	private String origen;
    private String destino;
    private Double costo;
	public ItemTablita(int id, String origen, String destino, Double costo) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}
	public ItemTablita() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
}
