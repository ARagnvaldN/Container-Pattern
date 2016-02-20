
public class Test {
	
	public static void main(String[] args) throws AllInputsNotDefinedException{
			
		// The gate is connected to one false and one true generator
		System.out.print("Test, expected result: false");
		
			AndGate andgate = new AndGate();
			Generator gen1 = new Generator(true);
			Generator gen2 = new Generator(false);
			Generator gen3 = new Generator(false);
			andgate.addInput(gen1);
			andgate.addInput(gen2);
			
		System.out.print("\tTest, actual result: "+andgate.getOutput()+"\n");
		
		if(andgate.getOutput()){
			System.out.println("Test failed");
			return;
		}
			
		
		// The gate is connected to two true generators
		System.out.print("Test, expected result: true");
		
			gen2.setOutput(true);
			
		System.out.print("\tTest, actual result: "+andgate.getOutput()+"\n");
		
		if(!andgate.getOutput()){
			System.out.println("Test failed");
			return;
		}	
			
		// The gate is connected to one false and two true generator
		System.out.print("Test, expected result: false");
		
			andgate.addInput(gen3);
			
		System.out.print("\tTest, actual result: "+andgate.getOutput()+"\n");
		
		if(andgate.getOutput()){
			System.out.println("Test failed");
			return;
		}
			
			
		//Connect two generator, one false one true and print truthtable
		System.out.print("Test, expected result: true");
		
			OrGate orgate = new OrGate();
			orgate.addInput(gen1);
			orgate.addInput(gen3);
			

		System.out.print("\tTest, actual result: "+orgate.getOutput()+"\n");
		
		if(!orgate.getOutput()){
			System.out.println("Test failed");
			return;
		}

			
		//Not gate
		System.out.print("Test, expected result: false");
		
			NotGate notgate = new NotGate();
			notgate.addInput(gen1);			//true
			
		System.out.print("\tTest, actual result: "+notgate.getOutput()+"\n");
		
		if(notgate.getOutput()){
			System.out.println("Test failed");
			return;
		}
		
		
		Generator A = new Generator(true);
		Generator B = new Generator(true);
		
			A.setOutput(true);
			B.setOutput(true);

			
			XOrGate xor = new XOrGate();
			xor.addInput(A);
			xor.addInput(B);
	
		
			System.out.print("Test, expected result: false");
			// A 1 B 1
			System.out.print("\tTest, actual result: "+xor.getOutput()+"\n");
			
			if(xor.getOutput()){
				System.out.println("Test failed");
				return;
			}
			
			System.out.print("Test, expected result: true");
			// A 1 B 0
			B.setOutput(false);
			System.out.print("\tTest, actual result: "+xor.getOutput()+"\n");	
				
			if(!xor.getOutput()){
				System.out.println("Test failed");
				return;
			}
			
			System.out.print("Test, expected result: false");
			// A 0 B 0
				A.setOutput(false);
			System.out.print("\tTest, actual result: "+xor.getOutput()+"\n");
			
			if(xor.getOutput()){
				System.out.println("Test failed");
				return;
			}
			
			System.out.print("Test, expected result: true");
			// A 0 B 1
				B.setOutput(true);
			System.out.print("\tTest, actual result: "+xor.getOutput()+"\n");

			if(!xor.getOutput()){
				System.out.println("Test failed");
				return;
			}
			
			
			//Build a simple 4-bit adder
			
			//Create A and B
			Generator[] AN = new Generator[4];
			Generator[] BN = new Generator[4];
			boolean[] CN = new boolean[4];
			
			//5 AND gates
			AndGate and1 = new AndGate();
			AndGate and2 = new AndGate();
			AndGate and3 = new AndGate();
			AndGate and4 = new AndGate();
			AndGate and5 = new AndGate();
			
			//7 XOR gates
			XOrGate xor1 = new XOrGate();
			XOrGate xor2 = new XOrGate();
			XOrGate xor3 = new XOrGate();
			XOrGate xor4 = new XOrGate();
			XOrGate xor5 = new XOrGate();
			XOrGate xor6 = new XOrGate();
			XOrGate xor7 = new XOrGate();
			
			//2 OR gates
			OrGate or1 = new OrGate();
			OrGate or2 = new OrGate();
			
			//Set generators
			//A = 5, 0101
			AN[0] = new Generator(true);
			AN[1] = new Generator(false);
			AN[2] = new Generator(true);
			AN[3] = new Generator(false);

			//B = 3, 0011
			BN[0] = new Generator(true);
			BN[1] = new Generator(true);
			BN[2] = new Generator(false);
			BN[3] = new Generator(false);

			System.out.print("Test, expected result: 8.0");
			
			//CONNECT!
			xor1.addInput(AN[0]);
			xor1.addInput(BN[0]);
			and1.addInput(AN[0]);
			and1.addInput(BN[0]);
			
			xor2.addInput(AN[1]);
			xor2.addInput(BN[1]);
			and2.addInput(AN[1]);
			and2.addInput(BN[1]);
			
			xor3.addInput(AN[2]);
			xor3.addInput(BN[2]);
			and3.addInput(AN[2]);
			and3.addInput(BN[2]);
			
			xor4.addInput(AN[3]);
			xor4.addInput(BN[3]);
			
			////
			
			xor5.addInput(and1);
			xor5.addInput(xor2);
			and4.addInput(and1);
			and4.addInput(xor2);
			
			or1.addInput(and4);
			or1.addInput(and2);
			
			xor6.addInput(or1);
			xor6.addInput(xor3);
			and5.addInput(or1);
			and5.addInput(xor3);
			
			or2.addInput(and5);
			or2.addInput(and3);
			
			xor7.addInput(xor4);
			xor7.addInput(or2);
			
			////
			
			CN[3] = xor7.getOutput();
			CN[2] = xor6.getOutput();
			CN[1] = xor5.getOutput();
			CN[0] = xor1.getOutput();
			
			System.out.print("\tTest, actual result: "+getSum(CN)+"\n");
			
			if(getSum(CN) != 8){
				System.out.println("Test failed");
				return;
			}
			
			//Reset generators
			//A = 7, 0111
			AN[0].setOutput(true);
			AN[1].setOutput(true);
			AN[2].setOutput(true);
			AN[3].setOutput(false);

			//B = 7, 0111
			BN[0].setOutput(true);
			BN[1].setOutput(true);
			BN[2].setOutput(true);
			BN[3].setOutput(false);

			System.out.print("Test, expected result: 14.0");
			CN[3] = xor7.getOutput();
			CN[2] = xor6.getOutput();
			CN[1] = xor5.getOutput();
			CN[0] = xor1.getOutput();
			
			System.out.print("\tTest, actual result: "+getSum(CN)+"\n");
			
			if(getSum(CN) != 14){
				System.out.println("Test failed");
				return;
			}
			
			//Reset generators
			//A = 9, 1001
			AN[0].setOutput(true);
			AN[1].setOutput(false);
			AN[2].setOutput(false);
			AN[3].setOutput(true);

			//B = 2, 0010
			BN[0].setOutput(false);
			BN[1].setOutput(true);
			BN[2].setOutput(false);
			BN[3].setOutput(false);

			System.out.print("Test, expected result: 11.0");
			CN[3] = xor7.getOutput();
			CN[2] = xor6.getOutput();
			CN[1] = xor5.getOutput();
			CN[0] = xor1.getOutput();
			
			System.out.print("\tTest, actual result: "+getSum(CN)+"\n");
			
			if(getSum(CN) != 11){
				System.out.println("Test failed");
				return;
			}
			
			//Test XORgate
			XOrGate xorg = new XOrGate();
			xorg.addInput(gen1);
			xorg.addInput(gen3);
			xorg.printTruthTable();
			
			//Test ANDgate
			AndGate and = new AndGate();
			and.addInput(gen1);
			and.addInput(gen3);
			and.printTruthTable();
			
			//Test ORgate
			OrGate or = new OrGate();
			or.addInput(gen1);
			or.addInput(gen3);
			or.printTruthTable();
			
			//Test NOTgate
			NotGate not = new NotGate();
			not.addInput(gen1);
			not.printTruthTable();
			
			System.out.print("Test completed succesfully.");
			
	}
	
	public static double getSum(boolean[] b){
		double d = 0;
		
		for(int i=0;i<4;i++){
			if(b[i])
				d += Math.pow(2, i);
		}
		return d;
	}


}
