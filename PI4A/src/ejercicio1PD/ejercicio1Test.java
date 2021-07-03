package ejercicio1PD;

import us.lsi.pd.AlgoritmoPD;

//import us.lsi.pd.AlgoritmoPD;

public class ejercicio1Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosEstanteria.iniDatos("ficheros/PI4AEj1DatosEntrada5.txt");
		
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = false;
		AlgoritmoPD.metricasOK = true;
		
		System.out.println(DatosEstanteria.getLibros());
		ProblemaEstanteriaPD p = ProblemaEstanteriaPD.createInitial();
		System.out.println("Problema inicial: " + p.toString());
		var a = AlgoritmoPD.createPDR(p);
		a.ejecuta();
		System.out.println(AlgoritmoPD.metricas.toString());
		System.out.println("Solucion= "+a.getSolucion());
		
		
	}

}
