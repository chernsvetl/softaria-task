package org.example;

import java.util.*;

import static org.example.WebChanges.generateEmailText;

public class Main {
    public static void main(String[] args) {

        Hashtable<String, String> yesterdayData = new Hashtable<>();
        Hashtable<String, String> todayData = new Hashtable<>();

        generateEmailText(yesterdayData, todayData);
    }
}