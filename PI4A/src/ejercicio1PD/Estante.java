package ejercicio1PD;

public class Estante {
	private static Integer nId = 0;
	
	private Integer id;
	private Integer altura;
	private Integer anchura;
	
	public static Estante create(Integer altura, Integer anchura) {
		return new Estante(altura, anchura);
	}
	
	private Estante(Integer altura, Integer anchura) {
		super();
		this.id = nId;
		nId++;
		this.altura = altura;
		this.anchura = anchura;
	}
	
	public Integer getId() {
		return id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Estante other = (Estante) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estante [id=" + id + ", altura=" + altura + ", anchura=" + anchura + "]";
	}
	
	
}
