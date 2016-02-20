
public class XOrStrategy implements GateStrategy{

	@Override
	public Gate createGate(OutputCalculator A, OutputCalculator B)
			throws AllInputsNotDefinedException {
		
		//Create an XOR gate out of 2 notgates, 2 andgates and 1 orgate
		NotGate invertA = new NotGate();
		NotGate invertB = new NotGate();
		
		AndGate andA = new AndGate();
		AndGate andB = new AndGate();
		
		OrGate or = new OrGate();
		
		invertA.addInput(A);
		andA.addInput(invertA);
		andA.addInput(B);
		
		invertB.addInput(B);
		andB.addInput(A);
		andB.addInput(invertB);
		
		or.addInput(andA);
		or.addInput(andB);
		
		return or;
	}

}
