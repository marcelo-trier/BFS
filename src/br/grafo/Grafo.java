package br.grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Grafo {
//	private static final char NOME_VERTICE[] = { 'r', 's', 't', 'u', 'v', 'w', 'x', 'y' };
//	private char NOME_VERTICES[] = { 's', 'x', 'z', 'w', 'y', 't' };
	private char NOME_VERTICES[];
	private String nomes;
	protected int verticeInicial = 0;
	boolean directed = true; // este grafo eh direcionado??
	
	protected List<Vertice> vertices = new ArrayList<Vertice>();
	protected List<Aresta> arestas = new ArrayList<Aresta>();


	public void setVerticeInicial( int i ) {
		verticeInicial = i;
	}
	
	public String toString() {
		String msg = "Printing G = { V, E }\n";
		msg += "V[" + vertices.size() + "] = " + vertices + "\n";
		msg += "E[" + arestas.size() + "] = " + arestas + "\n";
		return msg;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void transpose() {
		for (Aresta a : arestas) {
			a.transpose();
		}
	}

	public void init( Grafo g ) throws Exception {
		init( g.getArestas(), g.getVertices() );
		NOME_VERTICES = g.NOME_VERTICES;
		nomes = g.nomes;
		verticeInicial = g.verticeInicial;
		directed = g.directed;
		
	}
	
	public void init(List<Aresta> la, List<Vertice> lv) throws Exception {
		if (la == null || lv == null)
			return;

		arestas.clear();
		vertices.clear();
		arestas.addAll( la );
		vertices.addAll( lv );

	}

	public static Aresta getAresta( List<Aresta> la, boolean direct , Vertice v1, Vertice v2, float w ) {
		for( Aresta a : la ) {
			if ( !direct && (v1 == a.v[0] || v1 == a.v[1]) && (v2 == a.v[0] || v2 == a.v[1])
					&& a.peso==w ) {
				return a;
			}

			if( direct && v1==a.v[0] && v2==a.v[1] && a.peso==w )
				return a;
		}
		return null;
	}
	
	public Aresta getAresta(Vertice v1, Vertice v2, float w ) {
		return getAresta( arestas, directed, v1, v2, w );
	}

	public void addAresta(Vertice v1, Vertice v2, float w ) throws Exception {
		if (getAresta(v1, v2, w) == null) {
			Aresta a = new Aresta();
			a.setInfo(v1, v2, w);
			v1.addAdj(a);
			if (!directed)
				v2.addAdj(a);
			arestas.add(a);
		}
	}

	public void buildAdjacentes() throws Exception {
		List<Aresta> tmp = new ArrayList<Aresta>();

		for (Vertice vertex : vertices) {
			tmp.clear();
			for (Aresta a : arestas) {
				if( getAresta( tmp, directed, a.v[0], a.v[1], a.peso ) != null ) {
					continue;
				}

				if( a.v[0].equals( vertex ) )
					tmp.add( a );
				else
					if( !directed && a.v[1].equals( vertex ) )
						tmp.add( a );
			}
			vertex.adjacentes.clear();
			vertex.adjacentes.addAll(tmp);
		}
	}

	public void criaVertices() throws Exception {
		vertices.clear();
		String label;
		for( int i=0; i<nomes.length(); i++ ) {
			label = "" + nomes.charAt( i );
			Vertice v = new Vertice( i, label );
			vertices.add( v );
		}
	}

	public void criaVertices(int len) throws Exception {
		boolean nomesDefinidos = false;

/*		NOME_VERTICES = new char[ nomes.length() ];
		for( int i=0; i<nomes.length(); i++ ) {
			NOME_VERTICES[ i ] = nomes.charAt( i );
		} */
		
		if (len == NOME_VERTICES.length) {
			nomesDefinidos = true;
		}

		vertices.clear();
		String label;
		char tmp = 'a';
		for (int i = 0; i < len; i++) {
			if (nomesDefinidos)
				label = "" + NOME_VERTICES[i];
			else
				label = "" + tmp++;
			Vertice v = new Vertice( i, label );
			vertices.add(v);
		}
	}
	
	public void loadFromFile(File f) throws Exception {
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String linha;
		int numeroLinha = -1;

		while ((linha = br.readLine()) != null) {
			linha = linha.trim();
			if (linha.equals("") || linha.charAt(0) == '#') {
				continue;
			} 
			else if( linha.charAt(0) == '@' ) { // nomes
				nomes = linha.substring( 1 );
				if (vertices.size() <= 0)
					criaVertices();
				continue; 
			}
			else if( linha.charAt( 0 ) == '*' ) {
				String tmp = linha.substring( 1 );
				verticeInicial = Integer.parseInt( tmp );
				continue;
			}
			numeroLinha++;
			String[] tokens = linha.split("\\s+"); // pega qualquer coisa:
													// espaço, tab, quebra de
													// linha, etc..

			for (int numeroColuna = 0; numeroColuna < tokens.length; numeroColuna++) {
				float peso = Float.parseFloat(tokens[numeroColuna]);
				if ( peso > 0 ) {
					Vertice v1, v2;
					v1 = vertices.get(numeroLinha);
					v2 = vertices.get(numeroColuna);
					addAresta( v1, v2, peso );
				}
			}
		}

		br.close();
	}
	
	
}
