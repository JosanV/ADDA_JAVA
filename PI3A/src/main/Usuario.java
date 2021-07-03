package main;

public class Usuario {
	
	private String nombre;
	private String categoria;
	
	public static Usuario of() {
		return new Usuario("", "");
	}
	
	public static Usuario ofFormat(String[] fmt) {
		return new Usuario(fmt[0], fmt[1]);
	}


	public Usuario(String nombre, String categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	public Usuario(String nombre) {	//Public para el ejercicio b
		super();
		this.nombre = nombre;
		this.categoria = null;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario " + this.nombre + " Cat: " + this.categoria;
	}
	
	
	
}
