package ejercicio1PD;

public class Libro {
	private Integer altura;
	private Integer anchura;
	
	public static Libro create(String s) {
		return new Libro(s);
	}
		
	private Libro(Integer altura, Integer anchura) {
		super();
		this.altura = altura;
		this.anchura = anchura;
	}
	
	private Libro(String s) {
		String[] v = s.split(",");
		Integer ne = v.length;
		if(ne != 2) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
		this.altura = Integer.parseInt(v[0]);
		this.anchura = Integer.parseInt(v[1]);
	}
	
	private Libro() {
		super();
	}

	public Integer getAltura() {
		return altura;
	}

	public Integer getAnchura() {
		return anchura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altura == null) ? 0 : altura.hashCode());
		result = prime * result + ((anchura == null) ? 0 : anchura.hashCode());
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
		Libro other = (Libro) obj;
		if (altura == null) {
			if (other.altura != null)
				return false;
		} else if (!altura.equals(other.altura))
			return false;
		if (anchura == null) {
			if (other.anchura != null)
				return false;
		} else if (!anchura.equals(other.anchura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [altura=" + altura + ", anchura=" + anchura + "]";
	}
	
	
}
