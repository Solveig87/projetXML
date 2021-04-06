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
	public void testConvertToPdf_succes() throws TransformerConfigurationException, TransformerException, SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException  {
		//GIVEN
		String source = ProjetConstantes.XMLTEST;
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
	public void testConvertToPdf_xmlInexistant1() {
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		String source = "fichierNonExistant.xml";
		String feuille =ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testConvertToPdf_xmlInexistant2() throws IOException, BadlyFormedXMLException, ParserConfigurationException, TransformerException, SAXException, BadlyFormedXSLException {
		/* Vérifie que l'Exception FileNotFoundException est bien levée */
		
		//GIVEN
		String source = "fichierNonExistant.xml";
		String feuille =ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		PdfGeneration.convertToPDF(source, feuille, sortie);
		
	}
	
	@Test 
	public void testConvertToPdf_xslInexistant1() {
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		String source = ProjetConstantes.XMLTEST;
		String feuille = "feuilleNonExistante.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testConvertToPdf_xslInexistant2() throws IOException, BadlyFormedXMLException, ParserConfigurationException, TransformerException, SAXException, BadlyFormedXSLException {
		/* Vérifie que l'Exception FileNotFoundException est bien levée */
		
		//GIVEN
		String source = ProjetConstantes.XMLTEST;
		String feuille = "feuilleNonExistante.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		PdfGeneration.convertToPDF(source, feuille, sortie);
		
	}
	
	@Test 
	public void testConvertToPdf_xmlMalForme1() throws IOException{
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		File xmlMalForme= repertoireTest.newFile("xmlMalforme.xml");
		FileUtils.writeStringToFile(xmlMalForme,  ProjetConstantes.XMLBADFORM);
		String source = repertoireTest.getRoot()+"/xmlMalForme.xml";
		String feuille = ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = BadlyFormedXMLException.class)
	public void testConvertToPdf_xmlMalForme2() throws IOException, BadlyFormedXMLException, ParserConfigurationException, TransformerException, SAXException, BadlyFormedXSLException{
		/* Vérifie que l'Exception BadlyFormedXMLException est bien levée */
		
		//GIVEN
		File xmlMalForme= repertoireTest.newFile("xmlMalforme.xml");
		FileUtils.writeStringToFile(xmlMalForme,  ProjetConstantes.XMLBADFORM);
		String source = repertoireTest.getRoot()+"/xmlMalForme.xml";
		String feuille = ProjetConstantes.FEUILLEPDF;
		String sortie = repertoireTest.getRoot()+"/test.pdf";		
		
		//WHEN
		PdfGeneration.convertToPDF(source, feuille, sortie);
		
	}
	
	@Test 
	public void testConvertToPdf_xslMalForme1() throws IOException{
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		File xslMalForme= repertoireTest.newFile("xslMalforme.xsl");
		FileUtils.writeStringToFile(xslMalForme, ProjetConstantes.XSLBADFORM);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslMalForme.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = BadlyFormedXMLException.class)
	public void testConvertToPdf_xslMalForme2() throws IOException, BadlyFormedXMLException, ParserConfigurationException, TransformerException, SAXException, BadlyFormedXSLException{
		/* Vérifie que l'Exception BadlyFormedXMLException est bien levée */
		
		//GIVEN
		File xslMalForme= repertoireTest.newFile("xslMalforme.xsl");
		FileUtils.writeStringToFile(xslMalForme, ProjetConstantes.XSLBADFORM);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslMalForme.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		PdfGeneration.convertToPDF(source, feuille, sortie);
		
	}
	
	@Test
	public void testConvertToPdf_xslNonFonctionnelle1() throws IOException{
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		File xslNonFonc= repertoireTest.newFile("xslNonFonc.xsl");
		FileUtils.writeStringToFile(xslNonFonc, ProjetConstantes.XSLNONFONC);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslNonFonc.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";	
		
		//WHEN
		try {
			PdfGeneration.convertToPDF(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = BadlyFormedXSLException.class) 
	public void testConvertToPdf_xslNonFonctionnelle2() throws IOException, BadlyFormedXMLException, ParserConfigurationException, TransformerException, SAXException, BadlyFormedXSLException{
		/* Vérifie que l'Exception BadlyFormedXSLException est bien levée */
		
		//GIVEN
		File xslNonFonc= repertoireTest.newFile("xslNonFonc.xsl");
		FileUtils.writeStringToFile(xslNonFonc, ProjetConstantes.XSLNONFONC);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslNonFonc.xsl";
		String sortie = repertoireTest.getRoot()+"/test.pdf";
		
		//WHEN
		PdfGeneration.convertToPDF(source, feuille, sortie);
		
	}

}

