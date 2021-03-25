package projetXSLT;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import java.io.IOException;

/**
 * Classe permettant d'opérer des transformations XML-> HTML via XSLT
 */
public class HtmlGeneration {
	
	 public static void main( String[] args )
	    {
	        try {
	            convertToHtml("bibliotheque.xml", "to_html.xsl", "test.html");
	        } catch ( IOException | TransformerException | SAXException e) {
	            e.printStackTrace();
	        }
	    }
	
	/**
	 * Méthode permettant de convertir un fichier XML vers HTML via une transformation XSLT
	 * @param sourceXml - string - chemin du fichier source au format XML
	 * @param feuilleXsl - string - chemin de la feuille de style au format XSL
	 * @param sortieHtml - string - chemin du fichier de sortie HTML
	 */
	public static void convertToHtml(String sourceXml, String feuilleXsl, String sortieHtml)
			throws TransformerException, TransformerConfigurationException, 
		         SAXException, IOException	   
			{
			
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer(new StreamSource(feuilleXsl));

		    transformer.transform(new StreamSource(sourceXml), new StreamResult(sortieHtml));
		  }

}