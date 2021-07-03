package ejercicio5AG;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Conjunto implements Comparable<Conjunto>{
	
	public static Conjunto create(Set<Integer> setElementos, Integer peso) {
		return new Conjunto(setElementos, peso);
	}

	public static Conjunto create(String s) {
		return new Conjunto(s);
	}
	
	private static Integer nCodigo = 0;
	
	private Integer codigo;
	private Set<Integer> setElementos;
	private Integer peso;
	
	
	private Conjunto(Set<Integer> setElementos, Integer peso) {
		super();
		this.codigo = nCodigo;
		nCodigo++;
		this.setElementos = setElementos;
		this.peso = peso;
	}
	
	private Conjunto(String s){		
		String[] v = s.split("},");
		Integer ne = v.length;
		if(ne != 2) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
		this.codigo = nCodigo;
		nCodigo++;
		this.setElementos =  Arrays.asList(v[0].substring(1,v[0].length()).split(","))
				.stream()
				.map(x -> Integer.parseInt(x))
				.collect(Collectors.toSet());
		this.peso = Integer.parseInt(v[1]);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Set<Integer> getSetElementos() {
		return setElementos;
	}

	public Integer getPeso() {
		return peso;
	}
	
	public Integer  getRatioElementosPeso() {
		return (setElementos.size())/peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Conjunto other = (Conjunto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	public int compareTo(Conjunto c) {
		return this.getRatioElementosPeso().compareTo(c.getRatioElementosPeso());
	}

	@Override
	public String toString() {
		return "(" + setElementos + ", " + peso + ")";
	}
	
	
}
