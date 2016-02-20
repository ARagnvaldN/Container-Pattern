
public class AndGate extends Gate{

	@Override
	public boolean getOutput() throws AllInputsNotDefinedException{
		
		if(numberOfInputs() < 2)
			throw new AllInputsNotDefinedException();
		
		//Get the last gate in the series of multiple gates
		Gate lastGate = getLastGate(new AndStrategy());
		
		return (lastGate.getInput(0).getOutput() 
				&& lastGate.getInput(1).getOutput());
	}
	
}
