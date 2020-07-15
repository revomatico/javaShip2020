package com.revomatico.play.javaship2020;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import com.revomatico.play.javaship2020.Movie;

public class VladMovieImporter implements MovieImporter {

    public List<Movie> importMovies(String path) {
        PopcornApp app = new PopcornApp();

        try {
            Scanner buff = new Scanner(new File(path));
            String entire_line = buff.nextLine();// we have column name on the first row
            entire_line = buff.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); // Date format used in date parsing

            while (buff.hasNextLine()) {

                String[] arrSplit = entire_line.split(",");
                // we have the entire line read in a String, we need to parse it
                String string_year = arrSplit[arrSplit.length - 2];
                try {
                    Date year = format.parse(string_year);
                    String name = arrSplit[5];
                    app.addMovie(new Movie(name, year));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                entire_line = buff.nextLine();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return app.listMovies();
    }

    /*
     * public void sortlist(PopcornApp ap) { List<Movie> movies=ap.listMovies();
     * Collection.sort(movies); app.print_Movies(app.listMovies()); }
     */
}