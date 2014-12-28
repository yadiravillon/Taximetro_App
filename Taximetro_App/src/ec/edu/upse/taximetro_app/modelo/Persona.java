package ec.edu.upse.taximetro_app.modelo;

public class Persona {
	/*
	"CREATE TABLE personas (" +
			" id_p INTEGER PRIMARY KEY AUTOINCREMENT," +
			" nombres TEXT," +
			" apellidos TEXT," +
			" email TEXT," +
			" estado TEXT )"
	*/
	
	private Integer id_p;
	private String nombres;
	private String apellidos;
	private String email;
	private String estado;
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(Integer id_p, String nombres, String apellidos,
			String email, String estado) {
		super();
		this.id_p = id_p;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.estado = estado;
	}

	public Integer getId_p() {
		return id_p;
	}

	public void setId_p(Integer id_p) {
		this.id_p = id_p;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
