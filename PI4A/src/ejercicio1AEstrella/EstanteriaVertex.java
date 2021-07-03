package ejercicio1AEstrella;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio1PD.DatosEstanteria;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class EstanteriaVertex extends ActionVirtualVertex<EstanteriaVertex, EstanteriaEdge, Integer>{
	
	private Integer index;
	private List<Integer> anchurasRestante;
	
	public static EstanteriaVertex of() {
		return new EstanteriaVertex(0, DatosEstanteria.getListaAnchuras());
	}
	
	public static EstanteriaVertex lastVertex() {
		return new EstanteriaVertex(DatosEstanteria.getLibros().size(), 
				IntStream.range(0, DatosEstanteria.getNumeroEstantes()).boxed().map(x -> x = 0).collect(Collectors.toList()));
	}
	
	
	public static EstanteriaVertex of(Integer index, List<Integer> anchurasRestante) {
		return new EstanteriaVertex(index, anchurasRestante);
	}
	
	private EstanteriaVertex(Integer index, List<Integer> anchurasRestante) {
		super();
		this.index = index;
		this.anchurasRestante = anchurasRestante;
	}

	public Integer getIndex() {
		return index;
	}

	public List<Integer> getAnchurasRestante() {
		return anchurasRestante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anchurasRestante == null) ? 0 : anchurasRestante.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
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
		EstanteriaVertex other = (EstanteriaVertex) obj;
		if (anchurasRestante == null) {
			if (other.anchurasRestante != null)
				return false;
		} else if (!anchurasRestante.equals(other.anchurasRestante))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}

	@Override
	public Boolean isValid() {
		return index >= 0 && index <= DatosEstanteria.getLibros().size();
	}

	@Override
	public List<Integer> actions() {
		if(this.index == DatosEstanteria.getLibros().size()) return new ArrayList<>();
		List<Integer> res = DatosEstanteria.getEstantes()
				.stream()
				.filter(p1 -> p1.getAltura() >= DatosEstanteria.getAlturaLibro(index))
				.filter(p2 -> anchurasRestante.get(p2.getId()) >= DatosEstanteria.getAnchuraLibro(index))
				.map(e -> e.getId())
				.collect(Collectors.toList());
		res.add(-1);
		return res;
	}

	@Override
	public EstanteriaVertex neighbor(Integer a) {
		List<Integer> aux = new ArrayList<Integer>(this.anchurasRestante);
		if(a > -1) {
			int n = aux.get(a) - DatosEstanteria.getAnchuraLibro(index);
			aux.set(a, n);
		}
		return EstanteriaVertex.of(index+1, aux);
	}
	
	public EstanteriaEdge getEdgeFromAction(Integer a) {
		EstanteriaVertex v = this.neighbor(a);
		return EstanteriaEdge.of(this, v, a);
	}
	
	public static Double heuristic(EstanteriaVertex v1, EstanteriaVertex v2) {
		Double h = 0.;
		List<Integer> anchurasRestantesAux = new ArrayList<Integer>(v1.anchurasRestante);
		int total = anchurasRestantesAux.stream().mapToInt(i -> i.intValue()).sum();
		for (int i = v1.index; i < DatosEstanteria.getLibros().size(); i++) {
			if(DatosEstanteria.getAnchuraLibro(i) <= total) {
				total = total - DatosEstanteria.getAnchuraLibro(i);
				h = h+1;
			} else {
				break;
			}
			
		}
		return -h;	//Max
	}

	@Override
	public String toString() {
		return "[index=" + index + ", anchurasRestante=" + anchurasRestante + "]";
	}
	
	
	
	
	
}
