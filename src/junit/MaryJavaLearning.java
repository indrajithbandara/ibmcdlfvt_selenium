package junit;


public class MaryJavaLearning {
	
	public static String a = "Hello world";
	
	enum theJoker{
		CLUBS,  DIAMONDS, HEARTS, SPADES;
		
		public static int getsize(){
			return 4;
		}
		
	}
	
	public static int digui(int n){
		
		if (n < 0)
			//exception
			return -1;
		if (n == 1)
			return 1;
		else
			return (n*digui(n-1));
		
	}//static
	
	public static void main(String[] arg){
		

		/********
		System.out.println(a);
		
		int i = 1, j = 100, sum = 0;
		for(; i<=50; i++, j--)
		{
			sum += i + j;
		}
		
		System.out.println(sum);
		*******/
		
		System.out.println(digui(6));

		
		
		
	}//main
	

}//class MaryJaveLearning
