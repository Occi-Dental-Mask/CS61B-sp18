public class Fib
{
	public static int fib(int n){
		int i = 0,pre = 0,post = 1;
		while (i <= n){
			if (i == n) return post;
			else{
				int temp = pre;
				pre = post;
				post = temp + post;
			}
			i += 1;
		}
	}
	public static void main(Sring [] args){
		System.out.println(fib(9));
	}
}