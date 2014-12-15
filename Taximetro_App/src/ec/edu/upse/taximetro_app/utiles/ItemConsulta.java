package ec.edu.upse.taximetro_app.utiles;

public class ItemConsulta {

	private String Ruta;
    private Double Valor;
    private Double Distancia;
	
    public ItemConsulta(String ruta, Double valor, Double distancia) {
    	   this.Ruta = ruta;
	        this.Valor = valor;
	        this.Distancia = distancia;
		
	}

	public String getRuta() {
		return Ruta;
	}

	public void setRuta(String ruta) {
		Ruta = ruta;
	}

	public Double getValor() {
		return Valor;
	}

	public void setValor(Double valor) {
		Valor = valor;
	}

	public Double getDistancia() {
		return Distancia;
	}

	public void setDistancia(Double distancia) {
		Distancia = distancia;
	}
    
}
