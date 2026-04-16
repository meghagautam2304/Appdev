import java.util.*;
import java.io.*;

public class Main {
public static void main(String args[]) {
	int n;
	System.out.println("Enter a number: ");
	Scanner sc=new Scanner(System.in);
	n=sc.nextInt();
	if(n%2==0) {
		System.out.println(n+" is even number");
	}
	else {
		System.out.println(n+" is odd number");
	}
}
}
