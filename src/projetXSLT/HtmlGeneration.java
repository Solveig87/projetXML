package projetXSLT;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.Document;
import org.xml.sax.*;

import java.io.File;
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
			throws TransformerException, TransformerConfigurationException, 
		         SAXException, IOException, BadlyFormedXMLException	   
				{
			File fichierXML = new File(sourceXml);
	        if(!fichierXML.exists() || fichierXML.isDirectory()) { 
	            throw new FileNotFoundException();
	        }
	        
			try {
				// Create a new factory to create parsers
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				// Use the factory to create a parser (builder) and use it to parse the document.
				DocumentBuilder builder = dBF.newDocumentBuilder();

				InputSource is = new InputSource(sourceXml);
				Document doc = builder.parse(is);
				}
				catch (Exception e) {
					throw new BadlyFormedXMLException(sourceXml + " est mal formé.");
				}
			
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer(new StreamSource(feuilleXsl));

		    transformer.transform(new StreamSource(sourceXml), new StreamResult(sortieHtml));
		  }

}