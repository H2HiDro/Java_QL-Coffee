package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class CharacterStreamIOBuffered {

	public void writeToFile(String data, String path) throws IOException {
		try {
            File newTextFile = new File(path);
            FileWriter fw = new FileWriter(newTextFile);
            fw.write(data);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
	}

	public void readFromFile(String fileName) throws IOException {
		Reader in = null;
		Scanner scanner = null;
		try {
			
			in = new FileReader(fileName);
			scanner = new Scanner(in);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
		}finally {
			in.close();
			scanner.close();
		}
	}
}