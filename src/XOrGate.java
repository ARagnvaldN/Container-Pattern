

public class XOrGate extends Gate {
	
	@Override
	public boolean getOutput() 
			throws AllInputsNotDefinedException {

		if(numberOfInputs() < 2)
			throw new AllInputsNotDefinedException();
		
		//Get the last gate in the series of multiple gates
		Gate lastGate = getLastGate(new XOrStrategy());
		
		return lastGate.getOutput();
	}

	

}
