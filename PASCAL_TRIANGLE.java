import java.util.*;
public class PASCAL_TRIANGLE {
	public static List<List<Integer>> generatePascalTriangle (int numRows) {
		List<List<Integer>> pascalTriangle = new ArrayList<>();
		for (int i = 0; i < numRows; ++i) {
		List<Integer> currRow = new ArrayList<>();
		for (int j = 0; j <= i ; ++j){
		currRow.add((0<j&&j<i)?pascalTriangle.get(i-1).get(j-1)+pascalTriangle.get(i-1).get(j):1);
		}
		pascalTriangle.add(currRow);
		}
		return pascalTriangle ;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a non negative integer:-");
		int n=sc.nextInt();
		List<List<Integer>> N=generatePascalTriangle(n);
		if(n>0) {
			System.out.println("The first "+n+" rows of Pascal's triangle:-");
			for(int i=0;i<n;i++) {
				for(int s=1;s<n-i;s++) {
					System.out.print(" ");
				}
			System.out.println(N.get(i));
		}
			}
		else {
			System.out.println("Plz check the number.");
		}
	}

}
