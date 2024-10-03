package dtos;

public class MiembrosDto {

	long idUsuario=11111;
	String nombreUsuario="aaaaa";
	String apellidoUsuario="aaaaa";
	String dniUsuario="aaaaa";
	String emailUsuario="aaaaa";
	
	
	public MiembrosDto(long idUsuario, String nombreUsuario, String apellidoUsuario, String dniUsuario,
			String emailUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.dniUsuario = dniUsuario;
		this.emailUsuario = emailUsuario;
	}
	
	
	public MiembrosDto() {
		super();
	}


	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}


	@Override
	public String toString() {
		return "MiembrosDto [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", apellidoUsuario="
				+ apellidoUsuario + ", dniUsuario=" + dniUsuario + ", emailUsuario=" + emailUsuario + "]";
	}

	
	
}
