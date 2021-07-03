package ejercicio2AEstrella;

import java.util.ArrayList;
import java.util.List;

import ejercicio2PD.DatosTarea;
import ejercicio2PD.Tarea;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class TareaVertex extends ActionVirtualVertex<TareaVertex, TareaEdge, Integer>{
	
	private Integer index;
	private List<Tarea> tareasCogidas;
	
	public static TareaVertex of() {
		return new TareaVertex(0, new ArrayList<Tarea>());
	}
	
	public static TareaVertex lastVertex() {
		return new TareaVertex(DatosTarea.getTareas().size(), 
				new ArrayList<Tarea>());
	}
	
	
	public static TareaVertex of(Integer index, List<Tarea> tareasCogidas) {
		return new TareaVertex(index, tareasCogidas);
	}
	
	private TareaVertex(Integer index, List<Tarea> arrayList) {
		super();
		this.index = index;
		this.tareasCogidas = arrayList;
	}

	public Integer getIndex() {
		return index;
	}

	public List<Tarea> getTareasCogidas() {
		return tareasCogidas;
	}

	@Override
	public Boolean isValid() {
		return index >= 0 && index <= DatosTarea.getTareas().size();
	}

	@Override
	public List<Integer> actions() {
		if(this.index == DatosTarea.getTareas().size()) return new ArrayList<>();
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		if(tareasCogidas.stream().allMatch(t -> !t.seSolapaBoolean(DatosTarea.getTarea(index)))) {
			res.add(1);
		}
		return res;
	}

	@Override
	public TareaVertex neighbor(Integer a) {
		List<Tarea> l = new ArrayList<Tarea>(tareasCogidas);
		if(a == 1) {
			l.add(DatosTarea.getTarea(index));
		}
		return TareaVertex.of(index+1, l);
	}
	
	public TareaEdge getEdgeFromAction(Integer a) {
		TareaVertex v = this.neighbor(a);
		return TareaEdge.of(this, v, a);
	}
	
	public static Double heuristic(TareaVertex v1, TareaVertex v2) {
		Double h = 0.;
		for (int i = v1.index; i < DatosTarea.getTareas().size(); i++) {
			h = h +  DatosTarea.getGanancia(i);
		}
		return -h;	//Max
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((tareasCogidas == null) ? 0 : tareasCogidas.hashCode());
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
		TareaVertex other = (TareaVertex) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (tareasCogidas == null) {
			if (other.tareasCogidas != null)
				return false;
		} else if (!tareasCogidas.equals(other.tareasCogidas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TareaVertex [index=" + index + ", tareasCogidas=" + tareasCogidas + "]";
	}

	
	
	
	
	
	
	
}
