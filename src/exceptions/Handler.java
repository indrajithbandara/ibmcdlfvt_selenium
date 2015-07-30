package exceptions;

import junit.framework.AssertionFailedError;

public interface Handler {

	void handle(Exception e);
	
	void failedVerify(AssertionFailedError e);
	
}
