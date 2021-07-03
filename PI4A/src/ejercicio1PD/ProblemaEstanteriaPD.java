package ejercicio1PD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPDR;

public class ProblemaEstanteriaPD implements ProblemaPDR<SolucionEstanteria, Integer, ProblemaEstanteriaPD>{

	private Integer index;
	private List<Integer> anchurasRestante;
	
	 public static ProblemaEstanteriaPD createInitial() {
		 return new ProblemaEstanteriaPD(0, DatosEstanteria.getListaAnchuras());
	 }
	 
	 public static ProblemaEstanteriaPD create(ProblemaEstanteriaPD p, Integer a) {
		 return new ProblemaEstanteriaPD(p, a);
	 }
	
	private ProblemaEstanteriaPD(Integer index, List<Integer> anchurasRestante) {
		super();
		this.index = index;
		this.anchurasRestante = anchurasRestante;
	}
	
	private ProblemaEstanteriaPD(ProblemaEstanteriaPD p, Integer a) {
		super();
		List<Integer> aux = new ArrayList<Integer>(p.anchurasRestante);
		if(a > -1) {
			int n = aux.get(a) - DatosEstanteria.getAnchuraLibro(p.index);
			aux.set(a, n);
		}
		this.anchurasRestante = aux;
		this.index = p.index + 1;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.Max;
	}

	@Override
	public int size() {
		return DatosEstanteria.getLibros().size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return (this.index == DatosEstanteria.getLibros().size());
	}
	
	@Override
	public List<Integer> getAlternativas() {
		List<Integer> res = DatosEstanteria.getEstantes()
				.stream()
				.filter(p1 -> p1.getAltura() >= DatosEstanteria.getAlturaLibro(index))
				.filter(p2 -> anchurasRestante.get(p2.getId()) >= DatosEstanteria.getAnchuraLibro(index))
				.map(e -> e.getId())
				.collect(Collectors.toList());
		res.add(-1);
		
//		List<Integer> res = new ArrayList<Integer>();
//		for (int i = 0; i < DatosEstanteria.getNumeroEstantes(); i++) {
//			if(DatosEstanteria.getLibros().get(index).getAltura() <= DatosEstanteria.getAlturaEstante(i) &&
//					DatosEstanteria.getLibros().get(index).getAnchura() <= anchurasRestante.get(i)) {
//				res.add(i);
//			}
//		}
//		res.add(-1);
		return res;
	}

	@Override
	public ProblemaEstanteriaPD getSubProblema(Integer a) {
		return ProblemaEstanteriaPD.create(this, a);
	}
	
	@Override
	public Sp<Integer> getSolucionParcialCasoBase() {
		return Sp.create(null, 0.);
	}
	

	@Override
	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> s) {
		Double n = s.valorDeObjetivo;
		if(a > -1) {
			n = n+1;
		}
		return Sp.create(a, n);
	}

	

	@Override
	public SolucionEstanteria getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		return SolucionEstanteria.empty();
	}

	@Override
	public SolucionEstanteria getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionEstanteria s) {
		s.add(DatosEstanteria.getLibros().get(this.index), sp.alternativa);
		return s;
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
		ProblemaEstanteriaPD other = (ProblemaEstanteriaPD) obj;
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
	public String toString() {
		return "ProblemaEstanteriaPD [index=" + index + ", anchurasRestante=" + anchurasRestante + "]";
	}
	
	
	
}
