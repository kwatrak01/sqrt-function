package pl.konradwatrak;

import java.io.IOException;
import java.util.Arrays;
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
	private Scanner scanner;
	
	private void run() {
		scanner = new Scanner(System.in);
		System.out.println("Please select prefered language:");
		System.out.println("1. Polish");
		System.out.println("2. English");
		
		Integer select = Integer.parseInt(scanner.nextLine());
		switch (select) {
			case 1:
				lang = new Polish();
				break;
			case 2:
				lang = new English();
				break;
			default:
				lang = new English();
				break;
		}
		
		try {
			lang.init();
			lang.saveFromTemplate();
			lang.initDictionary(lang.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		calc(getNumbers());
		
	}
	
	private void calc(float[] n) {
		long time = System.currentTimeMillis();
		
		if (n.length == 0)
			return;
		
		float delta = (float)Math.pow(n[1], 2) - 4*(n[0]*n[2]);
		
		if (delta > 0) {
			float x1 = (-n[1] - (float)Math.sqrt(delta)) / 2 * n[0];
			float x2 = (-n[1] + (float)Math.sqrt(delta)) / 2 * n[0];
			
			System.out.println(lang.t("ZERO_PLACES_FULL").replace("%x1%", x1 + "").replace("%x2%", x2 + ""));
		} else if (delta < 0) {
			System.out.println(lang.t("ZERO_PLACES_NONE"));
		} else {
			float x0 = -n[1]/2*n[0];
			
			System.out.println(lang.t("ZERO_PLACES_PART").replace("%x0%", x0 + ""));
		}
		
		scanner.close();
		System.out.println(lang.t("COUNTER").replace("%time%", ((System.currentTimeMillis() - time) / 1000) + "s"));
	}
	
	private float[] getNumbers() {
		StringBuilder builder = new StringBuilder();
		
		System.out.println("A,B,C: ");
		builder.append(scanner.nextLine());
		
		List<String> args = Arrays.asList(builder.toString().split(","));
		if (args.size() < 3) {
			System.err.println(lang.t("INVALID_INPUT"));
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
