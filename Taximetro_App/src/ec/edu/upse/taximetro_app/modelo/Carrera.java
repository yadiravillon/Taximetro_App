package ec.edu.upse.taximetro_app.modelo;

public class Carrera {
	private int idCarrera;
	private int idPersonas;
	private int idTarifa;
	private Double km;
	private Double valor;
	private String origen;
	private Double cordenada_origen; 
	private String destino;
	private Double cordenada_destino; 
	private String dia; 
	private String mes;
	private String anio;
	
	public Carrera(int idCarrera, int idPersonas, int idTarifa, Double km,
			Double valor, String origen, Double cordenada_origen,
			String destino, Double cordenada_destino, String dia, String mes,
			String anio) {
		super();
		this.idCarrera = idCarrera;
		this.idPersonas = idPersonas;
		this.idTarifa = idTarifa;
		this.km = km;
		this.valor = valor;
		this.origen = origen;
		this.cordenada_origen = cordenada_origen;
		this.destino = destino;
		this.cordenada_destino = cordenada_destino;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public int getIdPersonas() {
		return idPersonas;
	}

	public void setIdPersonas(int idPersonas) {
		this.idPersonas = idPersonas;
	}

	public int getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}

	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Double getCordenada_origen() {
		return cordenada_origen;
	}

	public void setCordenada_origen(Double cordenada_origen) {
		this.cordenada_origen = cordenada_origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Double getCordenada_destino() {
		return cordenada_destino;
	}

	public void setCordenada_destino(Double cordenada_destino) {
		this.cordenada_destino = cordenada_destino;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	}
