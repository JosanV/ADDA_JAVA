package ejercicio7AG;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conjunto{
	
	public static Conjunto create(List<Integer> elementos, Integer resultado) {
		return new Conjunto(elementos, resultado);
	}

	public static Conjunto create(String s) {
		return new Conjunto(s);
	}
	
	private static Integer nCodigo = 0;
	
	private Integer codigo;
	private List<Integer> elementos;
	private Integer resultado;
	
	
	private Conjunto(List<Integer> elementos, Integer resultado) {
		super();
		this.codigo = nCodigo;
		nCodigo++;
		this.elementos = elementos;
		this.resultado = resultado;
	}
	
	private Conjunto(String s){		
		String[] v = s.split(":");
		Integer ne = v.length;
		if(ne != 2) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
		this.codigo = nCodigo;
		nCodigo++;
		this.elementos =  Arrays.asList(v[0].split(","))
				.stream()
				.map(x -> Integer.parseInt(x))
				.collect(Collectors.toList());
		this.resultado = Integer.parseInt(v[1]);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public List<Integer> getElementos() {
		return elementos;
	}

	public Integer getResultado() {
		return resultado;
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
	
	
	@Override
	public String toString() {
		return "Conjunto [elementos=" + elementos + ", resultado=" + resultado + "]";
	}
	
	
}
