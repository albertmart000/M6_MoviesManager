package com.logic.app;

import com.logic.utils.MovieManager;

public class AppMain {

    public static void main(String[] args) {

        MovieManager mm = new MovieManager();

//        System.out.println(mm.findByTitle("HHHH"));
//        mm.moviesCount();
//        mm.showAllMovies(2);        
//        mm.showAllMovies(2,3);
//        mm.userMovies.forEach((k, v) -> v.forEach(m -> System.out.println(m.getRating())));
        System.out.println("-".repeat(75));

//        mm.sortMoviesByRating(mm.approvedMovie().get(true), false).forEach(m -> System.out.println("TITULO: " + m.getTitle() + "; RATING: "
//                + String.format("%.2f", m.getRating())));

        mm.topByGenre(0, 5);
        System.out.println("-".repeat(75));

    }
}
