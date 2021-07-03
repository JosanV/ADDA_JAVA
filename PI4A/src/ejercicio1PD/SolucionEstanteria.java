package ejercicio1PD;

import java.util.HashMap;
import java.util.Map;

public class SolucionEstanteria implements Comparable<SolucionEstanteria>{
	public static SolucionEstanteria empty() {
		return new SolucionEstanteria();
	}
	
	private Map<Libro, Integer> m;
	
	private SolucionEstanteria() {
		this.m = new HashMap<Libro, Integer>();
	}
	
	private SolucionEstanteria(Map<Libro, Integer> m) {
		this.m = new HashMap<Libro, Integer>(m);
	}
	
	public Map<Libro, Integer> getS() {
		return new HashMap<Libro, Integer>(this.m);
	}
	
	public void add(Libro l, int n) {
		this.m.put(l, n);
	}
	
	
	public void remove(Libro l, int n) {
		this.m.remove(l, n);
	}
	
	public void remove(Libro l) {
		this.m.remove(l);
	}
	
	public SolucionEstanteria copy() {
		return new SolucionEstanteria(this.m);
	}
	
	public Integer getValor() {
		return m.keySet().stream()
				.mapToInt(x -> x.getAnchura() + x.getAltura())
				.sum();
	}

	@Override
	public String toString() {
		return "SolucionEstanteria [m=" + m + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
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
		SolucionEstanteria other = (SolucionEstanteria) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		return true;
	}

	@Override
	public int compareTo(SolucionEstanteria o) {
		return this.getValor().compareTo(o.getValor());
	}
	
	
	
}
