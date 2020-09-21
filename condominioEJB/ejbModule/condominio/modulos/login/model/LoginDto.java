package condominio.modulos.login.model;

public class LoginDto {

	private long idUsuario;
	private String nombres;
	private String nombreRol;
	private String cedula;
	private String seguridadCedula;
	private boolean activo;
	private boolean inseguro;
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public boolean isInseguro() {
		return inseguro;
	}
	public void setInseguro(boolean inseguro) {
		this.inseguro = inseguro;
	}
	public String getSeguridadCedula() {
		return seguridadCedula;
	}
	public void setSeguridadCedula(String seguridadCedula) {
		this.seguridadCedula = seguridadCedula;
	}
	
}
