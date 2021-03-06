package projetXSLT;

/**
 * FormatException indique une erreur de non respect d'un format imposé.
 */

public class FormatException extends Exception {

	public FormatException() {
	}
	
	public FormatException(String message){  
        super(message);  
    }  
	
	public FormatException(Throwable cause) {
		super(cause);
	}

	public FormatException(String message, Throwable cause) {
		super(message, cause);
	}

}