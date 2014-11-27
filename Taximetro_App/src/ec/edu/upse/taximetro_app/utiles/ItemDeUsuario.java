package ec.edu.upse.taximetro_app.utiles;

public class ItemDeUsuario {
private int id_login;
private String Usuario;
private String Contraseña;
public ItemDeUsuario(int id_login, String usuario, String contraseña) {
	super();
	this.id_login = id_login;
	Usuario = usuario;
	Contraseña = contraseña;
}
public int getId_login() {
	return id_login;
}
public void setId_login(int id_login) {
	this.id_login = id_login;
}
public String getUsuario() {
	return Usuario;
}
public void setUsuario(String usuario) {
	Usuario = usuario;
}
public String getContraseña() {
	return Contraseña;
}
public void setContraseña(String contraseña) {
	Contraseña = contraseña;
}

}
