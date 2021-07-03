package ejercicio3PD;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPDR;

public class ProblemaTrayectosPD implements ProblemaPDR<SolucionTrayecto, Integer, ProblemaTrayectosPD> {
	
	private Integer estacionActual;
	private Integer destino;
	private Integer index;
	
	public static ProblemaTrayectosPD createInitial(Integer origen, Integer destino) {
		return new ProblemaTrayectosPD(origen, destino, 0);
	}
	
	private ProblemaTrayectosPD(Integer estacionActual, Integer destino, Integer index) {
		super();
		this.estacionActual = estacionActual;
		this.destino = destino;
		this.index = index;
	}

	public Integer getEstacionActual() {
		return estacionActual;
	}

	public Integer getDestino() {
		return destino;
	}

	public Integer getIndex() {
		return index;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}

	@Override
	public int size() {
		return DatosTrayecto.getTrayectos().size() - index;
	}

	@Override
	public boolean esCasoBase() {
		return (estacionActual == destino);	
	}

	@Override
	public List<Integer> getAlternativas() {	//index trayectorias de la estacion actual
		List<Integer> res = IntStream.range(0, DatosTrayecto.getTrayectos().size())
				.filter(i -> DatosTrayecto.getTrayecto(i).getOrigen() == estacionActual)
				.filter(i -> DatosTrayecto.getTrayecto(i).getDestino() <= destino)
				.boxed()
				.collect(Collectors.toList());
		return res;
	}

	@Override
	public ProblemaTrayectosPD getSubProblema(Integer a) {
		return new ProblemaTrayectosPD(DatosTrayecto.getTrayecto(a).getDestino(), destino, a);
	}

	@Override
	public Sp<Integer> getSolucionParcialCasoBase() {
		return Sp.create(null, 0.);
	}

	@Override
	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> s) {
		Double res = s.valorDeObjetivo + DatosTrayecto.getTrayecto(a).getCoste();
		return Sp.create(a, res);
	}

	@Override
	public SolucionTrayecto getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		return SolucionTrayecto.create(index, DatosTrayecto.getTrayecto(index));
	}

	@Override
	public SolucionTrayecto getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionTrayecto s) {
		s.add(sp.alternativa, DatosTrayecto.getTrayecto(sp.alternativa));
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estacionActual == null) ? 0 : estacionActual.hashCode());
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
		ProblemaTrayectosPD other = (ProblemaTrayectosPD) obj;
		if (estacionActual == null) {
			if (other.estacionActual != null)
				return false;
		} else if (!estacionActual.equals(other.estacionActual))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}


	
	
}
