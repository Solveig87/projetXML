package projetXSLT;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;

public class Transformation {

    public static void main( String[] args )
    {
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File("bibliotheque.xml"));
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Entrez le format de fichier de sortie souhaité : xml ou pdf.");	
	    String format = myObj.nextLine();
	    
	    if (format.equals("pdf")) {
        
        try {
        	PdfGeneration.convertToPDF(xmlSource);
        } catch (FOPException | IOException | TransformerException e) {
            e.printStackTrace();
        }
	    }
	    
	    else if (format.equals("xml")) {
	    	System.out.println("Code de Camille en attente");
	    }
	    
	    else {
			System.out.println("Le format demandé n'est pas pris en charge.");
		}
    }
	    

}
