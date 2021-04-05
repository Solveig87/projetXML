package projetXSLT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Classe permettant d'opérer des transformations XML-> PDF via XSLT
 */

public class PdfGeneration {
	
	/**
	 * Méthode permettant de convertir un fichier XML vers PDF via une transformation XSLT
	 * @param input - string - chemin du fichier source au format XML
	 * @param xslt - string - chemin de la feuille de style au format XSL
	 * @param output - string - chemin du fichier de sortie PDF
	 */

    public static void convertToPDF(String input, String xslt, String output) throws IOException, BadlyFormedXMLException, ParserConfigurationException, TransformerException, SAXException {
        File xsltFile = new File(xslt);
        
        // Vérification que le fichier d'entrée existe
        if( !VerificationFichiers.fichierExiste(input) ) { 
        	throw new FileNotFoundException("ERREUR - Fichier d'entrée non trouvé : " + input);
	    }
     	        
     	// Vérification que la feuille XSL existe
     	 if( !VerificationFichiers.fichierExiste(xslt) ) { 
     		throw new FileNotFoundException("ERREUR - Feuille XSL non trouvée : " + xslt);
     	} 
        
     	//Vérification que le fichier d'entrée est bien formé et non vide
		try {
			VerificationFichiers.parserXML(input);
			}
		catch (SAXParseException e) {
			throw new BadlyFormedXMLException("ERREUR FATALE - Le fichier d'entrée " + input + " est mal formé : \n" + e.getMessage());
		}
		
		//Vérification que la feuille de style est bien formée et non vide
		try {
			VerificationFichiers.parserXML(xslt);
			}
		catch (SAXParseException e) {
			throw new BadlyFormedXMLException("ERREUR FATALE - La feuille de style " + xslt + " est mal formée : \n" + e.getMessage());
		}
        
		//Transformation 
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
        
        //Cas d'une feuille de style XSLT non fonctionnelle : le transformer sera null
	    if (transformer == null) {
	    	throw new BadlyFormedXSLException("ERREUR FATALE - La feuille de style " + xslt + " est mal formée (non fonctionnelle)!");
	    }
	    
	    OutputStream out = new java.io.FileOutputStream(output);
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
        Result res = new SAXResult(fop.getDefaultHandler());

        transformer.transform(new StreamSource(input), res);
        out.close();
    }
}