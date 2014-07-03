package br.pereira.tests;

import java.io.File;

import br.grafo.Grafo;
import br.pereira.bfs.BFS;
import br.pereira.util.GerenteArquivos;

public class Test1 {

	public static void main(String[] args) throws Exception {
		System.out.println( "RUNNING: " + Test1.class.getName() + "\n\n" );

		File f = GerenteArquivos.getInstance().getOpenFile();
		Grafo g = new Grafo();
		g.loadFromFile( f );
		System.out.println( g );

		BFS bfs = new BFS( g );
//		bfs.setVerticeInicial( 1 );
		bfs.execute();
		System.out.println( bfs );
//		Dijkstra dj = new Dijkstra( g );
//		dj.execute( 0 );
//		System.out.println( dj );
	}
}
