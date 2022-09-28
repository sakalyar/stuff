class GFG{
// Function that prints
// the required sequence
	static void split(int x, int n)
	{
	  
	// If we cannot split the
	// number into exactly 'N' parts
	if(x < n)
	System.out.print("-1 ");
	  
			  
	  
		// If x % n == 0 then the minimum
		// difference is 0 and all
		// numbers are x / n
		else if (x % n == 0)
		{
			for(int i=0;i<n;i++)
			System.out.print((x/n)+" ");
		}
		else
		{
	  
			// upto n-(x % n) the values
			// will be x / n
			// after that the values
			// will be x / n + 1
			int zp = n - (x % n);
			int pp = x/n;
			for(int i=0;i<n;i++)
			{
	  
				if(i>= zp)
				System.out.print((pp + 1)+" ");
				else
				System.out.print(pp+" ");
			}
		}
	}
		  
	// Driver code
	public static void main(String[] args)
	{
			  
	int x = 5;
	int n = 3;
	split(x, n);
	  
	}
}
