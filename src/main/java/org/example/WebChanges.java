package org.example;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.stream.Collectors;

public class WebChanges {
    public static String generateEmailText(Hashtable<String, String> yesterdayData, Hashtable<String, String> todayData){

        Set<String> disappearedPages = searchDisappearedPages(yesterdayData, todayData);
        Set<String> createdPages = searchNewPages(yesterdayData, todayData);
        Set<String> changedPages = searchChangedPages(yesterdayData, todayData);

        StringBuilder sb = new StringBuilder("Здравствуйте, дорогая и.о. секретаря\n\n");
        sb.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");

        if (!disappearedPages.isEmpty()) {
            sb.append("Исчезли следующие страницы:\n");
            for (String page : disappearedPages) {
                sb.append(page).append("\n");
            }
        }

        if (!createdPages.isEmpty()) {
            sb.append("Появились следующие новые страницы:\n");
            for (String page : createdPages) {
                sb.append(page).append("\n");
            }
        }

        if (!changedPages.isEmpty()) {
            sb.append("Изменились следующие страницы:\n");
            for (String page : changedPages) {
                sb.append(page).append("\n");
            }
            sb.append("\n");
        }

        sb.append("С уважением,\n");
        sb.append("автоматизированная система\n");
        sb.append("мониторинга.\n");

        return sb.toString();
    }

    private static Set<String> searchDisappearedPages(Hashtable<String, String> yesterdayData, Hashtable<String, String> todayData){
        return yesterdayData.keySet().stream()
                .filter(key -> !todayData.containsKey(key))
                .collect(Collectors.toCollection(HashSet::new));
    }

    private static Set<String> searchNewPages(Hashtable<String, String> yesterdayData, Hashtable<String, String> todayData){
        return todayData.keySet().stream()
                .filter(key -> !yesterdayData.containsKey(key))
                .collect(Collectors.toCollection(HashSet::new));
    }

    private static Set<String> searchChangedPages(Hashtable<String, String> yesterdayData, Hashtable<String, String> todayData){
        return yesterdayData.keySet().stream()
                .filter(key -> todayData.containsKey(key) && !todayData.get(key).equals(yesterdayData.get(key)))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
