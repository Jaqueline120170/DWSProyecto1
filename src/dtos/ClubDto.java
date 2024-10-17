package dtos;

public class ClubDto {
	
	long idClub=11111;
	String nombreClub="aaaaa";
	int miembrosClub=11111;
	String sedeClub="aaaaa";
	
	
	
	public ClubDto() {
		super();
	}

	public ClubDto(long idClub, String nombreClub, int miembrosClub, String sedeClub) {
		super();
		this.idClub = idClub;
		this.nombreClub = nombreClub;
		this.miembrosClub = miembrosClub;
		this.sedeClub = sedeClub;
	}
	
	public long getIdClub() {
		return idClub;
	}
	public void setIdClub(long idClub) {
		this.idClub = idClub;
	}
	public String getNombreClub() {
		return nombreClub;
	}
	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}
	public int getMiembrosClub() {
		return miembrosClub;
	}
	public void setMiembrosClub(int miembrosClub) {
		this.miembrosClub = miembrosClub;
	}
	public String getSedeClub() {
		return sedeClub;
	}
	public void setSedeClub(String sedeClub) {
		this.sedeClub = sedeClub;
	}

	@Override
	public String toString() {
		return "ClubesDto [idClub=" + idClub + ", nombreClub=" + nombreClub + ", miembrosClub=" + miembrosClub
				+ ", sedeClub=" + sedeClub + "]";
	}
	
	
	
}
