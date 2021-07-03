package ejercicio6PLI;

import java.time.LocalTime;

public class Tarea implements Comparable<Tarea>{
	
	private Integer ganancia;
	private  LocalTime duracion;
	private  LocalTime inicio;
	
	
	public Tarea(Integer ganancia, LocalTime duracion, LocalTime inicio) {
		super();
		this.ganancia = ganancia;
		this.duracion = duracion;
		this.inicio = inicio;
	}

	public Tarea(String s) {
		String[] v = s.split(",");
		Integer ne = v.length;
		if(ne != 3) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
		
		inicio = LocalTime.parse(v[0].trim());
		duracion = LocalTime.parse(v[1].trim());;
		ganancia = Integer.parseInt(v[2].trim());
	}


	public static Tarea create(Integer ganancia,LocalTime duracion, LocalTime inicio) {
		return new Tarea(ganancia, duracion, inicio);
	}

	
	public static Tarea create(String s) {
		return new Tarea(s);
	}
	
	public Integer getGanancia() {
		return ganancia;
	}
	public  LocalTime getDuracion() {
		return duracion;
	}
	public  LocalTime getInicio() {
		return inicio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ganancia == null) ? 0 : ganancia.hashCode());
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
		Tarea other = (Tarea) obj;
		if (ganancia == null) {
			if (other.ganancia != null)
				return false;
		} else if (!ganancia.equals(other.ganancia))
			return false;
		return true;
	}
	
	
	public int compareTo(Tarea o) {
		return this.getGanancia().compareTo(o.getGanancia());
	}

	@Override
	public String toString() {
		return "(" + inicio + "," + duracion + "," +  ganancia + ")";
	}
	
	
	
	
	

}
