import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class KWIC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub 

		String filters[] = {"is", "the", "of", "and", "as", "a", "after"};
		List<String> outputs = new ArrayList<String>();
		outputs = getLines();
		outputs = rotateString(outputs);
		outputs = filterFirstWord(outputs, filters);
		outputs = sort(outputs);
		display(outputs);
	}

	private static List<String> getLines()
	{
		int i = 0;
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
		reader.close();
		return lines;
	}

	private static List<String> rotateString(List<String> inputs)
	{
		List<String> outputs = new ArrayList<String>();
		for(String input: inputs)
		{
			outputs.add(input);
			String[] splitInput = input.split(" ");
			for(int i = 1; i<splitInput.length;i++)
			{
				String temp = splitInput[i%(splitInput.length)];
				int j = i;
				while(j%(splitInput.length)+1 != i)
				{
					j++;
					temp += " "+splitInput[j%(splitInput.length)];
				}
				outputs.add(temp);
			}
		}
		return outputs;
	}
	
	private static List<String> filterFirstWord(List<String >inputs, String[] filters)
	{
		List<String> outputs = new ArrayList<String>();
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
	private static List<String> sort(List<String> inputs)
	{
		 Comparator<String> cmp = new Comparator<String>(){
			 public int compare(String s1, String s2){return s1.charAt(0)-s2.charAt(0);
			 }};
			 inputs.sort(cmp);
		return inputs;
	}
	private static void display(List<String> inputs)
	{
		for(String input: inputs)
		{
			System.out.println(input);
		}
	}
	
}
