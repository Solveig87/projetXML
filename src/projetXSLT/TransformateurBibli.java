package projetXSLT;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.xml.sax.SAXException;

/**
 * Classe permettant d'opérer des transformations XML-> PDF ou HTML via XSLT
 */

public class TransformateurBibli {
	
	private String htmlStylesheet;
	private String pdfStylesheet;
	
	public TransformateurBibli() {
		this.setHtmlStylesheet("to_html.xsl");
		this.setPdfStylesheet("xmltopdf.xsl");
	}

	/**
	 * Méthode permettant de convertir un fichier XML vers HTML ou PDF via une transformation XSLT
	 * @param input - string - chemin du fichier source au format XML
	 * @param out - string - chemin du fichier de sortie sans son extension
	 * @param format - string - format du fichier de sortie : pdf ou html
	 */	
	
	public void convertXML(String input, String out, String format) {
		String output = out + "." + format;
	    if (format.equals("pdf")) {
	        try {
	        	PdfGeneration.convertToPDF(input, pdfStylesheet, output);
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } catch (FOPException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		    
	    else if (format.equals("html")) {
	        try {
	        	HtmlGeneration.convertToHtml(input, htmlStylesheet, output);
	        } catch (IOException e) {
	            System.out.println("Erreur : le fichier n'a pas été trouvé.");
	        } catch (FOPException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
	    }
	    
	    else {
			System.out.println("ERREUR - Le format demandÃ© n'est pas pris en charge.");
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
	

    public static void main( String[] args )
    {
    	TransformateurBibli transfo = new TransformateurBibli();
    	Scanner myObj = new Scanner(System.in);
	    System.out.println("Entrez le format de sortie souhaité.");	
	    String format = myObj.nextLine();
        transfo.convertXML("xmlmalforme.xml", "test2", format);
        myObj.close();
    }

}

