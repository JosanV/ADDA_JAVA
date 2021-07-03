package ejercicio3PD;

public class Trayecto {
	Integer origen;
	Integer destino;
	Integer coste;
	
	public static Trayecto create(String s) {
		return new Trayecto(s);
	}
	
	private Trayecto(Integer origen, Integer destino, Integer coste) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.coste = coste;
	}

	private Trayecto(String s) {
		String[] linea = s.split(",");
		this.origen = Integer.parseInt(linea[0]);
		this.destino = Integer.parseInt(linea[1]);
		this.coste = Integer.parseInt(linea[2]);
	}
	
	public Integer getOrigen() {
		return origen;
	}

	public Integer getDestino() {
		return destino;
	}

	public Integer getCoste() {
		return coste;
	}
	
	public Double getRatioAvancePrecio() {
		return (double) ((this.destino-this.origen)/this.coste);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coste == null) ? 0 : coste.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
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
		Trayecto other = (Trayecto) obj;
		if (coste == null) {
			if (other.coste != null)
				return false;
		} else if (!coste.equals(other.coste))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trayecto [origen=" + origen + ", destino=" + destino + ", coste=" + coste + "]";
	}
	
	
}
