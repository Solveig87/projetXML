package projetXSLT;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.xml.sax.SAXException;

public class TransformateurBibli {
	
	private String input;
	
	public TransformateurBibli(String input) {
		this.setInput(input);
	}
	
	public void convertXML(String format) {
	    if (format.equals("pdf")) {
	        try {
	        	PdfGeneration.convertToPDF(this.input, "xmltopdf.xsl", "bibliotheque.pdf");
	        } catch (FOPException | IOException | TransformerException e) {
	            e.printStackTrace();
	        }
		}
		    
	    else if (format.equals("html")) {
	        try {
	        	HtmlGeneration.convertToHtml(this.input, "to_html.xsl", "test.html");
	        } catch ( IOException | TransformerException | SAXException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    else {
			System.out.println("Le format demandé n'est pas pris en charge.");
		}
	}
	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}


    public static void main( String[] args )
    {
        TransformateurBibli transfo = new TransformateurBibli("bibliotheque.xml");
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Entrez le format de fichier de sortie souhaité : pdf ou html.");	
	    String format = myObj.nextLine();
	    transfo.convertXML(format);
    }	    

}
