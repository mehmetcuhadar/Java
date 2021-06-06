import java.util.Random;
import java.util.Arrays;

public class HW1 {
	
	// This is the method that returns the count of 'a' chars in the matrix
	// Feel free to add a helper method for the recursive part of the algorithm
	public static int acount(char[][] mat) {
		int result = 0;
		// Your code goes here 
		for(int j=0;j<mat.length;j++){
			result+=rowCounter(mat[j], 0, mat.length);
		}
		return result;
	}
	
	public static int rowCounter(char[] row,int lowest, int highest){
		int index= (lowest+highest)/2;
		if(highest<lowest){
			return 0;
		}
		if(isLetterA(Character.toString(row[index]))){
			if(row.length>index){
				if(isLetterA(Character.toString(row[index+1]))){
					return rowCounter(row, index+1, highest);
				}
			}			
			return index+1;
		}

		return rowCounter(row, lowest, index-1);
	}


	public static boolean isLetterA( String letter){
		if(letter.equals("a")){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// This method creates a test case for you
		int n = 5;
		Random rand = new Random();
		char[][] input = new char[n][n];
		for (int i = 0; i < n; i++) {
			int a_num = rand.nextInt(n);
			for (int j = 0; j < a_num; j++) {
				input[i][j] = 'a';
			}
			for (int j = a_num; j < n; j++) {
				input[i][j] = 'b';
			}
		}
		// Here you can see the matrix row by row 
		System.out.println(Arrays.deepToString(input));
		// Here you can see the result of your function
		System.out.println(acount(input));
	}

}
