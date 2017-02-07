import java.io.*;
class FourVecV2
{
    static BufferedReader keyboard = new
	BufferedReader (new InputStreamReader(System.in)) ;
    static PrintWriter screen = new PrintWriter( System.out, true);
    
    // ----------- METHODS ------------------------------  
    // Method 1: Compiles inputs from user into an array
    private static double[] ArrayCompiler() throws IOException
    {
        //Asks user to input four vector into the form: 123 456 789 012
        screen.println("Please input the four vector where each four vector has 3 digits and each is separated by a space:\t");
        String userInput = new String(keyboard.readLine());
        //Pulls values for energy and momentum vectors out of the string and compiles them in an array
        double[] outputArray = {0,0,0,0};
        for(int n=1; n <= 4; n++)
        {
            int m = (n*4 - 3);
            outputArray[n-1] = new Double(userInput.substring(m-1,m+2)).doubleValue();
        }
        return outputArray;
    }
    //Method 2: Takes an inputted Array and calculates the Rest Mass and Invariant Momentum
    private static double[] AbsMomentumInvMass(double[] input4Vec)
    {
        double AbsMomentum = Math.sqrt(Math.pow(input4Vec[1],2) + Math.pow(input4Vec[2],2) + Math.pow(input4Vec[3],2));
        double InvariantMass = Math.sqrt(Math.pow(input4Vec[0],2)- Math.pow(AbsMomentum,2));
        double[] absMoInvMa = {AbsMomentum,InvariantMass};
        return absMoInvMa;
    }
    //Method 3: Takes a 2D array and adds  the two dimensions //TODO 
    private static double [] ArrayAdder(double[][] twoDInput)
    {
        double[] oneDOutput = {0,0,0,0};
        for(int n=0;n<4;n++)
        {
            oneDOutput[n] = (twoDInput[n][0]) + (twoDInput[n][1]);
        }
        return oneDOutput;
    }
    // --------- MAIN -----------------------------
    public static void main(String[] args) throws IOException
    {
        screen.println("FOR FOUR-VECTOR 1");
        double[] fourVec1 = ArrayCompiler();
        double[] fourVec2 = {5,-4,0,0};
        //Takes input array and outputs M and absP
        double[] fourVecMandP1 = AbsMomentumInvMass(fourVec1);
        double[][] fourVec2D = {fourVec1,fourVec2};
        double[] vecAdded = ArrayAdder(fourVec2D);
        screen.println("P is " +fourVecMandP1[0]+ " GeV and Momentum is " +fourVecMandP1[1] + " GeV.");
        screen.println(vecAdded[0]+" "+ vecAdded[1]+" "+ vecAdded[2]+" "+ vecAdded[3]);
    }
}