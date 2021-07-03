package ejercicio2PD;

import java.util.HashMap;
import java.util.Map;

public class SolucionTarea {
	
	private Map<Integer, Tarea> m;

	public static SolucionTarea empty() {
		return new SolucionTarea();
	}
	
	private SolucionTarea() {
		super();
		this.m = new HashMap<Integer, Tarea>();	
	}
	
	private SolucionTarea(Map<Integer, Tarea> m) {
		super();
		this.m = new HashMap<Integer, Tarea>(m);	
	}

	public Map<Integer, Tarea> getM() {
		return m;
	}
	
	public void add(int i, Tarea t) {
		m.put(i, t);
	}
	
	public void remove(int i, Tarea t) {
		m.remove(i, t);
	}
	
	public SolucionTarea copy() {
		return new SolucionTarea(m);
	}

	@Override
	public String toString() {
		return "SolucionTarea [l=" + m + "]";
	}
	
	
	
	
}
