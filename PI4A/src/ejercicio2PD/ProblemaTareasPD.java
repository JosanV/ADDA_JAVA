package ejercicio2PD;

import java.util.ArrayList;
import java.util.List;

import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPDR;

public class ProblemaTareasPD implements ProblemaPDR<SolucionTarea, Integer, ProblemaTareasPD>{
	
	private Integer index;
	private List<Tarea> tareasCogidas;
	
	public static ProblemaTareasPD createInitial() {
		return new ProblemaTareasPD(0, new ArrayList<Tarea>());
	}
	
	public static ProblemaTareasPD create(ProblemaTareasPD p, Integer a) {
		return new ProblemaTareasPD(p, a);
	}
	
	public Integer getIndex() {
		return index;
	}

	public List<Tarea> getTareasCogidas() {
		return new ArrayList<Tarea>(tareasCogidas);
	}

	private ProblemaTareasPD(Integer index, List<Tarea> tareasCogidas) {
		super();
		this.index = index;
		this.tareasCogidas = tareasCogidas;
	}
	
	private ProblemaTareasPD(ProblemaTareasPD p, Integer a) {
		super();
		List<Tarea> l = new ArrayList<Tarea>(p.tareasCogidas);
		if(a == 1) {
			l.add(DatosTarea.getTarea(p.index));
		}
		this.tareasCogidas = l;
		this.index = p.index+1;
		
	}

	@Override
	public Tipo getTipo() {
		return Tipo.Max;
	}

	@Override
	public int size() {
		return DatosTarea.getTareas().size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return index == DatosTarea.getTareas().size();
	}

	@Override
	public List<Integer> getAlternativas() {
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		if(tareasCogidas.stream().allMatch(t -> !t.seSolapaBoolean(DatosTarea.getTarea(index)))) {
			res.add(1);
		}
		return res;
	}

	@Override
	public ProblemaTareasPD getSubProblema(Integer a) {
		return ProblemaTareasPD.create(this, a);
	}

	@Override
	public Sp<Integer> getSolucionParcialCasoBase() {
		return Sp.create(null, 0.);
	}

	@Override
	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> s) {
		Double n = s.valorDeObjetivo + DatosTarea.getGanancia(index)*a;	//Si no lo ha cogido es *0
		return Sp.create(a, n);
	}

	@Override
	public SolucionTarea getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		return SolucionTarea.empty();
	}

	@Override
	public SolucionTarea getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionTarea s) {
		if(sp.alternativa == 1) {
			s.add(this.index+1, DatosTarea.getTarea(this.index));
		}
		
		return s;
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
		ProblemaTareasPD other = (ProblemaTareasPD) obj;
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
		return "ProblemaTareasPD [index=" + index + ", tareasCogidas=" + tareasCogidas + "]";
	}
	
	
	
	
}
