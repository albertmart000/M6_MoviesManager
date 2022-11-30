package com.logic.utils;

import com.logic.models.Director;
import com.logic.models.Genre;
import com.logic.models.Movie;
import com.logic.models.PEGI;
import com.logic.models.User;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MovieManager {

    public final List<Movie> movies;
    public final Map<User, List<Movie>> userMovies = new TreeMap<>();

    public MovieManager() {

        User u1 = new User("Ricardo", "1111", "ricardo@email.com");
        User u2 = new User("Albert", "2222", "albert@email.com");
        User u3 = new User("Susana", "3333", "susana@email.com");
        User u4 = new User("Maria", "4444", "maria@email.com");
        User u5 = new User("Jose", "5555", "jose@email.com");

        Director d1 = new Director("Quentin", "Tarantino", LocalDate.of(1963, Month.MARCH, 27), Locale.US);
        Director d2 = new Director("Stanley", "Kubrik", LocalDate.of(1928, Month.JULY, 26), Locale.US);
        Director d3 = new Director("Steven", "Spielberg", LocalDate.of(1946, Month.DECEMBER, 18), Locale.US);
        Director d4 = new Director("Pedro", "Almodovar", LocalDate.of(1949, Month.SEPTEMBER, 25), Locale.ITALY);
        Director d5 = new Director("David", "Fincher", LocalDate.of(1962, Month.AUGUST, 28), Locale.US);
        Director d6 = new Director("Werner", "Herzog", LocalDate.of(1942, Month.SEPTEMBER, 5), Locale.GERMANY);

        Movie m1 = new Movie("El club de la lucha", Genre.THRILLER, d5, 1999, false, PEGI.PEGI18);
        Movie m2 = new Movie("Seven", Genre.THRILLER, d5, 1995, false, PEGI.PEGI16);
        Movie m3 = new Movie("Alien 3", Genre.SCIFY, d5, 1992, false, PEGI.PEGI18);
        Movie m4 = new Movie("2001: Una odisea en el espacio", Genre.SCIFY, d2, 1968, false, PEGI.PEGI16);
        Movie m5 = new Movie("La chaqueta metálica", Genre.BELIC, d2, 1987, false, PEGI.PEGI18);
        Movie m6 = new Movie("Lolita", Genre.ROMANTIC, d2, 1962, false, PEGI.PEGI16);
        Movie m7 = new Movie("ET", Genre.SCIFY, d3, 1982, false, PEGI.PEGI12);
        Movie m8 = new Movie("Tiburón", Genre.TERROR, d3, 1975, false, PEGI.PEGI18);
        Movie m9 = new Movie("En busca del valle encantado", Genre.ANIME, d3, 1988, false, PEGI.PUBLIC);
        Movie m10 = new Movie("Todo sobe mi madre", Genre.DRAMA, d4, 1999, false, PEGI.PUBLIC);
        Movie m11 = new Movie("Hablé con ella", Genre.ROMANTIC, d4, 2000, false, PEGI.PEGI7);
        Movie m12 = new Movie("Volver", Genre.DRAMA, d4, 2005, false, PEGI.PUBLIC);
        Movie m13 = new Movie("Los odiosos 8", Genre.WESTERN, d1, 2015, false, PEGI.PEGI16);
        Movie m14 = new Movie("Pulp Fiction", Genre.THRILLER, d1, 1994, false, PEGI.PEGI16);
        Movie m15 = new Movie("Kill Bill Vol.II", Genre.WESTERN, d1, 2003, false, PEGI.PEGI18);
        Movie m16 = new Movie("Aguirre, la cólera de Dios", Genre.HISTORY, d6, 1972, false, PEGI.PEGI18);
        Movie m17 = new Movie("Stroszek", Genre.DRAMA, d6, 1977, false, PEGI.PEGI12);
        Movie m18 = new Movie("Nosferatu", Genre.TERROR, d6, 1979, false, PEGI.PEGI18);

        movies = addMovies(new ArrayList<Movie>(), m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18);

        userMovies.put(u1, addMovies(new ArrayList<Movie>(), m1, m2, m5, m7, m9, m12, m16, m18));
        userMovies.put(u2, addMovies(new ArrayList<Movie>(), m3, m4, m8, m10, m11, m13, m17, m18));
        userMovies.put(u3, addMovies(new ArrayList<Movie>(), m1, m6, m14, m15));
        userMovies.put(u4, addMovies(new ArrayList<Movie>(), m2, m4, m7, m10, m15, m17, m18));
        userMovies.put(u5, addMovies(new ArrayList<Movie>(), m1));

        //setRating(u3, m6.getTitle(), 4.5);
        //userMovies.get(u3).get(0);
        randomizeRatings();
        updateRatings();

        //System.out.println(userMovies.values());
        //System.out.println(movies.get(0).getRating());
    }

    public List<Movie> addMovies(List<Movie> moviesList, Movie... movies) {
        moviesList.addAll(Arrays.asList(movies));
        return moviesList;
    }

    public Movie findByTitle(String title) throws NoSuchElementException {
        try {
            return movies.stream()
                    .filter((Movie mov)
                            -> {
                        return mov.getTitle().toLowerCase().contains(title.trim().toLowerCase());
                    })
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            System.out.println("No hay ninguna película con ese título");
        }
        return null;
    }

    public long moviesCount() {
        long count = movies.stream()
                .count();
        System.out.println("El número de películas es: " + count);
        return count;
    }

    public void showAllMovies() {
        movies.stream()
                .forEach(movie -> System.out.println(movie));
    }

    public void showAllMovies(int limit) {
        movies.stream()
                .limit(limit)
                .forEach(movie -> System.out.println(movie));
    }

    public void showAllMovies(int limit, long skip) {
        movies.stream()
                .skip(skip)
                .limit(limit)
                .forEach(movie -> System.out.println(movie));
    }

    public void setValueRating(User user, String title, Double Rating) {
        List<Movie> mv = userMovies.get(user).stream()
                .peek(m -> {
                    if (m.getTitle().equals(title)) {
                        m.setRating(Rating);
                    }
                })
                .collect(Collectors.toList());
        userMovies.replace(user, mv);
        //updateRatings();

    }

    public void randomizeRatings() {
        userMovies.forEach((k, v) -> v.stream()
                .forEach(m -> setValueRating(k, m.getTitle(), ((Math.random() * (10 - 3)) + 3))));
    }

    public Double averageRating() {
        return movies.stream()
                .mapToDouble(m -> m.getRating())// entra m movie y sale d double
                .filter(d -> d > 0)
                .average()
                .getAsDouble();
    }

    public Double averageRating(Collection<Movie> cm) {
        return cm.stream()
                .filter(m -> m.getRating() > 0)
                .mapToDouble(m -> m.getRating())// entra m movie y sale d double             
                .average()
                .getAsDouble();
    }

    public List<Movie> flatMapToList() {
        // aplanamos: convertimos varias listas en una sola
        return userMovies.values().stream()
                .flatMap(c -> c.stream().map(m -> m))//entra c Lista de Movie y sale m Stream de movie
                .toList();
    }

    public Map<String, List<Movie>> groupByMovies(List<Movie> ml) {
        return ml.stream()
                .collect(Collectors.groupingBy(m -> m.getTitle()));
    }

    public Double getAverage(String title, Map<String, List<Movie>> ml) {
        return ml.get(title).stream().mapToDouble(m -> m.getRating())
                .average()
                .orElse(0.0);
    }

    public void updateRatings() {
        Map<String, List<Movie>> data = groupByMovies(flatMapToList());
        movies.stream().forEach(m -> m.setRating(getAverage(m.getTitle(), data)));
    }

    public Map<Boolean, List<Movie>> approvedMovie() {
        return movies.stream()
                .collect(Collectors.partitioningBy(m -> m.getRating() >= 5));
    }

    public List<Movie> showApprovedMovies(boolean op) {
        System.out.println((op) ? "Películas aprobadas: " : "Películas suspendidas");

        //approvedMovie().get(op).forEach(System.out::println);//equivale a (m-> System.out.println
        List<Movie> ml = approvedMovie().get(op);
        ml.forEach(m -> System.out.println("TITULO: " + m.getTitle() + "; RATING: "
                + String.format("%.2f", m.getRating())));
        return ml;
    }

    public List<Movie> sortMoviesByRating(List<Movie> ml, boolean op) {
        //ml.sort((m1, m2) -> (int)m1.getRating().doubleValue() - (int)m2.getRating().doubleValue());
        //ml.sort((m1, m2) -> (int) (m1.getRating() * 100 - m2.getRating() * 100));//multiplicamos por 100 para poder utilizar los decimales del double.

        Comparator<Movie> compare = (op) ? (m1, m2) -> (int) (m1.getRating() * 100 - m2.getRating() * 100)
                : (m1, m2) -> (int) (m2.getRating() * 100 - m1.getRating() * 100);
        ml.sort(compare);
        return ml;
    }
    
    public void topByGenre(int num, int limit){
        if ( num >= 0 && num <= 10){
            movies.stream()
                    .filter(m -> m.getGenre() == Genre.values()[num])
                    .sorted(Comparator.comparing((Movie m) -> m.getRating() * 100).reversed().thenComparing((Movie a, Movie b)-> a.getYear() - b.getYear()))
                    .limit(limit)
                    .forEach(m -> System.out.println("TITULO: " + m.getTitle() + "; RATING: "
                + String.format("%.2f", m.getRating()) + "; AÑO: " + m.getYear()));
        }
        
    }

}
