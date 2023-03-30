package logicalPrograming;

public class swipingNumbr {
	public static void main(String[] args) {
		
		int a=10;
		int b=20;
		System.out.println("before swapping"+ a+" "+b);
		
		
		//logic 1
		
		int t=a;
	 a=b;
	 b=t; 
	 System.out.println("after swaping1 "+ a+" "+b);
	 
	 
	 //logic 2
	 
	 a=a+b;
	 a=a-b;
	 b=a-b;
	 
	 System.out.println("after swaping2 "+ a+" "+b);
	 
	 //logic 4
	 a=a*b;
	 b=a/b;
	 a=a/b;
	 System.out.println("after swaping3 "+ a+" "+b);
	
	 
	 //logic 5
	 
	 
	 
	 
	}

}
