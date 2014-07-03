package br.pereira.util;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class GerenteArquivos {

	public static final GerenteArquivos instance = new GerenteArquivos();
	
	JFileChooser fc;
	File baseDir;
	protected GerenteArquivos() {
		baseDir = new File( System.getProperty( "user.dir" ) );
		fc = new JFileChooser( "." );
	}
	
	Component parent = null;

	public static GerenteArquivos getInstance() {
		return instance;
	}

	public void setParent( Component umComponente ) {
		parent = umComponente;
	}

	public File selecionaDiretorio() throws IOException {
		fc.setMultiSelectionEnabled( false );
		selectFile( JFileChooser.DIRECTORIES_ONLY );
		return fc.getCurrentDirectory();
	}
	
	public File getOpenFile() throws IOException {
		return getFile( JFileChooser.OPEN_DIALOG );
	}

	public File getSaveFile() throws IOException {
		return getFile( JFileChooser.SAVE_DIALOG );
	}

	public File getFile( int dialogType ) throws IOException {
		fc.setMultiSelectionEnabled( false );
		selectFile( dialogType );
		return fc.getSelectedFile();
	}
	
	public void selectFile( int dialogType ) throws IOException {
		fc.setDialogType( dialogType );
		fc.setCurrentDirectory( baseDir );
		if ( fc.showSaveDialog( parent ) != JFileChooser.APPROVE_OPTION) {
			throw new IOException();
		}
	}
	
	public File[] carregaArquivos() throws Exception  {
		fc.setMultiSelectionEnabled(true);
		selectFile( JFileChooser.OPEN_DIALOG );
		return fc.getSelectedFiles();
	}	
}
