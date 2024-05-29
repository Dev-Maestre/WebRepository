package com.example.dsystem.System;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TokenManager {
    // TODO - FIX TOKEN MANAGER
    private static final String FILE_PATH = "src/main/java/com/example/DSystem/System/tokens.txt";

    public static void saveToken(String token) {
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            writer.write(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getToken() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))){
            if (scanner.hasNext())
                return scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean isTokenAdmin(String token) {
        return token.equals("admin");
    }
}
