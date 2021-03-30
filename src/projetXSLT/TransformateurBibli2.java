package projetXSLT;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.xml.sax.SAXException;

public class TransformateurBibli2 {
	
	private String htmlStylesheet;
	private String pdfStylesheet;
	
	public TransformateurBibli2() {
		this.setHtmlStylesheet("to_html.xsl");
		this.setPdfStylesheet("xmltopdf.xsl");
	}
	
	public void convertXML(String input, String output, String format) {
	    if (format.equals("pdf")) {
	        try {
	        	PdfGeneration.convertToPDF(input, pdfStylesheet, output);
	        } catch (FOPException | IOException | TransformerException e) {
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
    	TransformateurBibli2 transfo = new TransformateurBibli2();
        transfo.convertXML("bibliotheque.xml", "test2.html", "html");
    }

}

