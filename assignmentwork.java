import java.util.ArrayList;

public class assignmentwork 
{
	public static void main(String[] args)
	{
		String[] test = new String[] {"gojo", "google", "jogo", "bill", "pup", "cipher", "watchmen", "knight", "it", "stand", "sandman",
				"hydra", "surtr"};
		//String[] test0 = new String[] {"cat", "sbx", "log", "sun", "mug", "row", "job", "cox", "lap", "rat", "per", "dad", "car", "fig", "pig", "via", "low",
		//	"lox", "tea", "ate", "are", "dog", "tsl"};
		//lexo_radix(test);
		//lexo_radix(test0);
		
		int[] s1 = new int[] {32, 3};
		int[] t1 = new int[] {1, 2, 3, 52, 32, 54};
		
		int[] s2 = new int[] {89, 32, 54, 32, 3};
		int[] t2 = new int[] {54, 32, 99};
		
		int[] s3 = new int[] {0, 67};
		int[] t3 = new int[] {100, 5, 66, 2, 32, 90};
		
		int[] s4 = new int[] {};
		int[] t4 = new int[] {54, 32, 99};
		
		System.out.println(subint(s1, t1));
		System.out.println(subint(s2, t2));
		System.out.println(subint(s3, t3));
		System.out.println(subint(s4, t4));
	}
	
	//A lot of this code is from the textbook, with modifications to fit the assignment. I"ve added comments explaining the code to show that I understand the code I"m using. 
	public static void lexo_radix(String[] s)
	{
		//Creates an array of the desired size, with each position in the array being occupied by another array to handle overflow. 
		ArrayList<ArrayList<String>> buckets = new ArrayList<ArrayList<String>>();
		
	    for (int i = 0; i < 26; i++) 
	      {
	         buckets.add(new ArrayList<String>());
	      }
	    
	    int copyBackIndex = 0;
	      
	    int maxDigits = lexo_radixGetMaxLength(s);
	    
	    //This for loop iterates through order of magnitude. Instead of using powers of tens, I switched it to start at the farthest right
	    //letter, and simply keep going inwards, as my convert method fetches an index on it's own. 
	    for (int digitIndex = maxDigits - 1; digitIndex >= 0; digitIndex--) 
	    {
	    	//This for loop goes through every string input. The contents are modified from the tb to work with Strings. The try catch is for when
	    	//checking a string shorter than the longest string, in which case it defaults to zero: similar to 87 being 0 in the 100s place. 
	        for (int i = 0; i < s.length; i++) 
	        {
	        	char[] temp = s[i].toCharArray();
	        	try
	        	{
	        		buckets.get(convert(temp[digitIndex])).add(s[i]);
	        	}
	        	
	        	catch(Exception exception)
	        	{
	        		buckets.get(0).add(s[i]);
	        	}
	            
	         }
	         
	         // Copy buckets back into numbers array
	         copyBackIndex = 0;
	         
	         for (int i = 0; i < 26; i++) 
	         {
	            ArrayList<String> bucket = buckets.get(i);
	            
	            for (int j = 0; j < bucket.size(); j++) 
	            {
	               //Line below generated my answer for #4. 
	               //System.out.println(i + " " +  bucket.get(j));
	               s[copyBackIndex] = bucket.get(j);
	               copyBackIndex++;
	            }
	            
	            bucket.clear();
	         }
	       
	        /* This code was for generating the answer to #4. 
	        for (int i = 0; i < s.length; i++) 
	 	    {
	 	         System.out.println(s[i]);
	 	    }
	        */
	        System.out.println("\n");
	        
	      
	      }
	    
	    for (int i = 0; i < s.length; i++) 
	    {
	         System.out.println(s[i]);
	    }
	    
		
	}
	
	//This method is more or less copied from my excel sheet converter for test1. Turns a letter into it"s numerical position in the alphabet. 
	public static int convert(char letter)
	{	
		switch (letter) 
	    {
	        case 'a':
	            return 1;
	            
	        case 'b':
	            return 2;
	            
	        case 'c':
	            return 3;
	            
	        case 'd':
	            return 4;
	            
	        case 'e':
	            return 5;
	            
	        case 'f':
	            return 6;
	            
	        case 'g':
	            return 7;
	            
	        case 'h':
	            return 8;
	            
	        case 'i':
	            return 9;
	            
	        case 'j':
	            return 10;
	            
	        case 'k':
	            return 11;
	            
	        case 'l':
	            return 12;
	            
	        case 'm':
	            return 13;
	            
	        case 'n':
	            return 14;
	            
	        case 'o':
	            return 15;
	            
	        case 'p':
	            return 16;
	            
	        case 'q':
	            return 17;
	            
	        case 'r':
	            return 18;
	            
	        case 's':
	            return 19;
	            
	        case 't':
	            return 20;
	            
	        case 'u':
	            return 21;
	            
	        case 'v':
	            return 22;
	            
	        case 'w':
	            return 23;
	            
	        case 'x':
	            return 24;

	        case 'y':
	            return 25;

	        case 'z':
	            return 26;
	    }
		
		return 0;
	}
	
	//Modified from the textbook to work with strings. Very easy, since it"s a string. 
	public static int lexo_radixGetLength(String value)
	{
		char[] array = value.toCharArray();
		return array.length;
	}
	
	//From the textbook. 
	public static int lexo_radixGetMaxLength(String[] s)
	{
		int maxDigits = 0;
		
	    for (int i = 0; i < s.length; i++) 
	      {
	         int digitCount = lexo_radixGetLength(s[i]);
	         
	         if (digitCount > maxDigits) 
	         {
	             maxDigits = digitCount;
	         }
	      }
	      
	      return maxDigits;
	}

	//Radix sort the int arrays, and then check if equal indexes match each other. If not, or if s is larger than t, s isn't a subset. 
	public static String subint(int[] s, int[] t)
	{
		s = radixSort(s);
		t = radixSort(t);
		
		if(s.length > t.length)
		{
			return "No.";
		}
		
		boolean found = false;
		
		for(int i = 0; i < s.length; i++)
		{
			for(int j = 0; j < t.length; j++)
			{
				if(s[i] == t[j])
				{
					found = true;
				}
			}
			
			if(found == false)
			{
				return "No.";
			}
			
			else
			{
				found = false;
			}
		}
		
		return "Yes.";
	}
	
	//Everything below this is straight radix sort taken from the textbook, no alterations made, for my answer to #6. I hope my comments and 
	// alterations on #5 are enough to prove I know how this works and could replicate it on my own, but please let me know if not and I'll answer
	// any questions to verify. 
	
	public static int radixGetMaxLength(int[] numbers) {
	      int maxDigits = 0;
	      for (int i = 0; i < numbers.length; i++) {
	         int digitCount = radixGetLength(numbers[i]);
	         if (digitCount > maxDigits) {
	             maxDigits = digitCount;
	         }
	      }
	      return maxDigits;
	   }
	   
	public static int radixGetLength(int value) {
	      if (value == 0) {
	         return 1;
	      }
	      
	      int digits = 0;
	      while (value != 0) {
	         digits++;
	         value /= 10;
	      }
	      return digits;
	   }
	   
	public static int[] radixSort(int[] numbers) {
	      ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
	      for (int i = 0; i < 10; i++) {
	         buckets.add(new ArrayList<Integer>());
	      }
	      
	      int copyBackIndex = 0;
	      
	      // Find the max length, in number of digits
	      int maxDigits = radixGetMaxLength(numbers);
	      
	      int pow10 = 1;
	      for (int digitIndex = 0; digitIndex < maxDigits; digitIndex++) {
	         for (int i = 0; i < numbers.length; i++) {
	            int num = numbers[i];
	            int bucketIndex = (Math.abs(num) / pow10) % 10;
	            buckets.get(bucketIndex).add(num);
	         }
	         
	         // Copy buckets back into numbers array
	         copyBackIndex = 0;
	         for (int i = 0; i < 10; i++) {
	            ArrayList<Integer> bucket = buckets.get(i);
	            for (int j = 0; j < bucket.size(); j++) {
	               numbers[copyBackIndex] = bucket.get(j);
	               copyBackIndex++;
	            }
	            bucket.clear();
	         }
	         
	         pow10 *= 10;
	      }
	      
	      ArrayList<Integer> negatives = new ArrayList<Integer>();
	      ArrayList<Integer> nonNegatives = new ArrayList<Integer>();
	      for (int num : numbers) {
	         if (num < 0) {
	            negatives.add(num);
	         }
	         else {
	            nonNegatives.add(num);
	         }
	      }
	      
	      // Copy sorted content to array - negatives in reverse, then non-negatives
	      copyBackIndex = 0;
	      for (int i = negatives.size() - 1; i >= 0; i--) {
	         numbers[copyBackIndex] = negatives.get(i);
	         copyBackIndex++;
	      }
	      for (int i = 0; i < nonNegatives.size(); i++) {
	         numbers[copyBackIndex] = nonNegatives.get(i);
	         copyBackIndex++;
	      }
	      
	      return numbers;
	   }


}


