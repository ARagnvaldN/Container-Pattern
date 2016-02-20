
public class OrStrategy implements GateStrategy{

	@Override
	public Gate createGate(OutputCalculator A, OutputCalculator B)
			throws AllInputsNotDefinedException {
		
		Gate g = new OrGate();
		g.addInput(A);
		g.addInput(B);
		
		return g;
	}

}
