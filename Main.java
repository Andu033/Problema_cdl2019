

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		String[][] texts = new String[args.length][];
		Scanner s;
		for (int i = 0; i < args.length; i++) {
			try (Stream<String> stream = Files.lines(Paths.get(args[i]))) {
				
				texts[i] = stream.collect(Collectors.joining(" ")).replaceAll("\\s+", " ").split("[ ']");
				
				for (int j = 0; j < texts[i].length; j++) {
					texts[i][j] = texts[i][j].replaceAll("[^a-zA-Z-]", "");
					texts[i][j] = texts[i][j].toLowerCase();
					
				}
			}
		}
		HashMap<String, String> res;
		s = new Scanner(System.in);
		String s1 = s.nextLine();
		while (!s1.equals("/exit")) {
			res = new HashMap<>();
			for (int i = 0; i < args.length; i++) {
				HashSet<String> set = new HashSet<>();
				for (String cuv : texts[i]) {
					if (cuv.contains("-")) {
						for (String part : cuv.split("-")) {
							if (part.startsWith(s1)) {
								set.add(cuv);
								break;
							}
						}
					}
					if (cuv.startsWith(s1)) set.add(cuv);
				}
				List<String> list = new LinkedList<>(set);
				Collections.sort(list);
				for (int k = 0; k < Math.min(list.size(), 5); k++)
					if (res.containsKey(list.get(k))) {
						res.put(list.get(k), res.get(list.get(k)) + (1 + i) + " ");
					} else res.put(list.get(k), list.get(k) + " : " + (1 + i) + " ");
				
			}
			List<String> aux123 = new LinkedList<>(res.values());
			Collections.sort(aux123);
			
			for (int i = 0; i < Math.min(aux123.size(), 5); i++) {
				String str = aux123.get(i);
				System.out.println(str);
			}
			if (res.size() != 0) ;
			else System.out.println("No suggestions...");
			System.out.print("\n");
			s1 = s.nextLine();
		}
		
	}
}
