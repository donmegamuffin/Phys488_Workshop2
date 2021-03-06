import java.io.*;
import java.util.Random;

class GenerateHistogram
{
    static BufferedReader keyboard = new
    BufferedReader (new InputStreamReader(System.in)) ;
    static PrintWriter screen = new PrintWriter( System.out, true);
    
    private static void writeToDisk(int [] hist, double low, double dx, long trials, long under, long over , String filename) throws IOException
    {
        FileWriter file = new FileWriter(filename);     // this creates the file with the given name
        PrintWriter outputFile = new PrintWriter(file); // this sends the output to file1

        // Write the file as a comma seperated file (.csv) so it can be read it into EXCEL
        // first some general information about the histogram
        outputFile.println("Binlow , " + low);     // note the comma in the text here
        outputFile.println("Binint , " + dx);
        outputFile.println("nbins  , " + hist.length);
        //<<<TASK3.2 : Prints the amount of trials, underflows, and overflows at the top of the .csv>>>
        outputFile.println("ntrials  , " + trials); 
        outputFile.println("#Underflowed  , " + under); 
        outputFile.println("#Overflowed  , " + over); 
        // now make a loop to write the contents of each bin to disk, one number at a time
        // together with the x-coordinate of the centre of each bin.
        for (int n = 0; n < hist.length; n++) 
        {
            // calculate the x coordinate of the centre of each bin
            double binCentre = low + dx/2 + n*dx;
            // comma separated values
            outputFile.println(n + "," + binCentre + "," + hist[n] +"," + Math.sqrt(hist[n]));
        }
        outputFile.close(); // close the output file
        screen.println("Data written to disk in file " + filename);
        return;
    }

    
    public static void main (String [] args ) throws IOException
    {
    final int SIZE = 20; // note : the array elements are numbered 0, 1 , 2 ,3 ,.... SIZE-1 
    final double binlow  = 0.4; // this is the low edge of the first bin , hist1[0].       <<<TASK3.1>>>
    final double binhigh = 0.9; // this is the upper edge of the last bin, hist1[SIZE-1].  <<<TASK3.1>>>
    long numberUnderflows=0; // this is the number of random values that fall below binlow. <<TASK3.1>>>
    long numberOverflows=0; // this is the number of random values that fall above binhigh. <<TASK3.1>>>
    
    int [] hist = new int[SIZE]; //  The array is filled with zeros, see page 162 Hubbard
    double [] histError = new double[SIZE]; // Empty array is filled zeroes                 <<TASK3.1>>>
    Random value = new Random(); // instantiate a variable of the class Random
    
    final double binsize = (binhigh - binlow)/( (double)SIZE);  // note the cast (double)
    screen.println("The width of each bin = " + binsize);
    screen.println("Input the number of random numbers to generate");
    int trials = new Integer(keyboard.readLine()).intValue();

    // print output about 20 times
    int interval = trials / 20;
    if (interval == 0) {
        interval = 1;
    }
    for (int i = 0; i < trials; i++) {                    
        double randNumber = value.nextDouble(); 
        // print some output on screen - not essential, but nice to monitor progress
        if (i % interval == 0) { // "%" gived the remainder of the integer division
        screen.println(" The " + i + "th number is =  " + randNumber);
        }

        // calculate which bin of the array should be increased by 1
        if(randNumber <=binlow || randNumber >=binhigh) // <<TASK 3.2>>> Under/Over
        {
            //Checks for outliers and tallies values
            if(randNumber <=binlow)
            {
                numberUnderflows++;
            }
            if(randNumber >=binhigh)
            {
                numberOverflows++;
            }
        }
        else
        {
            int bin = (int) ( (randNumber - binlow)/binsize ); 
            hist[bin]++; // add 1 to the location in the array hist1
        }
    }
    // Histogram has been filled. Show the contents on the screen.
    // Also, add up the contents of the bins to see if the sum equals trials
    long sum = 0;
    for (int bin = 0; bin < SIZE; bin++) {
        histError[bin] = Math.sqrt(hist[bin]);
        screen.printf("Bin number " + bin + " contents =  " +  hist[bin]+ ";error = " + "%.3f", histError[bin]); //<<<TASK3.1>>>
        screen.println(" ("+ (hist[bin]/histError[bin]) +"%)"); //<<TASK3.1>>
        sum = sum + hist[bin];
    }
    screen.println("Number of trials = " + trials + " , the sum of the contents = " + sum );
    screen.println("The number of random numbers overflowed: " +numberOverflows); //<<<TASK3.1>>>
    screen.println("The number of random numbers underflowed: " +numberUnderflows); //<<<TASK3.1>>
    screen.println("Writing to disk, please wait....");
    writeToDisk(hist, binlow, binsize, trials, numberUnderflows, numberOverflows, "test.csv");  //<<TASK3.2>>
    }
}
