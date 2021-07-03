package main;

public class Pasillo {
	private Camara source;
	private Camara target;
	private Double distancia;
	
	public static Pasillo of() {
		return new Pasillo();
	}

	public static Pasillo ofVertex(Camara c1, Camara c2, Double distancia) {
		return new Pasillo(c1,c2, distancia);
	}

	public static Pasillo ofFormat(Camara c1, Camara c2, String[] formato) {
		return new Pasillo(c1,c2,formato);
	}
	
	private Pasillo(Camara source, Camara target, Double distancia) {
		super();
		this.source = source;
		this.target = target;
		this.distancia = distancia;
	}

	private Pasillo(Camara source, Camara target) {
		super();
		this.source = source;
		this.target = target;
		this.distancia = 0.0;
	}
	
	private Pasillo(Camara source, Camara target, String[] s) {
		super();
		this.source = source;
		this.target = target;
		this.distancia = Double.parseDouble(s[2]);
	}
	
	private Pasillo() {
		super();
		this.source = null;
		this.target = null;
	}

	public Camara getSource() {
		return source;
	}

	public Camara getTarget() {
		return target;
	}


	public Double getDistancia() {
		return distancia;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Pasillo other = (Pasillo) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pasillo [source=" + source + ", target=" + target + "]" + "DISTANCIA: " + this.distancia;
	}

	
	
	
}
