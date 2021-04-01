package projetXSLT;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.xml.sax.SAXException;

public class TransformateurBibli {
	
	private String htmlStylesheet;
	private String pdfStylesheet;
	
	public TransformateurBibli() {
		this.setHtmlStylesheet("to_html.xsl");
		this.setPdfStylesheet("xmltopdf.xsl");
	}
	
	public void convertXML(String input, String out, String format) {
		String output = out + "." + format;
	    if (format.equals("pdf")) {
	        try {
	        	PdfGeneration.convertToPDF(input, pdfStylesheet, output);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (FOPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    
	    else if (format.equals("html")) {
	        try {
	        	HtmlGeneration.convertToHtml(input, htmlStylesheet, output);
	        } catch ( IOException | TransformerException | SAXException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    else {
			System.out.println("ERREUR - Le format demandé n'est pas pris en charge.");
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
	    System.out.println("Entrez le format de sortie souhait�.");	
	    String format = myObj.nextLine();
        transfo.convertXML("bibliotheque.xml", "test2", format);
    }

}

