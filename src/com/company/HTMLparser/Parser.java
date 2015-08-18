package com.company.HTMLparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by compaurum on 24.06.2015.
 */

public class Parser {
    public static void main (String [] args) throws IOException {

        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0")
                .data("query", "Java")
                .userAgent("Mo")
                .cookie("auth", "token")
                .timeout(1000)
                .post();

        Element body = doc.getElementById("body");
        Elements links = doc.getElementsByTag("a");
        int i = 0;
        for (Element link : links){
            String linkHref = link.attr("href");
            String linkText = link.text();

            System.out.println(++i +" " + linkHref + " " + linkText);
        }


        //Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
    }
}
