import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Lab2 mark = new Lab2("input.txt");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input w0: ");
		String w0 = scanner.next();

		PrintStream writer = null;
		String outputFileName = "";
		try {
			if(outputFileName.isEmpty())
				writer = System.out;
			else
				writer = new PrintStream(new File(outputFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		writer.println("Possible w1:");
		writer.println(mark.solve(w0));
	}
}
