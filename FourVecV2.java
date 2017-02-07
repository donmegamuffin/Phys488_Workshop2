/*Author: Zachary Humphreys; ID: 200951438
 *Date: 07/02/17
 */
import java.io.*;
import java.util.Arrays;

class FourVecV2
{
    static BufferedReader keyboard = new
    BufferedReader (new InputStreamReader(System.in)) ;
    static PrintWriter screen = new PrintWriter( System.out, true);
    
    // ----------- METHODS ----------------------------------------------------------------------------------------
    // Method 1: Compiles inputs from user into an array
    private static double[][] ArrayCompiler() throws IOException
    {
        //Asks user to input four vector into the form: 123 456 789 012
        //Pulls values for energy and momentum vectors out of the string and compiles them in an array
        double[][] arrayOut = {{0,0,0,0},{0,0,0,0}};
        for(int i=0;i<2;i++)
        {
            screen.println("FOR FOUR VECTOR: "+(i+1));
            screen.println("Please input the four vector where each four vector has 3 digits and each is separated by a space:\t");
            String userInput = new String(keyboard.readLine());
            for(int n=1; n <= 4; n++)
            {
                int m = (n*4 - 3);
                arrayOut[i][n-1] = new Double(userInput.substring(m-1,m+2)).doubleValue();
            }
        }
        return arrayOut;
    }
    
    //Method 2: Takes an inputted Array and calculates the Rest Mass and Invariant Momentum
    private static double[][] ArrayMandPextractor(double[][] arrayIn)
    {
        double[][] arrayOut = {{0,0},{0,0}};
        for(int i=0;i<2;i++)
        {
            double AbsMomentum = Math.sqrt(Math.pow(arrayIn[i][1],2) + Math.pow(arrayIn[i][2],2) + Math.pow(arrayIn[i][3],2));
            double InvariantMass = Math.sqrt(Math.pow(arrayIn[i][0],2)- Math.pow(AbsMomentum,2));
            arrayOut[i][0] = InvariantMass;
            arrayOut[i][1] = AbsMomentum;
        }
        return arrayOut;
    }
    
    //Method 3: Takes a 2D array and adds  the two dimensions
    private static double[][] ArrayAdder(double arrayIn[][])
    {
        double[][] arrayOut = {{0,0,0,0},{0,0,0,0}};
        for(int n = 0; n<4; n++)
        {
            arrayOut[0][n] = (arrayIn[0][n] + arrayIn[1][n]);
        }
        return arrayOut;
    }
    
    
    // --------- MAIN ----------------------------------------------------------------------------------------------
    public static void main(String[] args) throws IOException
    {
        //Calculates necessary Variables using methods
        double[][] fourVector_Array = ArrayCompiler();
        double[][] MassMomentum_Array = ArrayMandPextractor(fourVector_Array);
        double[][] fourVectorAdded_Array = ArrayAdder(fourVector_Array);
        double[][] MassMomentumAdded_Array = ArrayMandPextractor(fourVectorAdded_Array);
        //Crude output variables for debug purposes
        screen.println(Arrays.deepToString(fourVector_Array));
        screen.println(Arrays.deepToString(MassMomentum_Array));
        screen.println(Arrays.deepToString(fourVectorAdded_Array));
        screen.println(Arrays.deepToString(MassMomentumAdded_Array));        
    }
}