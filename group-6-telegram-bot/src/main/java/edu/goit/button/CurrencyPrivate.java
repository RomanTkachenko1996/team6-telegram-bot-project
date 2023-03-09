package edu.goit.button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyPrivate {
    public static final Gson gs = new GsonBuilder().create();

    public static String getCurrency()  {
        try {
           String text = Jsoup.connect("https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11")
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
            ArrayList<Currency> list = gs.fromJson(text,TypeToken.getParameterized(List.class, Currency.class).getType());
            return  convert(list);
        } catch (IOException e) {
            System.out.println("Error parse!");
        }
        return null;
    }

    public static  String convert(ArrayList<Currency> list)  {
        return list.stream()
                .map(c-> "cc: " + c.getCcy()+ " buy: " + c.getBuy().setScale(2, RoundingMode.FLOOR)
                        + " sale: " + c.getSale().setScale(2, RoundingMode.FLOOR))
                .collect(Collectors.joining("\n","Курс ПриватБанкну:\n","."));
    }
}