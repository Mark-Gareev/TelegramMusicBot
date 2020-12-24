package com.company;

import java.util.Scanner;

public class Main {
    private static Bot bot = new Bot();
    public static void main(String[] args) {
	// write your code here
        System.out.println("INFO:server bot application was started. Write \"exit\" to end");
        Scanner scan = new Scanner(System.in);
            bot.start();
            while (true) {
                if (scan.next().equals("exit")) {
                    bot.stop();
                    System.out.println("Goodbye! Bot was finished his work");
                    break;
                }
            }


    }
}
