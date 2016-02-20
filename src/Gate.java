import java.util.ArrayList;


public abstract class Gate implements OutputCalculator {
	
	private ArrayList<OutputCalculator> inputs = new ArrayList<OutputCalculator>();
	
	public void addInput(OutputCalculator o) 
			throws AllInputsNotDefinedException {
		
		inputs.add(o);
	}

	public void connectTo(Gate g) {
		
		try {
			g.addInput(this);
		} catch (AllInputsNotDefinedException e) {
			e.printStackTrace();
		}
	}

	public OutputCalculator getInput(int i) {
		
			return inputs.get(i);
	}

	public int numberOfInputs() {
		
		return inputs.size();
	}
	
	public void printTruthTable() throws AllInputsNotDefinedException {
				
		//Create this type of gate
		Gate g = null;
		try {
			g = this.getClass().newInstance();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
		//Create an arraylist of as many generators as inputs and 
		//add the generators as inputs to this gate
		ArrayList<Generator> truthInputs = new ArrayList<Generator>();
		
		for(int i=0;i<numberOfInputs();i++){
			Generator gen = new Generator(false);
			truthInputs.add(gen);
			g.addInput(gen);
		}
		
		//Print the table
		System.out.print("Truthtable, "+getClass().getName()+"\n");
		
		//For every combination
		for(int i = 0; i < (int)Math.pow(2, numberOfInputs());i++){
			
			//Print 1 for true and 0 for false
			for(int j = 0; j < truthInputs.size();j++){
				if(truthInputs.get(j).getOutput())			
					System.out.print("1\t");
				else
					System.out.print("0\t");
			}
			
			//Get output from the gate
			if(g.getOutput())
				System.out.print("|\t1\n");
			else
				System.out.print("|\t0\n");
			
			//Count up binary
			truthInputs = upTruthLine(truthInputs, truthInputs.size()-1);
	
		}
			
	}

	public void removeInput(int i) {
		inputs.remove(i);
		
	}

	protected ArrayList<Generator> upTruthLine(ArrayList<Generator> gens, int index) throws AllInputsNotDefinedException{
		
		//Counts upward in binary
		if(gens.get(index).getOutput() == true){
			gens.get(index).setOutput(false);
			if(index > 0)upTruthLine(gens, --index);
			else return gens;
		} else {
			gens.get(index).setOutput(true);
			return gens;
		}
		
		return gens;
	}
	
	protected Gate getLastGate(GateStrategy gs) 
			throws AllInputsNotDefinedException{
		
		Gate returnGate = getLastGate(getInput(0), 1, gs);
		
		return returnGate;
	}
	
	protected Gate getLastGate(OutputCalculator A, int index, GateStrategy gs) 
			throws AllInputsNotDefinedException{
		
			OutputCalculator B = getInput(index);
			
		//Create the gate and connect inputs	
			Gate lastGate;
			lastGate = gs.createGate(A, B);
			
		if(index+1 < numberOfInputs())
			lastGate = getLastGate(lastGate, ++index, gs);
			
		return lastGate;
	}
	
}
