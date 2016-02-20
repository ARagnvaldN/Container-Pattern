
public class AndStrategy implements GateStrategy {
	
	@Override
	public Gate createGate(OutputCalculator A, OutputCalculator B) 
			throws AllInputsNotDefinedException{
		
		Gate g = new AndGate();
		g.addInput(A);
		g.addInput(B);
		
		return g;
		
	}

}
