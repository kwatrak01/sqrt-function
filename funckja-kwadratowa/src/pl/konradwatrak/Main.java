package pl.konradwatrak;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import pl.konradwatrak.languages.English;
import pl.konradwatrak.languages.Polish;

public class Main {
	
	public static void main(String[] arguments) {
		new Main();
	}
	
	public Main() {
		run();
	}
	
	private Language lang;
	
	private void run() {
		System.out.println("Please select prefered language:");
		System.out.println("1. Polish");
		System.out.println("2. English");
		
		Scanner scanner = new Scanner(System.in);
		int select = scanner.nextInt();
		switch (select) {
			case 1:
				lang = new Polish();
				break;
			case 2:
				lang = new English();
				break;
			default:
				break;
		}
		scanner.close();
		
		try {
			lang.init();
			HashMap<String, String> map = lang.load();
//			map.put("INVALID", "Nieprawidlowa operacja!");
//			map.put("NO_ZEROS", "Brak miejsc zerowych!");
//			lang.save(map);
			System.out.println("Loaded:");
			map.forEach((k, v) -> {
				System.out.println(k + " -> " + v);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Done!");
//		calc(getNumbers());
		
	}
	
	private void calc(float[] n) {
		long time = System.currentTimeMillis();
		
		if (n.length == 0)
			return;
		
		float delta = (float)Math.pow(n[1], 2) - 4*(n[0]*n[2]);
		
		if (delta > 0) {
			float x1 = (-n[1] - (float)Math.sqrt(delta)) / 2 * n[0];
			float x2 = (-n[1] + (float)Math.sqrt(delta)) / 2 * n[0];
			
			System.out.println("Miejsca zerowe x1=" + x1 + " x2=" + x2);
		} else if (delta < 0) {
			System.out.println("Brak miejsc zerowych");
		} else {
			float x0 = -n[1]/2*n[0];
			
			System.out.println("Miejsce zerowe x0=" + x0);
		}
		
		System.out.println("Policzenie zajelo Ci " + ((System.currentTimeMillis() - time) / 1000) + "s");
	}
	
	private float[] getNumbers() {
		Scanner scanner = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
		
		System.out.println("A,B,C: ");
		builder.append(scanner.next());
		
		List<String> args = Arrays.asList(builder.toString().split(","));
		if (args.size() < 3) {
			System.err.println("Invalid input");
			return new float[0];
		}
		
		float[] numbers = new float[args.size()];
		
		Iterator<String> iterator = args.iterator();
		int index = 0;
		while(iterator.hasNext()) {
			try {
				numbers[index] = Float.parseFloat(iterator.next());
			} catch (NullPointerException | NumberFormatException e) {
				e.printStackTrace();
			} finally {
				index++;
			}
		}
		
		return numbers;
	}
}
