package projetXSLT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

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

    public static void convertToPDF(String input, String xslt, String output) throws IOException, FOPException, TransformerException {
        File xsltFile = new File(xslt);
        
        File fichierXML = new File(input);
        if(!fichierXML.exists() || fichierXML.isDirectory()) { 
            throw new FileNotFoundException("Fichier non trouvé : " + input);
        }
        
		try {
			// Create a new factory to create parsers
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			// Use the factory to create a parser (builder) and use it to parse the document.
			DocumentBuilder builder = dBF.newDocumentBuilder();

			InputSource is = new InputSource(input);
			Document doc = builder.parse(is);
			}
			catch (Exception e) {
			System.out.println(input + " est mal formé.");
			System.exit(1);
			}
        
        StreamSource xmlSource = new StreamSource(fichierXML);
        
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out;
        out = new java.io.FileOutputStream(output);

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}