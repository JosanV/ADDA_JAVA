package ejercicio3PD;

import java.util.Map;
import java.util.TreeMap;

public class SolucionTrayecto {
	
	private Map<Integer,Trayecto> m;

	public static SolucionTrayecto empty() {
		return new SolucionTrayecto();
	}
	
	public static SolucionTrayecto create(Integer index, Trayecto t) {
		return new SolucionTrayecto(index, t);
	}
	
	private SolucionTrayecto() {
		super();
		this.m = new TreeMap<Integer, Trayecto>();
	}
	
	private SolucionTrayecto(Map<Integer,Trayecto> m) {
		super();
		this.m = new TreeMap<Integer, Trayecto>(m);
	}
	
	private SolucionTrayecto(Integer index, Trayecto t) {
		super();
		Map<Integer, Trayecto> res = new TreeMap<Integer, Trayecto>();
		res.put(index, t);
		this.m = res;
	}
	
	
	public void add(Integer index, Trayecto t) {
		this.m.put(index, t);
	}
	
	public void remove(Integer index, Trayecto t) {
		this.m.remove(index, t);
	}
	
	public Map<Integer,Trayecto> getM(){
		return m;
	}
	
	public SolucionTrayecto copy() {
		return new SolucionTrayecto(m);
	}

	@Override
	public String toString() {
		return "SolucionTrayecto [m=" + m + "]";
	}
	
}
