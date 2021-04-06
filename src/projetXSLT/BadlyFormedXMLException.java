package projetXSLT;

/**
 * BadlyFormedXMLException indique une erreur au niveau d'un document XML mal formé.
 * Cela peut concerner des chevauchements, des balises non fermées...
 */

public class BadlyFormedXMLException extends Exception {
	
	public BadlyFormedXMLException() {
	}
	
	public BadlyFormedXMLException(String message){  
        super(message);  
    }  
	
	public BadlyFormedXMLException(Throwable cause) {
		super(cause);
	}

	public BadlyFormedXMLException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
