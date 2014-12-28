package ec.edu.upse.taximetro_app.modelo;

public class Usuario {
/*
 "CREATE TABLE usuarios (" +
			" id_u INTEGER PRIMARY KEY AUTOINCREMENT," +
			" id_persona INTEGER NOT NULL," +
			" usuario TEXT," +
			" clave TEXT," +
			" estado TEXT, " +
			"FOREIGN KEY (id_persona) REFERENCES personas(id_p))"
 */
	private Integer id_u;
	private Persona persona;
	private String usuario;
	private String clave;
	private String estado;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer id_u, Persona persona, String usuario, String clave,
			String estado) {
		super();
		this.id_u = id_u;
		this.persona = persona;
		this.usuario = usuario;
		this.clave = clave;
		this.estado = estado;
	}

	public Integer getId_u() {
		return id_u;
	}

	public void setId_u(Integer id_u) {
		this.id_u = id_u;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
