package main;

public class Camara {
	private String nombre;

	public static Camara of() {
		return new Camara("");
	}
	
	public static Camara ofFormat(String[] formato) {
		return new Camara(formato[0]);
	}
	
	
	
	private Camara(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	private Camara() {
		super();
		this.nombre = null;
	}

	public String getNombre() {
		return nombre;
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
		Camara other = (Camara) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Camara [nombre=" + nombre + "]";
	}
	
	
	
	
}
