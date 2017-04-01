package Testing;

/**
 * All test package classes should extend this class to implement
 * the r=functionality for their run.
 * 
 * @author Greg
 */

abstract class Test_Abstract {
	private boolean testSuccess = false;
	private boolean testFailure = false;
	
	/**
	 * All test classes for each package should implement this
	 * method to be tested.
	 */
	abstract boolean run();
}		
