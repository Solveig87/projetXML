package projetXSLT;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe permettant d'opérer des transformations XML-> HTML via XSLT
 */
public class HtmlGeneration {
	
	/**
	 * Méthode permettant de convertir un fichier XML vers HTML via une transformation XSLT
	 * @param sourceXml - string - chemin du fichier source au format XML
	 * @param feuilleXsl - string - chemin de la feuille de style au format XSL
	 * @param sortieHtml - string - chemin du fichier de sortie HTML
	 */
	public static void convertToHtml(String sourceXml, String feuilleXsl, String sortieHtml)
			throws TransformerConfigurationException, TransformerException,
		         SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException	   
				{
		
			// Vérification que le fichier d'entrée existe
        	if( !VerificationFichiers.fichierExiste(sourceXml) ) { 
        		throw new FileNotFoundException("ERREUR - Fichier d'entrée non trouvé : " + sourceXml);
        	}
     	        
        	// Vérification que la feuille XSL existe
        	if( !VerificationFichiers.fichierExiste(feuilleXsl) ) { 
        		throw new FileNotFoundException("ERREUR - Feuille XSL non trouvée : " + feuilleXsl);
        	} 
        
        	//Vérification que le fichier d'entrée est bien formé et non vide
        	try {
        		VerificationFichiers.parserXML(sourceXml);
			}
        	catch (SAXParseException e) {
        		throw new BadlyFormedXMLException("ERREUR FATALE - Le fichier d'entrée " + sourceXml + " est mal formé : \n" + e.getMessage());
        	}
		
        	//Vérification que la feuille de style est bien formée et non vide
        	try {
        		VerificationFichiers.parserXML(feuilleXsl);
			}
        	catch (SAXParseException e) {
        		throw new BadlyFormedXMLException("ERREUR FATALE - La feuille de style " + feuilleXsl + " est mal formée : \n" + e.getMessage());
        	}
			
        	//Transformation 
			TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer(new StreamSource(feuilleXsl));
		    
		    //Cas d'une feuille de style XSLT non fonctionnelle : le transformer sera null
		    if (transformer == null) {
		    	throw new BadlyFormedXSLException("ERREUR FATALE - La feuille de style " + sourceXml + " est mal formée (non fonctionnelle)!");
		    }
		    
		    transformer.transform(new StreamSource(sourceXml), new StreamResult(sortieHtml));
		  }

}