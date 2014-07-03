package br.grafo;


public class Aresta implements Comparable<Aresta> {
	
	public Vertice v[] = { null, null };
//	public ArestaInfo info = new ArestaInfo();
	public float peso = 0;
	
	
	public String toString() {
		String msg;
//		msg = "[" + v[0].label + "--" + v[1].label + "]:"+peso;
		msg = v[0].label + "-" + v[1].label + ":"+peso;
		return msg;
	}
	
	public Aresta setInfo( Vertice v1, Vertice v2, float w ) {
		v[0] = v1;
		v[1] = v2;
		peso = w;
		return this;
	}
	
	public void transpose() {
		Vertice tmp;
		tmp = v[0];
		v[0] = v[1];
		v[1] = tmp;
	}
	
	public int compareTo(Aresta a) {
		if( peso == a.peso )
			return 0;
		else if( peso < a.peso )
			return -1;
		else
			return 1;
	}
}
