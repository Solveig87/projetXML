package projetXSLT;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Classe regroupant des méthodes permettant de vérifier la bonne existence ou formation de fichiers.
 */
public class VerificationFichiers {
	
	/**
	 * Méthode permettant de vérifier qu'un fichier existe bien
	 * @param cheminFichier - string - chemin du fichier dont l'existence doit être vérifiée
	 * @return true si le fichier existe, false sinon
	 */	
	public static boolean fichierExiste(String cheminFichier) {
		 File fichier = new File(cheminFichier);
	     if(fichier.exists() && !fichier.isDirectory()) { 
	        return true;
		 }
	     return false;
	}
	
	/**
	 * Méthode permettant de parser un fichier devant suivre une structure html pour vérifier qu'il est bien formé/non vide
	 * Lève une SAXException si le fichier est mal formé
	 * @param cheminFichier - string - chemin du fichier à parser
	 * @return doc - Document - l'objet document du fichier parsé
	 */	
	public static Document parserXML( String cheminFichier) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dBF.newDocumentBuilder();
		InputSource is = new InputSource(cheminFichier);
		builder.setErrorHandler(null);
		Document doc = builder.parse(is);
		return doc;
	}

}
