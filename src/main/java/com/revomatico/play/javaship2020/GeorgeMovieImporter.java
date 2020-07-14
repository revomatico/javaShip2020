package com.revomatico.play.javaship2020;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeorgeMovieImporter implements MovieImporter {
  @Override
  public List<Movie> importMovies(String path) {
    List<Movie> movies = new ArrayList<>();

    try {
      Reader reader = Files.newBufferedReader(Paths.get(path));
      CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
          .withFirstRecordAsHeader()
          .withIgnoreHeaderCase()
          .withTrim());

      for (CSVRecord csvRecord : csvParser) {
        String title = csvRecord.get("Title");
        Date releaseDate = new SimpleDateFormat("yyyy-MM-dd")
            .parse(csvRecord.get("Release Date"));

        movies.add(new Movie(title, releaseDate));
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    return movies;
  }
}
