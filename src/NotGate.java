
public class NotGate extends Gate {


	@Override
	public void addInput(OutputCalculator o) throws AllInputsNotDefinedException {
		if(numberOfInputs() != 0)
			throw new AllInputsNotDefinedException();
			
			super.addInput(o);
	}


	@Override
	public boolean getOutput() throws AllInputsNotDefinedException {
		
		if(numberOfInputs() != 1)
			throw new AllInputsNotDefinedException();
			
		return !getInput(0).getOutput();
	}


}
