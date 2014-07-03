package br.pereira.bfs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;

public class BFS extends Grafo {

	public BFS( Grafo g ) throws Exception {
		super();
		init( g );
	}
	
	public String toString() {
		String msg = "BFS.toString()\n";
		msg += super.toString();
		String duStr = "d[u] = { ";
		String piStr = "pi[u] = { ";
		for( Vertice v : vertices ) {
			duStr += "d[" + v + "]:" + v.info.du + ", ";
			piStr += "pi[" + v + "]:" + v.info.pi + ", ";
		}
		duStr = duStr.substring( 0, duStr.length() - 2 ) + " };";
		piStr = piStr.substring( 0, piStr.length() - 2 ) + " };";
		
		msg += duStr + "\n";
		msg += piStr + "\n";
		return msg;
	}

	public void init( int i ) {
		for( Vertice v : vertices ) {
			v.info.color = Color.WHITE;
			v.info.du = 999;
			v.info.pi = null;
		}
		
		Vertice s = vertices.get( i );
		s.info.color = Color.GRAY;
		s.info.du = 0;
	}
	
	public void execute() {
		init( verticeInicial );
		List<Vertice> Q = new ArrayList<Vertice>();
		Q.add( vertices.get( verticeInicial ) );
		
		while( !Q.isEmpty() ) {
			Vertice u = Q.remove( 0 );
			for( Aresta a : u.getAdjacentes() ) {
				Vertice v = ( a.v[0] == u ) ? a.v[1] : a.v[0];
				if( v.info.color != Color.WHITE )
					continue;
				v.info.color = Color.GRAY;
				v.info.du = u.info.du + 1;
				v.info.pi = u;
				Q.add( v );
			}
			u.info.color = Color.BLACK;
		}
	}

	
	
}
