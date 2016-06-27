import java.io.FileReader;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.io.FileWriter;
import com.opencsv.CSVWriter;
import java.util.*;
import java.math.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class CreateCsvsByKey
{
   @SuppressWarnings("resource")
   
   private static ArrayList<String[]> cMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> cMin = new ArrayList<String[]>();
   private static ArrayList<String[]> cSrpMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> cSrpMin = new ArrayList<String[]>();
   private static ArrayList<String[]> dMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> dMin = new ArrayList<String[]>();
   private static ArrayList<String[]> dSrpMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> dSrpMin = new ArrayList<String[]>();
   private static ArrayList<String[]> eMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> eMin = new ArrayList<String[]>();
   private static ArrayList<String[]> fMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> fMin = new ArrayList<String[]>();
   private static ArrayList<String[]> fSrpMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> fSrpMin = new ArrayList<String[]>();
   private static ArrayList<String[]> gMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> gMin = new ArrayList<String[]>();
   private static ArrayList<String[]> gSrpMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> gSrpMin = new ArrayList<String[]>();
   private static ArrayList<String[]> aMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> aMin = new ArrayList<String[]>();
   private static ArrayList<String[]> aSrpMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> aSrpMin = new ArrayList<String[]>();
   private static ArrayList<String[]> bMaj = new ArrayList<String[]>();
   private static ArrayList<String[]> bMin = new ArrayList<String[]>();
   
   
   public static void main(String[] args) throws Exception
   {
		String originalCSV = "MillionSongData.csv";
		String combinedCSV = "CleanCombinedKeys.csv";
		
		//write output to a file
		File file = new File("out.txt"); 
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		int hotnessColumn = 3;
		
		makeKeyCsvs(originalCSV);
		makeOneCSV(combinedCSV);
		fillArrayLists();
		makeOneCSV(combinedCSV);
		predict(dSrpMin);
   }
   
   //Methods   
   //----------------------------------------------------------
   //Creates csv's of each song key
   //cleans the hotness values
   //removes any row with bpm at 0
   //removes any duplicate records(note there were sometime 4x + of same record)
   //removes rows with "nan" in familiarity column
   static void makeKeyCsvs(String originalCSV) throws java.io.IOException
   {
	    CSVReader reader = new CSVReader(new FileReader(originalCSV), ',' , '"' , 0);
		
		CSVWriter C = new CSVWriter(new FileWriter("key_C.csv"));
		CSVWriter C_Sharp = new CSVWriter(new FileWriter("key_C_Sharp.csv"));
		CSVWriter D = new CSVWriter(new FileWriter("key_D.csv"));
		CSVWriter D_Sharp = new CSVWriter(new FileWriter("key_D_Sharp.csv"));
		CSVWriter E = new CSVWriter(new FileWriter("key_E.csv"));
		CSVWriter F = new CSVWriter(new FileWriter("key_F.csv"));
		CSVWriter F_Sharp = new CSVWriter(new FileWriter("key_F_Sharp.csv"));
		CSVWriter G = new CSVWriter(new FileWriter("key_G.csv"));
		CSVWriter G_Sharp = new CSVWriter(new FileWriter("key_G_Sharp.csv"));
		CSVWriter A = new CSVWriter(new FileWriter("key_A.csv"));
		CSVWriter A_Sharp = new CSVWriter(new FileWriter("key_A_Sharp.csv"));
		CSVWriter B = new CSVWriter(new FileWriter("key_B.csv"));
		
		HashSet<String> c_used = new HashSet<String>();
		HashSet<String> c_sharp_used = new HashSet<String>();
		HashSet<String> d_used = new HashSet<String>();
		HashSet<String> d_sharp_used = new HashSet<String>();
		HashSet<String> e_used = new HashSet<String>();
		HashSet<String> f_used = new HashSet<String>();
		HashSet<String> f_sharp_used = new HashSet<String>();
		HashSet<String> g_used = new HashSet<String>();
		HashSet<String> g_sharp_used = new HashSet<String>();
		HashSet<String> a_used = new HashSet<String>();
		HashSet<String> a_sharp_used = new HashSet<String>();
		HashSet<String> b_used = new HashSet<String>();
		
		
		
		
		String[] nextLine;
		
	
		while ((nextLine = reader.readNext()) != null) 
		{
			if (nextLine != null) 
			{	
				//ensure we don't write the headings
				if(!nextLine[4].equals("key") || !nextLine[4].equals("Key"))
				{
					//dont include rows with any of the following values
					if(nextLine[3].equals("0.0") || nextLine[3].equals("nan") ||nextLine[6].equals("0.0")||nextLine[1].equals("nan"))
					{
						//do nothing
					}
					else
					{
						//write to the appropriate key file
						//check for null values in hotness
						if(nextLine[4].equals("0"))
						{
							if(c_used.contains(nextLine[0]))
							{
								//do nothing
							}
							else
							{
								C.writeNext(nextLine);
								c_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("1"))
						{	
							if(c_sharp_used.contains(nextLine[0]))
							{
								//do nothing
							}
							else
							{
								C_Sharp.writeNext(nextLine);
								c_sharp_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("2"))
						{	
							if(d_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								D.writeNext(nextLine);
								d_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("3"))
						{	
							if(d_sharp_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								D_Sharp.writeNext(nextLine);
								d_sharp_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("4"))
						{	
							if(e_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								E.writeNext(nextLine);
								e_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("5"))
						{	
							if(f_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								F.writeNext(nextLine);
								f_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("6"))
						{	
							if(f_sharp_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								F_Sharp.writeNext(nextLine);
								f_sharp_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("7"))
						{	
							if(g_used.contains(nextLine[0]))
							{
							
							}
							else
							{
								G.writeNext(nextLine);
								g_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("8"))
						{	
							if(g_sharp_used.contains(nextLine[0]))
							{
								
							}	
							else
							{
								G_Sharp.writeNext(nextLine);
								g_sharp_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("9"))
						{	
							if(a_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								A.writeNext(nextLine);
								a_used.add(nextLine[0]);
							}
							
						}
						else if(nextLine[4].equals("10"))
						{	
							if(a_sharp_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								A_Sharp.writeNext(nextLine);
								a_sharp_used.add(nextLine[0]);
							}
						}
						else if(nextLine[4].equals("11"))
						{	
							if(b_used.contains(nextLine[0]))
							{
								
							}
							else
							{
								B.writeNext(nextLine);
								b_used.add(nextLine[0]);
							}
						}
					}
				}
			}
		}
		reader.close();
		C.close();
		C_Sharp.close();
		D.close();
		D_Sharp.close();
		E.close();
		F.close();
		F_Sharp.close();
		G.close();
		G_Sharp.close();
		A.close();
		A_Sharp.close();
		B.close();
		
	}
	//uses arraylist which have already been filled, more efficient then reading csv again
	static void orderCSVbyHotness(ArrayList<String[]> maj,ArrayList<String[]> min, String csv)throws java.io.IOException
	{	
		ArrayList<String[]> sortedRows = new ArrayList<String[]>();
		
		sortedRows.addAll(maj);
		sortedRows.addAll(min);
		
		Collections.sort(sortedRows,new Comparator<String[]>() 
		{
			public int compare(String[] strings, String[] otherStrings)
			{
				return strings[3].compareTo(otherStrings[3]);
			}
		});
		//open the csv and rewrite it sorted on hotness
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		
		for (String[] track : sortedRows) 
		{
			writer.writeNext(track);
		}
		writer.close();
		
	}
	//Calls sorting method on all key csv's
	static void sortAllCSVs()throws java.io.IOException
	{	
		orderCSVbyHotness(cMaj,cMin,"key_C.csv");
		orderCSVbyHotness(cSrpMaj,cSrpMin,"key_C_Sharp.csv");
		orderCSVbyHotness(dMaj,dMin,"key_D.csv");
		orderCSVbyHotness(dSrpMaj,dSrpMin,"key_D_Sharp.csv");
		orderCSVbyHotness(eMaj,eMin,"key_E.csv");
		orderCSVbyHotness(fMaj,fMin,"key_F.csv");
		orderCSVbyHotness(fSrpMaj,fSrpMin,"key_F_Sharp.csv");
		orderCSVbyHotness(gMaj,gMin,"key_G.csv");
		orderCSVbyHotness(gSrpMaj,gSrpMin,"key_G_Sharp.csv");
		orderCSVbyHotness(aMaj,aMin,"key_A.csv");
		orderCSVbyHotness(aSrpMaj,aSrpMin,"key_A_Sharp.csv");
		orderCSVbyHotness(bMaj,bMin,"key_B.csv");		
	}
	//orders the combined csv by hotness
	static void orderCombinedCSVbyHotness(String csv)throws java.io.IOException
	{
		CSVReader reader = new CSVReader(new FileReader(csv), ',' , '"' , 0);
		ArrayList<String[]> sortedRows = new ArrayList<String[]>();
		String[] nextLine;
		
		while ((nextLine = reader.readNext()) != null) 
		{
			if (nextLine != null) 
			{	
				//ensure we don't write the headings
				if(!nextLine[3].equals("song_hotttnesss"))
				{						
					//add all rows to list
					sortedRows.add(nextLine);											
					
				}
			}
		}
		Collections.sort(sortedRows,new Comparator<String[]>() 
		{
			public int compare(String[] strings, String[] otherStrings)
			{
				return strings[3].compareTo(otherStrings[3]);
			}
		});
		reader.close();
		//open the csv and rewrite it sorted on hotness
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		
		for (String[] track : sortedRows) 
		{
			writer.writeNext(track);
		}
		writer.close();
		
	}
	
	
	static void writeCSVtoNewCSV(ArrayList<String[]> list,CSVWriter writer)throws java.io.IOException
	{	
		for (String[] sa : list) 
		{
			writer.writeNext(sa);	
		}	
	}
	
	//Combines all of the key CSVs into one csv. 
	//The data has been cleaned so less memory now
	static void makeOneCSV(String s)throws java.io.IOException
	{	
		CSVWriter fullFile = new CSVWriter(new FileWriter(s));
		
		writeCSVtoNewCSV(cMaj,fullFile);
		writeCSVtoNewCSV(cMin,fullFile);
		writeCSVtoNewCSV(cSrpMaj,fullFile);
		writeCSVtoNewCSV(cSrpMin,fullFile);
		writeCSVtoNewCSV(dMaj,fullFile);
		writeCSVtoNewCSV(dMin,fullFile);
		writeCSVtoNewCSV(dSrpMaj,fullFile);
		writeCSVtoNewCSV(dSrpMin,fullFile);
		writeCSVtoNewCSV(eMaj,fullFile);
		writeCSVtoNewCSV(eMin,fullFile);
		writeCSVtoNewCSV(fMaj,fullFile);
		writeCSVtoNewCSV(fMin,fullFile);
		writeCSVtoNewCSV(fSrpMaj,fullFile);
		writeCSVtoNewCSV(fSrpMin,fullFile);
		
		writeCSVtoNewCSV(gMaj,fullFile);
		writeCSVtoNewCSV(gMin,fullFile);
		writeCSVtoNewCSV(gSrpMaj,fullFile);
		writeCSVtoNewCSV(gSrpMin,fullFile);
		writeCSVtoNewCSV(aMaj,fullFile);
		writeCSVtoNewCSV(aMin,fullFile);
		writeCSVtoNewCSV(aSrpMaj,fullFile);
		writeCSVtoNewCSV(aSrpMin,fullFile);
		writeCSVtoNewCSV(bMaj,fullFile);
		writeCSVtoNewCSV(bMin,fullFile);
		
		fullFile.close();
	}
	
	//make sure makeKeyCsvs() has run
	//Fill arraylists 
	static void fillArrayLists()throws java.io.IOException
	{	
		CSVReader C = new CSVReader(new FileReader("key_C.csv"), ',' , '"' , 0);
		CSVReader C_Sharp = new CSVReader(new FileReader("key_C_Sharp.csv"), ',' , '"' , 0);
		CSVReader D = new CSVReader(new FileReader("key_D.csv"), ',' , '"' , 0);
		CSVReader D_Sharp = new CSVReader(new FileReader("key_D_Sharp.csv"), ',' , '"' , 0);
		CSVReader E = new CSVReader(new FileReader("key_E.csv"), ',' , '"' , 0);
		CSVReader F = new CSVReader(new FileReader("key_F.csv"), ',' , '"' , 0);
		CSVReader F_Sharp = new CSVReader(new FileReader("key_F_Sharp.csv"), ',' , '"' , 0);
		CSVReader G = new CSVReader(new FileReader("key_G.csv"), ',' , '"' , 0);
		CSVReader G_Sharp = new CSVReader(new FileReader("key_G_Sharp.csv"), ',' , '"' , 0);
		CSVReader A = new CSVReader(new FileReader("key_A.csv"), ',' , '"' , 0);
		CSVReader A_Sharp = new CSVReader(new FileReader("key_A_Sharp.csv"), ',' , '"' , 0);
		CSVReader B = new CSVReader(new FileReader("key_B.csv"), ',' , '"' , 0);
		
		writeToArrays(C, cMaj,cMin);
		C.close();
		writeToArrays(C_Sharp, cSrpMaj,cSrpMin);
		C_Sharp.close();
		writeToArrays(D, dMaj,dMin);
		D.close();
		writeToArrays(D_Sharp, dSrpMaj,dSrpMin);
		D_Sharp.close();
		writeToArrays(E, eMaj,eMin);
		E.close();
		writeToArrays(F, fMaj,fMin);
		F.close();
		writeToArrays(F_Sharp, fSrpMaj,fSrpMin);
		F_Sharp.close();
		writeToArrays(G, gMaj,gMin);
		G.close();
		writeToArrays(G_Sharp, gSrpMaj,gSrpMin);
		G_Sharp.close();
		writeToArrays(A, aMaj,aMin);
		A.close();
		writeToArrays(A_Sharp, aSrpMaj,aSrpMin);
		A_Sharp.close();
		writeToArrays(B, bMaj,bMin);
		B.close();
	}
	static void writeToArrays(CSVReader read, ArrayList<String[]> maj,ArrayList<String[]> min)throws java.io.IOException
	{
		String[] nextLine;
		while ((nextLine = read.readNext()) != null) 
		{
			if (nextLine != null) 
			{	
				//ensure we don't write the headings
				if(!nextLine[4].equals("key") || !nextLine[4].equals("Key"))
				{
					if(nextLine[5].equals("1"))
					{
						maj.add(nextLine);
					}
					else if(nextLine[5].equals("0"))
					{
						min.add(nextLine);
					}
				}
			}
		}
	}
	
	//gets the average of a specified column
	static double getAverageHotness(String CombinedCSV, int columnNumber)throws java.io.IOException
	{
			CSVReader reader = new CSVReader(new FileReader(CombinedCSV), ',' , '"' , 0);
			double total = 0.0;
			double average = 0.0;
			int countRows = 0;
			
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) 
			{
				if(!nextLine[3].equals("song_hotttnesss"))
				{
					//listOfStringArrays.add(nextLine);
					double number = Double.parseDouble(nextLine[columnNumber]);
					total += number;
					countRows++;			
				}
			
			}
			reader.close();
			return average = (total/countRows);
	}
	
	//method for rounding doubles
	public static double round(double value, int places) throws java.io.IOException
	{
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	//0 is minor, 1 is major
	//Prints the percentage of the dataset each key takes up, lets us know most popular key
	static void getKeyDistributionAsPercentage()throws java.io.IOException
	{	

		double c = cMaj.size();		
		double c_sharp = cSrpMaj.size();
		double d =  dMaj.size();			
		double d_sharp = dSrpMaj.size();
		double e = eMaj.size();
		double f = fMaj.size();
		double f_sharp = fSrpMaj.size();
		double g = gMaj.size();
		double g_sharp = gSrpMaj.size();
		double a = aMaj.size();					
		double a_sharp = aSrpMaj.size();
		double b = bMaj.size();
		
		double cMinor = cMin.size();
		double c_sharpMinor = cSrpMin.size();
		double dMinor = dMin.size();				
		double d_sharpMinor = dSrpMin.size();
		double eMinor = eMin.size();
		double fMinor = fMin.size();
		double f_sharpMinor = fSrpMin.size();
		double gMinor = gMin.size();
		double g_sharpMinor = gSrpMin.size();
		double aMinor = aMin.size();					
		double a_sharpMinor = aSrpMin.size();
		double bMinor = bMin.size();
		
		double majorRows = c + c_sharp + d + d_sharp + e + f + f_sharp + g + g_sharp + a + a_sharp + b;
		double minorRows = cMinor + c_sharpMinor + dMinor + d_sharpMinor + eMinor + fMinor + f_sharpMinor + gMinor + g_sharpMinor + aMinor + a_sharpMinor + bMinor;

		double countRows = majorRows + minorRows;
		
		System.out.println("C major: \t" + (round((c/countRows*100),2)) +"% of the dataset");
		System.out.println("C minor:\t" + (round((cMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("C_Sharp major:\t" + (round((c_sharp/countRows*100),2)) +"% of the dataset");
		System.out.println("C_Sharp minor:\t" + (round((c_sharpMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("D major:\t" + (round((d/countRows*100),2)) +"% of the dataset");
		System.out.println("D minor:\t" + (round((dMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("D_Sharp:\t" + (round((d_sharp/countRows*100),2)) +"% of the dataset");
		System.out.println("D_Sharp minor:\t" + (round((d_sharpMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("E major:\t" + (round((e/countRows*100),2)) +"% of the dataset");
		System.out.println("E minor:\t" + (round((eMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("F major:\t" + (round((f/countRows*100),2)) +"% of the dataset");
		System.out.println("F minor:\t" + (round((fMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("F_Sharp major:\t" + (round((f_sharp/countRows*100),2)) +"% of the dataset");
		System.out.println("F_Sharp minor:\t" + (round((f_sharpMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("G major:\t" + (round((g/countRows*100),2)) +"% of the dataset");
		System.out.println("G minor:\t" + (round((gMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("G_Sharp major:\t" + (round((g_sharp/countRows*100),2)) +"% of the dataset");
		System.out.println("G_Sharp minor:\t" + (round((g_sharpMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("A major:\t" + (round((a/countRows*100),2)) +"% of the dataset");
		System.out.println("A minor:\t" + (round((aMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("A_Sharp major:\t" + (round((a_sharp/countRows*100),2)) +"% of the dataset");
		System.out.println("A_Sharp minor:\t" + (round((a_sharpMinor/countRows*100),2)) +"% of the dataset");
		
		System.out.println("B major:\t" + (round((b/countRows*100),2)) +"% of the dataset");
		System.out.println("B minor:\t" + (round((bMinor/countRows*100),2)) +"% of the dataset");		
	}
	//this method will get the min and max values of a given column
   //Choose what colummn in parameters
   static void getMinAndMaxValues(String csvName, int columnNumber) throws java.io.IOException
   {
		CSVReader reader = new CSVReader(new FileReader(csvName), ',' , '"' , 0);
		String[] nextLine;
		double min =0;
		double max =0;
		
		//get first values
		nextLine = reader.readNext();
		if(nextLine != null)
		{
			min = Double.parseDouble(nextLine[columnNumber]);
			max = Double.parseDouble(nextLine[columnNumber]);
		

			while ((nextLine = reader.readNext()) != null) 
			{
				if (nextLine != null) 
				{
					double number = Double.parseDouble(nextLine[columnNumber]);
					if(number < min)
						min = number;
					if(number > max)
						max = number;	
				}
			}
		}
		
		reader.close();
		System.out.println("min value: " + min);
		System.out.println("max value: " + max);


   }
	
	//Method to calculate variance		
	static void calculateVariance(String csvName, int columnNumber) throws java.io.IOException
	{
	   //enter read file
		CSVReader reader = new CSVReader(new FileReader(csvName), ',' , '"' , 0);
		double totalOfAllHotness = 0.0;
		String[] nextLine;
		double average = 0.0;

		int count = 0;

		ArrayList<String[]> listOfStringArrays = new ArrayList<String[]>();

		while ((nextLine = reader.readNext()) != null) 
		{
			if(!nextLine[columnNumber].equals("song_hotttnesss"))
			{
				//listOfStringArrays.add(nextLine);
				double number = Double.parseDouble(nextLine[columnNumber]);
				totalOfAllHotness += number;
				count++;
				listOfStringArrays.add(nextLine);
			}
			
		}
		average = (totalOfAllHotness/count);
		
		System.out.println("count: " + count);
		System.out.println("Average is:" + average);
		
		//Then for each number: subtract the Mean and square the result
		for (String[] sa : listOfStringArrays) 
		{
			double number = Double.parseDouble(sa[columnNumber]);
			number = number - average;
			number = (number * number);
			String num = String.valueOf(number);
			sa[columnNumber] = num;
			
		}
		
		//Then work out the average of those squared differences.
		totalOfAllHotness = 0.0;
		
		for (String[] sa : listOfStringArrays) 
		{
			double number = Double.parseDouble(sa[columnNumber]);
			totalOfAllHotness += number;		
		}
		
		System.out.println("Variance is: " + totalOfAllHotness/count);

		//close the files
		reader.close();
	}
	
	static void getHotnessDispersion(ArrayList<String[]> maj,ArrayList<String[]> min) throws java.io.IOException
	{	
		int hotnessColumn = 3;
		double countA = 0, countB = 0, countC = 0, countD = 0, countE = 0, total = 0;
		double countAminor = 0, countBminor = 0, countCminor = 0, countDminor = 0, countEminor = 0, totalminor = 0;

		for (String[] nextLine : maj) 
		{
			double number = Double.parseDouble(nextLine[hotnessColumn]);

			if(number > 0 && number < 0.2)
				countA++;
			else if(number > 0.2 && number < 0.4)
				countB++;
			else if(number > 0.4 && number < 0.6)
				countC++;
			else if(number > 0.6 && number < 0.8)
				countD++;
			else if(number > .8 && number <= 1)
				countE++;

			total++;
		}
		//Handle Minor
		for(String[] nextLine : min) 
		{
			double number = Double.parseDouble(nextLine[hotnessColumn]);

			if(number > 0 && number < 0.2)
				countAminor++;
			else if(number > 0.2 && number < 0.4)
				countBminor++;
			else if(number > 0.4 && number < 0.6)
				countCminor++;
			else if(number > 0.6 && number < 0.8)
				countDminor++;
			else if(number > .8 && number <= 1)
				countEminor++;

			totalminor++;
		}
		
		System.out.println("===============================================");
		System.out.println("Major:");
		System.out.println("0 - 20%: " + (round(countA/total*100,2)) + "%");
		System.out.println("20 - 40%: " + (round((countB/total*100),2)) + "%");
		System.out.println("40 - 60%: " + (round((countC/total*100),2)) + "%");
		System.out.println("60 - 80%: " + (round((countD/total*100),2)) + "%");
		System.out.println("80 - 100%: " + (round((countE/total*100),2)) + "%");
		System.out.println();
		
		System.out.println("Minor");
		System.out.println("0 - 20%: " + (round(countAminor/totalminor*100,2)) + "%");
		System.out.println("20 - 40%: " + (round((countBminor/totalminor*100),2)) + "%");
		System.out.println("40 - 60%: " + (round((countCminor/totalminor*100),2)) + "%");
		System.out.println("60 - 80%: " + (round((countDminor/totalminor*100),2)) + "%");
		System.out.println("80 - 100%: " + (round((countEminor/totalminor*100),2)) + "%");
		System.out.println("===============================================");
		System.out.println();
		
	}
	
	//method to print hotness dispesion is each key
	static void getHotnessDispersionEachKey()throws java.io.IOException
	{
		System.out.println("C: ");
		getHotnessDispersion(cMaj,cMin);
		
		System.out.println("C_Sharp: ");
		getHotnessDispersion(cSrpMaj,cSrpMin);
		
		System.out.println("D: ");
		getHotnessDispersion(dMaj, dMin);
		
		System.out.println("D_Sharp: ");
		getHotnessDispersion(dSrpMaj, dSrpMin);
		
		System.out.println("E: ");
		getHotnessDispersion(eMaj, eMin);
		
		System.out.println("F: ");
		getHotnessDispersion(fMaj, fMin);
		
		System.out.println("F_Sharp: ");
		getHotnessDispersion(fSrpMaj, fSrpMin);
		
		System.out.println("G: ");
		getHotnessDispersion(gMaj,gMin);
		
		System.out.println("G_Sharp: ");
		getHotnessDispersion(gSrpMaj, gSrpMin);
		
		System.out.println("A: ");
		getHotnessDispersion(aMaj, aMin);
		
		System.out.println("A_Sharp: ");
		getHotnessDispersion(aSrpMaj, aSrpMin);
		
		System.out.println("B: ");
		getHotnessDispersion(bMaj, bMin);
	}
	
	//must have the hotness column ordered low to high
	//get median and quartile values of given csv and column number
   static void getMedianAndQuartileValues(String csvName, int columnNumber) throws java.io.IOException
   {
	    orderCombinedCSVbyHotness(csvName);
		//enter read file
		CSVReader reader = new CSVReader(new FileReader(csvName), ',' , '"' , 0);

		String[] nextLine;

		int lower,mid,high;
		int count = 0;

		ArrayList<String[]> listOfStringArrays = new ArrayList<String[]>();

		while ((nextLine = reader.readNext()) != null) 
		{
			listOfStringArrays.add(nextLine);
		}
		count  = listOfStringArrays.size();

		System.out.println("count: " + count);

		lower = count/4;
		mid = count/2;
		high = mid + lower;

		String[] l;
		String[] m;
		String[] h;

		l = listOfStringArrays.get(lower);
		m = listOfStringArrays.get(mid);
		h = listOfStringArrays.get(high);

		System.out.println("25th Quartile: " + l[columnNumber]);
		System.out.println("median " + m[columnNumber]);
		System.out.println("75th Quartile: " + h[columnNumber]);
		
		double q1 = Double.parseDouble(l[columnNumber]);
		double q3 = Double.parseDouble(h[columnNumber]);
		
		double iqr = q3 - q1;
		
		iqr = iqr * 1.5;
		
		
		
		System.out.println("Outliers after: " + (iqr + q3));


		//close the files
		reader.close();
	   
   }
   
	//method to count the bpm categories in a key sorted csv
	//read in csv of key, get a total count of rows
	//split into 4 sections by popularity...sorting needed
	//in each section do a count of bpm categories....i.e. slow - fast
	static void getBPMcounts(ArrayList<String[]> list) throws java.io.IOException
	{
		int hotnessColumn = 3;

		ArrayList<String[]> lowPopularity = new ArrayList<String[]>();
		ArrayList<String[]> lowMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highPopularity = new ArrayList<String[]>();

		double hotness = 0.0;
		
		for (String[] sa : list) 
		{
			hotness = Double.parseDouble(sa[hotnessColumn]);
			
			if(hotness <= 0.25)
			{
				lowPopularity.add(sa);
			}
			else if(hotness > 0.25 && hotness <=0.50)
			{
				lowMediumPopularity.add(sa);
			}
			else if(hotness > 0.50 && hotness <= 0.75)
			{
				highMediumPopularity.add(sa);
			}
			else if(hotness > 0.75 && hotness <= 1.00)
			{
				highPopularity.add(sa);
			}
			
		}	
		System.out.println("-------------------------------------");
		countBPMranges(lowPopularity, "Low Popularity:");
		countBPMranges(lowMediumPopularity, "Low medium Popularity:");
		countBPMranges(highMediumPopularity, "High Medium Popularity:");
		countBPMranges(highPopularity, "High Popularity:");
		System.out.println("-------------------------------------");
	}
	//takes array of popularity and counts bpm range occurances
	static void countBPMranges(ArrayList<String[]> popularityArray, String rangeValue) throws java.io.IOException
	{
		int bpmColumn = 6;
		
		double verySlowCount = 0;
		double slowCount =0;
		double moderateCount = 0;
		double fastCount = 0;
		double vFastCount = 0;
		
		double bpm = 0.0;
		
		double numEntries = popularityArray.size();

		//make BPM ranges, count number of occurances of each range
		for (String[] sa : popularityArray) 
		{
			bpm = Double.parseDouble(sa[bpmColumn]);
			if(isBetween(bpm,0.0,60.0))
				verySlowCount++;
			else if(isBetween(bpm,60.01,80.0))
				slowCount++;
			else if(isBetween(bpm,80.01,110.0))
				moderateCount++;
			else if(isBetween(bpm,110.01,130.0))
				fastCount++;
			else if(bpm > 130.00)
				vFastCount++;		
		}
		System.out.println(rangeValue);
		System.out.println("0 - 60bpm: " + (round(verySlowCount/numEntries*100,2))+ "%");
		System.out.println("60 - 80bpm: " + (round(slowCount/numEntries*100,2))+ "%");
		System.out.println("80 - 110bpm: " + (round(moderateCount/numEntries*100,2))+ "%");
		System.out.println("110 - 130bpm: " + (round(fastCount/numEntries*100,2))+ "%");
		System.out.println("130+ bpm: " + (round(vFastCount/numEntries*100,2))+ "%");
		
		System.out.println();
	}
	//Prints bpm percentages for each popularity range of a csv
	static void printBPMrangeForEachKey() throws java.io.IOException
	{
		System.out.println("C MAJOR:");
		getBPMcounts(cMaj);
		
		System.out.println("C MINOR:");
		getBPMcounts(cMin);
		
		System.out.println("C Sharp MAJOR:");
		getBPMcounts(cSrpMaj);
		
		System.out.println("C Sharp MAJOR:");
		getBPMcounts(cSrpMin);
		
		System.out.println("D MAJOR:");
		getBPMcounts(dMaj);
		
		System.out.println("D MINOR:");
		getBPMcounts(dMin);
		
		System.out.println("D Sharp MAJOR:");
		getBPMcounts(dSrpMaj);
		
		System.out.println("D Sharp MINOR:");
		getBPMcounts(dSrpMin);
		
		System.out.println("E MAJOR:");
		getBPMcounts(eMaj);
		
		System.out.println("E MINOR:");
		getBPMcounts(eMin);
		
		System.out.println("F MAJOR:");
		getBPMcounts(fMaj);
		
		System.out.println("F MINOR:");
		getBPMcounts(fMin);
		
		System.out.println("F Sharp MAJOR:");
		getBPMcounts(fSrpMaj);
		
		System.out.println("F Sharp MINOR:");
		getBPMcounts(fSrpMin);
		
		System.out.println("G Major:");
		getBPMcounts(gMaj);
		
		System.out.println("G Minor:");
		getBPMcounts(gMin);
		
		System.out.println("G Sharp Major:");
		getBPMcounts(gSrpMaj);
		
		System.out.println("G Sharp Minor:");
		getBPMcounts(gSrpMin);
		
		System.out.println("A MAjor:");
		getBPMcounts(aMaj);
		
		System.out.println("A MINOR:");
		getBPMcounts(aMin);
		
		System.out.println("A Sharp MAJOR:");
		getBPMcounts(aSrpMaj);
		
		System.out.println("A Sharp MINOR:");
		getBPMcounts(aSrpMin);
		
		System.out.println("B Major:");
		getBPMcounts(bMaj);
		
		System.out.println("B MINOR:");
		getBPMcounts(bMin);
	}
	
	//Get the loudness catgories
	// range from -60 to 0, silence to v loud
	public static void loudnessCategories(ArrayList<String[]> list)throws java.io.IOException
	{
		int hotnessColumn = 3;

		ArrayList<String[]> lowPopularity = new ArrayList<String[]>();
		ArrayList<String[]> lowMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highPopularity = new ArrayList<String[]>();

		double hotness = 0.0;
		
		for (String[] sa : list) 
		{
			hotness = Double.parseDouble(sa[hotnessColumn]);
			
			if(hotness <= 0.25)
			{
				lowPopularity.add(sa);
			}
			else if(hotness > 0.25 && hotness <=0.50)
			{
				lowMediumPopularity.add(sa);
			}
			else if(hotness > 0.50 && hotness <= 0.75)
			{
				highMediumPopularity.add(sa);
			}
			else if(hotness > 0.75 && hotness <= 1.00)
			{
				highPopularity.add(sa);
			}
			
		}

		System.out.println("-------------------------------------");
		countLoudnessRanges(lowPopularity, "Low Popularity:");
		countLoudnessRanges(lowMediumPopularity, "Low medium Popularity:");
		countLoudnessRanges(highMediumPopularity, "High Medium Popularity:");
		countLoudnessRanges(highPopularity, "High Popularity:");
		System.out.println("-------------------------------------");		
					
	}
	static void countLoudnessRanges(ArrayList<String[]> popularityArray, String rangeValue) throws java.io.IOException
	{
		int loudnessColumn = 7;
		
		double veryLow = 0;
		double low =0;
		double moderate = 0;
		double loud = 0;
		double vLoud = 0;
		
		double loudness = 0.0;
		
		double numEntries = popularityArray.size();

		//make BPM ranges, count number of occurances of each range
		for (String[] sa : popularityArray) 
		{
			loudness = Double.parseDouble(sa[loudnessColumn]);
			if(loudness <= -48.)
				veryLow++;
			else if(loudness > -48. && loudness <=-36.)
				low++;
			else if(loudness > -36. && loudness <=-24.)
				moderate++;
			else if(loudness > -24. && loudness <=-12.)
				loud++;
			else if(loudness > -12.)
				vLoud++; 

			
		}
		System.out.println(rangeValue);
		System.out.println("Very low Volume:  " + (round(veryLow/numEntries*100,2))+ "%");
		System.out.println("low Volume:       " + (round(low/numEntries*100,2))+ "%");
		System.out.println("Moderate volume:  " + (round(moderate/numEntries*100,2))+ "%");
		System.out.println("Loud Volume:      " + (round(loud/numEntries*100,2))+ "%");
		System.out.println("Very Loud Volume: " + (round(vLoud/numEntries*100,2))+ "%");
		
		System.out.println();
	}
	
	////Prints loudness percentages for each popularity range of a csv
	static void printLoudnessRangeForEachKey() throws java.io.IOException
	{
		System.out.println("C MAJOR:");
		loudnessCategories(cMaj);
		
		System.out.println("C MINOR:");
		loudnessCategories(cMin);
		
		System.out.println("C Sharp MAJOR:");
		loudnessCategories(cSrpMaj);
		
		System.out.println("C Sharp MAJOR:");
		loudnessCategories(cSrpMin);
		
		System.out.println("D MAJOR:");
		loudnessCategories(dMaj);
		
		System.out.println("D MINOR:");
		loudnessCategories(dMin);
		
		System.out.println("D Sharp MAJOR:");
		loudnessCategories(dSrpMaj);
		
		System.out.println("D Sharp MINOR:");
		loudnessCategories(dSrpMin);
		
		System.out.println("E MAJOR:");
		loudnessCategories(eMaj);
		
		System.out.println("E MINOR:");
		loudnessCategories(eMin);
		
		System.out.println("F MAJOR:");
		loudnessCategories(fMaj);
		
		System.out.println("F MINOR:");
		loudnessCategories(fMin);
		
		System.out.println("F Sharp MAJOR:");
		loudnessCategories(fSrpMaj);
		
		System.out.println("F Sharp MINOR:");
		loudnessCategories(fSrpMin);
		
		System.out.println("G Major:");
		loudnessCategories(gMaj);
		
		System.out.println("G Minor:");
		loudnessCategories(gMin);
		
		System.out.println("G Sharp Major:");
		loudnessCategories(gSrpMaj);
		
		System.out.println("G Sharp Minor:");
		loudnessCategories(gSrpMin);
		
		System.out.println("A MAjor:");
		loudnessCategories(aMaj);
		
		System.out.println("A MINOR:");
		loudnessCategories(aMin);
		
		System.out.println("A Sharp MAJOR:");
		loudnessCategories(aSrpMaj);
		
		System.out.println("A Sharp MINOR:");
		loudnessCategories(aSrpMin);
		
		System.out.println("B Major:");
		loudnessCategories(bMaj);
		
		System.out.println("B MINOR:");
		loudnessCategories(bMin);
	}
		
	//check if a double is between two values
	public static boolean isBetween(Double x, Double lower, Double upper) 
	{
		return lower <= x && x <= upper;
	}
	
	public static void artistFamiliarityDistribution(ArrayList<String[]> list)throws java.io.IOException
	{
		int hotnessColumn = 3;

		ArrayList<String[]> lowPopularity = new ArrayList<String[]>();
		ArrayList<String[]> lowMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highPopularity = new ArrayList<String[]>();

		double hotness = 0.0;
		
		for (String[] sa : list) 
		{
			hotness = Double.parseDouble(sa[hotnessColumn]);
			
			if(hotness <= 0.25)
			{
				lowPopularity.add(sa);
			}
			else if(hotness > 0.25 && hotness <=0.50)
			{
				lowMediumPopularity.add(sa);
			}
			else if(hotness > 0.50 && hotness <= 0.75)
			{
				highMediumPopularity.add(sa);
			}
			else if(hotness > 0.75 && hotness <= 1.00)
			{
				highPopularity.add(sa);
			}
			
		}

		System.out.println("=========================================");
		countArtistFamiliarityRanges(lowPopularity, "Low Popularity:");
		countArtistFamiliarityRanges(lowMediumPopularity, "Low medium Popularity:");
		countArtistFamiliarityRanges(highMediumPopularity, "High Medium Popularity:");
		countArtistFamiliarityRanges(highPopularity, "High Popularity:");
		System.out.println("=========================================");		
					
	}
	static void countArtistFamiliarityRanges(ArrayList<String[]> popularityArray, String rangeValue) throws java.io.IOException
	{
		int familiarityColumn = 1;
		
		double vLow = 0;
		double low =0;
		double moderate = 0;
		double high = 0;
		double vHigh = 0;
		
		double familiarity = 0.0;
		
		double numEntries = popularityArray.size();

		//make BPM ranges, count number of occurances of each range
		for (String[] sa : popularityArray) 
		{
			familiarity = Double.parseDouble(sa[familiarityColumn]);
			if(familiarity <= .20)
				vLow++;
			else if(familiarity > .20 && familiarity <= .4)
				low++;
			else if(familiarity > .4 && familiarity <= .60)
				moderate++;
			else if(familiarity > .6 && familiarity <= .8)
				high++;
			else if(familiarity > .8)
				vHigh++; 			
		}
		System.out.println(rangeValue);
		System.out.println("-------------------");
		System.out.println("Very low Familiarity:  " + (round(vLow/numEntries*100,2))+ "%");
		System.out.println("low Familiarity:       " + (round(low/numEntries*100,2))+ "%");
		System.out.println("Moderate Familiarity:  " + (round(moderate/numEntries*100,2))+ "%");
		System.out.println("High Familiarity:      " + (round(high/numEntries*100,2))+ "%");
		System.out.println("Very high Familiarity: " + (round(vHigh/numEntries*100,2))+ "%");
		
		System.out.println();
	}
	////Prints Familiarity percentages for each popularity range of a csv
	static void printFamiliarityRangeForEachKey() throws java.io.IOException
	{
		System.out.println("C MAJOR:");
		artistFamiliarityDistribution(cMaj);
		
		System.out.println("C MINOR:");
		artistFamiliarityDistribution(cMin);
		
		System.out.println("C Sharp MAJOR:");
		artistFamiliarityDistribution(cSrpMaj);
		
		System.out.println("C Sharp MAJOR:");
		artistFamiliarityDistribution(cSrpMin);
		
		System.out.println("D MAJOR:");
		artistFamiliarityDistribution(dMaj);
		
		System.out.println("D MINOR:");
		artistFamiliarityDistribution(dMin);
		
		System.out.println("D Sharp MAJOR:");
		artistFamiliarityDistribution(dSrpMaj);
		
		System.out.println("D Sharp MINOR:");
		artistFamiliarityDistribution(dSrpMin);
		
		System.out.println("E MAJOR:");
		artistFamiliarityDistribution(eMaj);
		
		System.out.println("E MINOR:");
		artistFamiliarityDistribution(eMin);
		
		System.out.println("F MAJOR:");
		artistFamiliarityDistribution(fMaj);
		
		System.out.println("F MINOR:");
		artistFamiliarityDistribution(fMin);
		
		System.out.println("F Sharp MAJOR:");
		artistFamiliarityDistribution(fSrpMaj);
		
		System.out.println("F Sharp MINOR:");
		artistFamiliarityDistribution(fSrpMin);
		
		System.out.println("G Major:");
		artistFamiliarityDistribution(gMaj);
		
		System.out.println("G Minor:");
		artistFamiliarityDistribution(gMin);
		
		System.out.println("G Sharp Major:");
		artistFamiliarityDistribution(gSrpMaj);
		
		System.out.println("G Sharp Minor:");
		artistFamiliarityDistribution(gSrpMin);
		
		System.out.println("A MAjor:");
		artistFamiliarityDistribution(aMaj);
		
		System.out.println("A MINOR:");
		artistFamiliarityDistribution(aMin);
		
		System.out.println("A Sharp MAJOR:");
		artistFamiliarityDistribution(aSrpMaj);
		
		System.out.println("A Sharp MINOR:");
		artistFamiliarityDistribution(aSrpMin);
		
		System.out.println("B Major:");
		artistFamiliarityDistribution(bMaj);
		
		System.out.println("B MINOR:");
		artistFamiliarityDistribution(bMin);
	}
	
	
	//Method for finding the artist hotness distribution in relation to song hotness
	public static void artistHotnessDistribution(ArrayList<String[]> list)throws java.io.IOException
	{
		int hotnessColumn = 3;

		ArrayList<String[]> lowPopularity = new ArrayList<String[]>();
		ArrayList<String[]> lowMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highMediumPopularity = new ArrayList<String[]>();
		ArrayList<String[]> highPopularity = new ArrayList<String[]>();

		double hotness = 0.0;
		
		for (String[] sa : list) 
		{
			hotness = Double.parseDouble(sa[hotnessColumn]);
			
			if(hotness <= 0.25)
			{
				lowPopularity.add(sa);
			}
			else if(hotness > 0.25 && hotness <=0.50)
			{
				lowMediumPopularity.add(sa);
			}
			else if(hotness > 0.50 && hotness <= 0.75)
			{
				highMediumPopularity.add(sa);
			}
			else if(hotness > 0.75 && hotness <= 1.00)
			{
				highPopularity.add(sa);
			}
			
		}

		System.out.println("=========================================");
		countArtistHotnessRanges(lowPopularity, "Low Popularity:");
		countArtistHotnessRanges(lowMediumPopularity, "Low medium Popularity:");
		countArtistHotnessRanges(highMediumPopularity, "High Medium Popularity:");
		countArtistHotnessRanges(highPopularity, "High Popularity:");
		System.out.println("=========================================");		
					
	}
	static void countArtistHotnessRanges(ArrayList<String[]> popularityArray, String rangeValue) throws java.io.IOException
	{
		int artistHotnessColumn = 2;
		
		double vLow = 0;
		double low =0;
		double moderate = 0;
		double high = 0;
		double vHigh = 0;
		
		double artistHotness = 0.0;
		
		double numEntries = popularityArray.size();

		//make BPM ranges, count number of occurances of each range
		for (String[] sa : popularityArray) 
		{
			artistHotness = Double.parseDouble(sa[artistHotnessColumn]);
			if(artistHotness <= .20)
				vLow++;
			else if(artistHotness > .20 && artistHotness <= .4)
				low++;
			else if(artistHotness > .4 && artistHotness <= .60)
				moderate++;
			else if(artistHotness > .6 && artistHotness <= .8)
				high++;
			else if(artistHotness > .8)
				vHigh++; 			
		}
		System.out.println(rangeValue);
		System.out.println("-------------------");
		System.out.println("Very low artistHotness:  " + (round(vLow/numEntries*100,2))+ "%");
		System.out.println("low artistHotness:       " + (round(low/numEntries*100,2))+ "%");
		System.out.println("Moderate artistHotness:  " + (round(moderate/numEntries*100,2))+ "%");
		System.out.println("High artistHotness:      " + (round(high/numEntries*100,2))+ "%");
		System.out.println("Very high artistHotness: " + (round(vHigh/numEntries*100,2))+ "%");
		
		System.out.println();
	}
	////Prints Familiarity percentages for each popularity range of a csv
	static void printArtistHotnessRangeForEachKey() throws java.io.IOException
	{
		System.out.println("C MAJOR:");
		artistHotnessDistribution(cMaj);
		
		System.out.println("C MINOR:");
		artistHotnessDistribution(cMin);
		
		System.out.println("C Sharp MAJOR:");
		artistHotnessDistribution(cSrpMaj);
		
		System.out.println("C Sharp MAJOR:");
		artistHotnessDistribution(cSrpMin);
		
		System.out.println("D MAJOR:");
		artistHotnessDistribution(dMaj);
		
		System.out.println("D MINOR:");
		artistHotnessDistribution(dMin);
		
		System.out.println("D Sharp MAJOR:");
		artistHotnessDistribution(dSrpMaj);
		
		System.out.println("D Sharp MINOR:");
		artistHotnessDistribution(dSrpMin);
		
		System.out.println("E MAJOR:");
		artistHotnessDistribution(eMaj);
		
		System.out.println("E MINOR:");
		artistHotnessDistribution(eMin);
		
		System.out.println("F MAJOR:");
		artistHotnessDistribution(fMaj);
		
		System.out.println("F MINOR:");
		artistHotnessDistribution(fMin);
		
		System.out.println("F Sharp MAJOR:");
		artistHotnessDistribution(fSrpMaj);
		
		System.out.println("F Sharp MINOR:");
		artistHotnessDistribution(fSrpMin);
		
		System.out.println("G Major:");
		artistHotnessDistribution(gMaj);
		
		System.out.println("G Minor:");
		artistHotnessDistribution(gMin);
		
		System.out.println("G Sharp Major:");
		artistHotnessDistribution(gSrpMaj);
		
		System.out.println("G Sharp Minor:");
		artistHotnessDistribution(gSrpMin);
		
		System.out.println("A MAjor:");
		artistHotnessDistribution(aMaj);
		
		System.out.println("A MINOR:");
		artistHotnessDistribution(aMin);
		
		System.out.println("A Sharp MAJOR:");
		artistHotnessDistribution(aSrpMaj);
		
		System.out.println("A Sharp MINOR:");
		artistHotnessDistribution(aSrpMin);
		
		System.out.println("B Major:");
		artistHotnessDistribution(bMaj);
		
		System.out.println("B MINOR:");
		artistHotnessDistribution(bMin);
	}
	
	static void predict(ArrayList<String[]> keyArray)
	{
		//normalize volume so it runs from 0 to 1
		ArrayList<String[]> normalizedVolume = normalize(keyArray, 7);
		ArrayList<String[]> normalizedBPM = normalize(keyArray, 6);
		
		
		//get average of both artist hotness and familiarity
		double artistHotnessAvg = getPopularAverageofColumn(keyArray, 2);
		double artistFamiliarityAvg = getPopularAverageofColumn(keyArray, 1);
		double volumeAverage = getPopularAverageofColumn(keyArray, 7);
		double bpmAverage = getPopularAverageofColumn(keyArray, 6);
		
		
		int euclideanColumn = 8;
		
		ArrayList<String[]> randomSongs = getRandomSongs(keyArray);
		
		ArrayList<String[]> songsWithNewComparableValue = new ArrayList<String[]>();
		
		for(String[] sa:randomSongs)
		{
			double artistHotness = Double.parseDouble(sa[2]);
			double artistFamiliarity = Double.parseDouble(sa[1]);
			double songVolume = Double.parseDouble(sa[7]);
			double bpm = Double.parseDouble(sa[6]);
			
			double aVal = Math.abs(artistHotness - artistHotnessAvg);
            double bVal = Math.abs(artistFamiliarity - artistFamiliarityAvg);
			double cVal = Math.abs(songVolume - volumeAverage);
			double dVal = Math.abs(bpm - bpmAverage);
			
            double euclidean = Math.sqrt((aVal*aVal) +(bVal*bVal) + (cVal*cVal) + (dVal*dVal));
			
			String [] songData = new String [sa.length + 1];
			
			for(int i = 0; i < sa.length;i++)
			{
				songData[i] = sa[i];
			}
			songData[sa.length] = Double.toString(euclidean);
			songsWithNewComparableValue.add(songData);
		}
		
		//now order by the euclidean value and print
		
		Collections.sort(songsWithNewComparableValue,new Comparator<String[]>() 
		{
			public int compare(String[] strings, String[] otherStrings)
			{
				return strings[euclideanColumn].compareTo(otherStrings[euclideanColumn]);
			}
		});
		
		for(String sa[]: songsWithNewComparableValue)
		{
			System.out.println(sa[3]);//("TrackID: " + sa[0] + "\t euclidean: " + sa[euclideanColumn] + "\t Actual hotness" + sa[3]);
		}
		
	}
	//get average of a column for songs that are popular
	// songs over the average popularity are popular
	static double getPopularAverageofColumn(ArrayList<String[]> keyArray, int columnNumber)
	{
		double total = 0.0;
		double average = 0.0;
		int countRows = 0;
		int hotnessColumn = 3;
		
		double theActualAverage =  getAverageofColumn(keyArray,hotnessColumn);
		
		//now get the average of popular songs
		for(String[] sa: keyArray)
		{
			double hotness = Double.parseDouble(sa[hotnessColumn]);
			if(hotness > 0.75)//theActualAverage)
			{
				double value = Double.parseDouble(sa[columnNumber]);
				//count in the new average
				total += value;
				countRows++;
			}
		}
		return average = total/countRows;
	}
	//gets the average of a specified column
	static double getAverageofColumn(ArrayList<String[]> keyArray, int columnNumber)
	{
		double total = 0.0;
		double average = 0.0;
		int countRows = 0;
		
		for(String [] sa: keyArray)
		{
			double number = Double.parseDouble(sa[columnNumber]);
			total += number;
			countRows++;				
		}
		return average = (total/countRows);
	}
	//gets a list of random songs for testing 
	static ArrayList<String[]> getRandomSongs(ArrayList<String[]> keyArray)
	{
		ArrayList<String[]> songs = new ArrayList<String[]>();
		
		Random randomGenerator = new Random();
		int index; 
		
		for(int i = 0; i < 30; i++)
		{
			index = randomGenerator.nextInt(keyArray.size());
			songs.add(keyArray.get(index));
		}	
		return songs;
	}
	//Normalize from 0 - 1 the column in parameter
	static ArrayList<String[]> normalize(ArrayList<String[]> keyArray, int column)
	{
		//get min and max values
		double min = Double.parseDouble(keyArray.get(0)[column]);
		double max = Double.parseDouble(keyArray.get(0)[column]);
		
		for(String[] sa: keyArray)
		{
			double num = Double.parseDouble(sa[column]);
			if(num < min)
				min = num;
			
			if(num > max)
				max = num;
		}
		
		for(String[] sa: keyArray)
		{
			double num = Double.parseDouble(sa[column]);			
			num = (num - min)/(max - min);			
			sa[column] = Double.toString(num);
		}
		return keyArray;
	}
}