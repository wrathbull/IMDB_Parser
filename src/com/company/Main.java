package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;

public class Main {

    final static String BASE_URL = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";

    public static void main(String[] args) {

        try (FileWriter fileWriter = new FileWriter(new File("src\\resources\\movies.txt"))) {
            String top_element;
            Document document = Jsoup.connect(BASE_URL).get();
            Elements rating = document.select("td.ratingColumn.imdbRating strong");
            Elements movies = document.select("td.titleColumn a");
            for (int i = 0; i < 50; i++) {
                top_element = (i + 1) + ". " + movies.select("a").get(i).text() + " - "
                        + rating.select("strong").get(i).text() + "\n";
                fileWriter.write(top_element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}