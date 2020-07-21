package com.revomatico.play.javaship2020.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;

public class LauraMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> movies = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy");
    try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {

      csvReader.readLine(); //read the first line containing the name of columns
      String row;
      while ((row = csvReader.readLine()) != null) {
        String[] columns = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //split the data
        String movieTitle = columns[5].replaceAll("^[\"']+|[\"']+$", ""); //remove possible "(movie title)"
        Date productionYear = format.parse(columns[10]);
        movies.add(new Movie(movieTitle, productionYear));
      }
    } catch (IOException | ParseException e) {
      throw new RuntimeException("When importing movies from csv", e);
    }

    return movies;
  }
}
