package projetXSLT;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * Classe permettant d'opérer des transformations XML-> PDF ou HTML via XSLT
 */

public class TransformateurBibli {
	
	private String htmlStylesheet;
	private String pdfStylesheet;
	
	public TransformateurBibli() {
		this.setHtmlStylesheet(ProjetConstantes.FEUILLEHTML);
		this.setPdfStylesheet(ProjetConstantes.FEUILLEPDF);
	}

	/**
	 * Méthode permettant de convertir un fichier XML vers HTML ou PDF via une transformation XSLT
	 * @param input - string - chemin du fichier source au format XML
	 * @param out - string - chemin du fichier de sortie sans son extension
	 * @param format - string - format du fichier de sortie : pdf ou html
	 */	
	
	public void convertXML(String input, String out, String format) throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException  {
		String output = out + "." + format;
		if (format.equals("pdf")) {
        	PdfGeneration.convertToPDF(input, pdfStylesheet, output);
		}
	    else if (format.equals("html")) {
        	HtmlGeneration.convertToHtml(input, htmlStylesheet, output);
	    } 
	    else {
	    	throw new FormatException("Le format spécifié \"" + format + "\" n'est pas valide.");
	    }
	}

	public String getHtmlStylesheet() {
		return htmlStylesheet;
	}

	public void setHtmlStylesheet(String htmlStylsheet) {
		this.htmlStylesheet = htmlStylsheet;
	}

	public String getPdfStylesheet() {
		return pdfStylesheet;
	}

	public void setPdfStylesheet(String pdfStylesheet) {
		this.pdfStylesheet = pdfStylesheet;
	}	 
	

    public static void main( String[] args ) throws SAXException, TransformerException, ParserConfigurationException
    {
    	TransformateurBibli transfo = new TransformateurBibli();
    	Scanner myObj = new Scanner(System.in);
	    System.out.println("Entrez le format de sortie souhaité.");	
	    String format = myObj.nextLine();
	    System.out.println("Entrez le nom du fichier de sortie souhaité.");	
	    String output = myObj.nextLine();
	    try {
	    	transfo.convertXML("bibliotheque.xml", output, format);
	    }
	    catch ( IOException | BadlyFormedXMLException | FormatException e) {
	    	System.err.println(e.getMessage());
	    }
	    System.out.println("Conversion bien effectuée.");	
        myObj.close();
    }

}

