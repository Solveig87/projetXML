package projetXSLT;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
 
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * Classe permettant de tester les méthodes de la classe PdfGeneration
 * @see PdfGeneration
 */
public class PdfGenerationTest {

	@Rule
	public TemporaryFolder repertoireTest = new TemporaryFolder();
	
	@Test
	public void convertToHtml_succes() throws TransformerConfigurationException, TransformerException, SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException  {
		//GIVEN
		String source = "bibliotheque.xml";
		String feuille = ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";
				
		//WHEN
		PdfGeneration.convertToPDF(source, feuille, sortie);	
		
		//THEN
		assertTrue( VerificationFichiers.fichierExiste(sortie) );
		PdfReader reader = new PdfReader(sortie);
		//vérification Contenu : la première page contient bien le titre
	    assertTrue(PdfTextExtractor.getTextFromPage(reader, 1).contains("Projet JAVA"));
		//vérification Contenu : vérifier que la deuxième page contient bien "Harry Potter"
	    assertTrue(PdfTextExtractor.getTextFromPage(reader, 2).contains("Harry Potter"));
	}
	
	@Test 
	public void convertToHtml_xmlInexistant() {
		//GIVEN
		String source = "fichierNonExistant.xml";
		String feuille =ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		Throwable exception = null;	
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(FileNotFoundException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xslInexistant() {
		//GIVEN
		String source = "bibliotheque.xml";
		String feuille = "feuilleNonExistante.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		Throwable exception = null;
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(FileNotFoundException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xmlMalForme() throws IOException{
		
		//GIVEN
		File xmlMalForme= repertoireTest.newFile("xmlMalforme.xml");
		FileUtils.writeStringToFile(xmlMalForme, "<?xml version=\"1.0\"?><bibli></bib>");
		String source = repertoireTest.getRoot()+"/xmlMalForme.xml";
		String feuille = ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		Throwable exception = null;		
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(BadlyFormedXMLException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xslMalForme() throws IOException{
		
		//GIVEN
		File xslMalForme= repertoireTest.newFile("xslMalforme.xsl");
		FileUtils.writeStringToFile(xslMalForme, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet>");
		String source = "bibliotheque.xml";
		String feuille = repertoireTest.getRoot()+"/xslMalForme.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		Throwable exception = null;		
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(BadlyFormedXMLException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xslNonFonctionnelle() throws IOException{
		
		//GIVEN
		File xslNonFonc= repertoireTest.newFile("xslNonFonc.xsl");
		FileUtils.writeStringToFile(xslNonFonc, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:style xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" exclude-result-prefixes=\"xs\" version=\"2.0\"></xsl:style>\n");
		String source = "bibliotheque.xml";
		String feuille = repertoireTest.getRoot()+"/xslNonFonc.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		Throwable exception = null;		
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(BadlyFormedXSLException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}

}

