package project;

import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
		System.out.println("Start");
		DesignMaker design = new DesignMaker();
		System.out.println("desin: " + design);
		design.make();
	}
}
