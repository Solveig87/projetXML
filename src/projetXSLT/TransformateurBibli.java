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
	
	/**
	  * Constructeur de la classe TransfoBibli
	  * feuilles de style définies par défaut, modifiables avec setHtmlStylesheet() et setPdfStylesheet()
	  */
	public TransformateurBibli() {
		this.setHtmlStylesheet(ProjetConstantes.FEUILLEHTML);
		this.setPdfStylesheet(ProjetConstantes.FEUILLEPDF);
	}

	/**
	 * Méthode permettant de convertir un fichier XML vers HTML ou PDF via une transformation XSLT
	 * @param input - string - chemin du fichier source au format XML
	 * @param format - string - format du fichier de sortie : pdf ou html
	 * * @param out - string - chemin du fichier de sortie sans son extension
	 */	
	
	public void convertXML(String input, String format, String out) throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException, BadlyFormedXSLException  {
		String output = out + "." + format;
		if (format.equals("pdf")) {
        	PdfGeneration.convertToPDF(input, pdfStylesheet, output);
		}
	    else if (format.equals("html")) {
        	HtmlGeneration.convertToHtml(input, htmlStylesheet, output);
	    } 
	    else {
	    	throw new FormatException("ERREUR - Le format spécifié \"" + format + "\" n'est pas valide. Les formats valides sont \"html\" ou \"pdf\"");
	    }
	}
	/**
	 * Surcharge de la méthode convertXML()
	 * Méthode permettant de convertir un fichier XML vers HTML ou PDF via une transformation XSLT sans préciser de fichier de sortie
	 * Le fichier de sortie est automatiquement généré sur le modèle du nom du fichier d'entrée
	 * @param input - string - chemin du fichier source au format XML
	 * @param format - string - format du fichier de sortie : pdf ou html
	 */	
	public void convertXML(String input, String format) throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException, BadlyFormedXSLException  {
		String sortie = input.substring(0, input.lastIndexOf('.'));
		convertXML(input, format, sortie);
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

    public static void main( String[] args ) throws SAXException, TransformerException, ParserConfigurationException, BadlyFormedXSLException
    {
    	TransformateurBibli transfo = new TransformateurBibli();
    	String entree;
   	 	String format;
   	 	String sortie = "";
    	switch (args.length) {
    	case 0 : 
    		Scanner myObj = new Scanner(System.in);
        	System.out.println("Entrez le chemin du fichier d'entrée (avec l'extension).");	
    	    entree = myObj.nextLine();
    	    System.out.println("Entrez le format de sortie souhaité.");	
    	    format = myObj.nextLine();
    	    System.out.println("Entrez le nom du fichier de sortie souhaité (extension ajoutée automatiquement). ");
    	    System.out.println("Si laissé vide le nom par défaut de la sortie sera le même que l'entrée. ");
    	    sortie = myObj.nextLine();
    	    myObj.close();
    	    break;
    	    
    	case 2 : 
    		entree = args[0];
    		format = args[1];
    		break;
    		
    	case 3 :
    		entree = args[0];
    		format = args[1];
    		sortie = args[2];
    		break;
    		
    	default :
    		System.err.println("ERREUR - La bonne utilisation est :");
    		System.err.println("java TransformateurBibli         (les arguments sont demandés à l'utilisateur pendant l'exécution)\n"
    				+ "ou\n"
    				+ "java TransformateurBibli fichier_entree format_sortie fichier_sortie        (les arguments sont en ligne de commande)");
    		return;
    	}
    	
    	try {
    		if(sortie.isEmpty()) {
    			transfo.convertXML(entree, format);
    		}
    		else {
    			transfo.convertXML(entree, format, sortie);
    		}
	    	System.out.println("Conversion bien effectuée.");	
	    }
	    catch ( IOException | BadlyFormedXMLException | FormatException e) {
	    	System.err.println(e.getMessage());
	    }
	    
    }

}

