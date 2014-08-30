import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class KWIC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		List<String> filters = new ArrayList<String>();//Arrays.asList("is", "the", "of", "and", "as", "a", "after");
		List<String> outputs = new ArrayList<String>();
		System.out.println("Enter title:");
		outputs = getLines();
		System.out.println("Enter filter word:");
		filters = getLines();

		long start = System.nanoTime();
		outputs = rotateString(outputs);
		outputs = filterFirstWord(outputs, filters);
		outputs = upperCaseKey(outputs, filters);
		outputs = sort(outputs);
		display(outputs);
		long stop = System.nanoTime();
		System.out.println("Time: "+(stop-start)/1000000000.0+"s");
	}

	private static List<String> getLines()
	{
		Scanner reader = new Scanner(System.in);
		List<String> lines = new ArrayList<String>();
		String line = "";
		while(true){
			line = reader.nextLine();
			if(!line.equals(""))
			{
				lines.add(line);
			}
			else
			{
				break;
			}
		}
//		reader.close();
		return lines;
	}

	private static List<String> rotateString(List<String> inputs)
	{
		List<String> outputs = new ArrayList<String>();
		for(String input: inputs)
		{
			outputs.add(input);
			String[] splitInput = input.split(" ");
			String temp = input;
			for(int i = 1; i<splitInput.length;i++)
			{
				temp = (temp.substring(temp.indexOf(" ")+1, temp.length()))+ " " +temp.substring(0, input.indexOf(" "));
				System.out.println(temp);
				outputs.add(temp);
			}
		}
		return outputs;
	}
	
	private static List<String> filterFirstWord(List<String >inputs, List<String> filters)
	{
		for(int i = 0; i<inputs.size();i++)
		{
			for(String filter: filters)
			{
				if(inputs.get(i).indexOf(" ") == filter.length() && filter.equalsIgnoreCase(inputs.get(i).substring(0, filter.length())))
				{
					inputs.remove(i);
					continue;
				}
			}
		}
		return inputs;
	}
	
	private static List<String> upperCaseKey (List<String> inputs, List<String> filters)
	{
		List<String> outputs = new ArrayList<String>();
		for(String input: inputs)
		{
			String temp = "";
			String[] splitInputs = input.split(" ");
			for(String splitInput: splitInputs)
			{
				boolean exist = false;
				for(String filter: filters)
				{
					if(splitInput.equalsIgnoreCase(filter))
					{
						exist = true;
						continue;
					}

				}
				if(!exist && splitInput.charAt(0)<='z' && splitInput.charAt(0)>='a')
				{
					splitInput = (char)(splitInput.charAt(0)-32)+splitInput.substring(1, splitInput.length());
				}
				
				if(exist && splitInput.charAt(0)<='Z' && splitInput.charAt(0)>='A')
				{
					splitInput = (char)(splitInput.charAt(0)+32)+splitInput.substring(1, splitInput.length());					
				}
				temp += splitInput + " ";
			}
			outputs.add(temp.substring(0, temp.length()-1));
		}
		return outputs;		
	}
	private static List<String> sort(List<String> inputs)
	{
		 Comparator<String> cmp = new Comparator<String>(){
			 public int compare(String s1, String s2){
				 int s1char = s1.charAt(0), s2char = s2.charAt(0);
				 				 
				 if(s1.charAt(0)<='z' && s1.charAt(0)>='a')
					 s1char = s1.charAt(0) - 32;

				 if(s2.charAt(0)<='z' && s2.charAt(0)>='a')
					 s2char = s2.charAt(0) - 32;

				 if(s1char!=s2char)
				 {
					 return s1char-s2char;
				 }
				 else
				 {
					 //s1 only had last character to check
					 if(s1.length()==1)
					 {
						 if(s2.length()>1)
							 return -1;
					 }
					 //s2 only had last character to check					 
					 if(s2.length()==1)
					 {
						 //All characters are the same
						 if(s1.equalsIgnoreCase(s2))
							 return 0;
						 else
							 return 1;
					 }
					 return compare(s1.substring(1, s1.length()), s2.substring(1, s2.length()));
				 }
			 }};
			 inputs.sort(cmp);
		return inputs;
	}
	private static void display(List<String> inputs)
	{
		System.out.println("Output:");
		for(String input: inputs)
		{
			boolean moreThanOneWord = input.contains(" ");
			System.out.println("["+input.substring(0, (moreThanOneWord? input.indexOf(" "):input.length()))+"]"+(moreThanOneWord? input.substring(input.indexOf(" "), input.length()): ""));
		}
	}
	
}
