package br.pereira.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;

public class Dijkstra extends Grafo {
	public Dijkstra( Grafo g ) throws Exception {
		super();
		init( g.getArestas(), g.getVertices() );
	}
	
	public void initializeSingleSource( int index ) {
		for( Vertice v : vertices ) {
			v.info.du = 999;
			v.info.pi = null;
		}
		Vertice u = vertices.get( index );
		u.info.du = 0;
	}
	
	public void initializeSingleSource( String label ) {
		Vertice u = null;
		for( Vertice v : vertices ) {
			v.info.du = 999;
			v.info.pi = null;
			if( v.label.equals( label ) ) {
				u = v;
			}
		}
		u.info.du = 0;
	}
	
	public void relax( Vertice u, Vertice v, float w ) {
		if( v.info.du > u.info.du + w ) {
			v.info.du = u.info.du + w;
			v.info.pi = u;
		}
	}
	
	public void execute( int id ) {
		initializeSingleSource( id );
		List<Vertice> S = new ArrayList<Vertice>();
		List<Vertice> Q = new ArrayList<Vertice>();
		Q.addAll( vertices );
		while( Q.size() > 0 ) {
			Collections.sort( Q );
			Vertice u = Q.remove( 0 );
			S.add( u );
			for( Aresta a : u.getAdjacentes() ) {
				Vertice v = a.v[1];
				float w = a.peso;
				relax( u, v, w );
			}
		}
		Collections.sort( vertices );
	}
	

	public String toString() {
		String msg = "Dijkstra.toString()\n";
		msg += "V["+ vertices.size() + "] = " + vertices + "\n";
		msg += "E["+ arestas.size() + "] = " + arestas + "\n";
		return msg;
	}
}
