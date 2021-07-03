package ejercicio2PD;

import java.time.Duration;
import java.time.LocalTime;

public class Tarea implements Comparable<Tarea>{
	
	private Integer ganancia;
	private  Duration duracion;
	private  LocalTime inicio;
	
	
	public Tarea(Integer ganancia, Duration duracion, LocalTime inicio) {
		super();
		this.ganancia = ganancia;
		this.duracion = duracion;
		this.inicio = inicio;
	}

	public Tarea(String s) {
		String[] v = s.split(",");
		Integer ne = v.length;
		if(ne != 3) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);
		
		LocalTime aux = LocalTime.parse(v[1].trim());
		int duracionRes = (aux.getHour()*60) + aux.getMinute();
		
		inicio = LocalTime.parse(v[0].trim());
		duracion = Duration.ofMinutes(duracionRes);
		ganancia = Integer.parseInt(v[2].trim());
	}


	public static Tarea create(Integer ganancia,Duration duracion, LocalTime inicio) {
		return new Tarea(ganancia, duracion, inicio);
	}

	public static Tarea create(String s) {
		return new Tarea(s);
	}
	
	public Integer getGanancia() {
		return ganancia;
	}
	
	public  Duration getDuracion() {
		return duracion;
	}
	
	public  LocalTime getInicio() {
		return inicio;
	}
	
	public LocalTime getFinal() {
		return inicio.plus(duracion);
	}
	
	public Double getRatioGananciaDuracion() {
		return (double) ganancia/duracion.toMinutes();
	}
	
	public Boolean seSolapaBoolean(Tarea t) {
		Boolean res = true;
		if(this.getInicio().isBefore(t.getInicio()) && this.getFinal().isBefore(t.getInicio())) {
			res = false;
		} else if(this.getInicio().isAfter(t.getFinal()) && this.getFinal().isAfter(t.getFinal())) {
			res = false;
		}
//		else if(this.getInicio().equals(t.getFinal())) {
//			res = false;
//		} else if(this.getFinal().equals(t.getInicio())) {
//			res = false;
//		}
		return res;
	}
	
	public Integer seSolapaInt(Tarea t) {
		Integer res = 1;
		if(this.getInicio().isBefore(t.getInicio()) && this.getFinal().isBefore(t.getInicio())) {
			res = 0;
		} else if(this.getInicio().isAfter(t.getFinal()) && this.getFinal().isAfter(t.getFinal())) {
			res = 0;
		} 
//		else if(this.getInicio().equals(t.getFinal())) {
//			res = 0;
//		} else if(this.getFinal().equals(t.getInicio())) {
//			res = 0;
//		}
		return res;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
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
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		return true;
	}

	public int compareTo(Tarea o) {
		return this.getGanancia().compareTo(o.getGanancia());
	}

	@Override
	public String toString() {
		return "(Hora de inicio = " + inicio + "; Hora de fin = " + getFinal() + "; Ganancia = " +  ganancia + ")";
	}
	
	
	
	
	

}
