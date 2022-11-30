package com.logic.ejemplos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings(value="unused")
public class EjemploProgramacionFuncional {

     public static void main(String[] args) throws Exception {
        String name = "Alvaro";
        var epf = new EjemploProgramacionFuncional();

        // INTERFACES FUNCIONALES BÁSICAS
        Function<String, Integer> apply = ((String text) -> text.length()); // s -> s.length
        Predicate<String> test = ((var text) -> text.startsWith("A") || text.length() >= 5); // Recibe un dato y devuelve boolean 
        Consumer<String> accept = text -> System.out.println("Nombre: " + text);
        Supplier<String> get = () -> {
            return "Bienvenido " + name;
        };

        //Versiones con doble dato de entrada (No disponible en Supplier, no tiene entrada)
        BiFunction<String, Integer, Character> biApply = (String text, Integer index) -> {
            if (index < text.length() && index >=0 ) {
                return text.charAt(index);
            } else {
                return null;
            }
        };
        BiPredicate<String, Integer> biTest = (var s, var i) -> !(s.length() > i);
        BiPredicate<String, Integer> biTest2 = new EjemploProgramacionFuncional()::lengthDistinctNumber;
        //<nombre del objeto o instancia> <::> <nombre del metodo - sin parentesis>
        BiConsumer<String, String> biAccept = epf::miPrint; // Si el método no es estático lo utilizamos a traves de la instancia
        //<nombre de la clase> <::> <nombre del metodo - sin parentesis>
        BiConsumer<String, String> biAccept2 = EjemploProgramacionFuncional::miPrint2; // Si el método es estático lo utilizamos con el nombre de la clase

        //Versiones de métodos con datos primitivos (Solo tres ejemplos)
        IntFunction intApply = number -> number++; // dato primitivo int
        Function<Integer, Integer> intFun = number -> number++; // Integer (Clase envoltorio)

        DoublePredicate doubleTest = decimal -> decimal >= 15.5;
        LongSupplier longGet = () -> 1_500_000L;

        ToIntFunction<Boolean> toIntFunction = (a) -> (a) ? 2 : 1;

        // TODO: Interfaces para conversion de clase -> dato primitivo (Ejemplo: ToIntFunction<?>)
        // Hay unas evoluciones de la interfaz Function
        UnaryOperator<String> unaryApply = s -> s.substring(0, 10);
        //Sustituye a:
        Function<String, String> unaryApply2 = s -> s.substring(0, 10);
        BinaryOperator<String> binaryApply = (frase1, frase2) -> frase1.concat(frase2);
        //Sustituye a:
        BiFunction<String, String, String> binaryApply2 = (frase1, frase2) -> frase1.concat(frase2);
        //TODO: 
        // Comparator 
        // Interfaz -> default, static, private 
        Comparator<String> compare = (a, b) -> b.charAt(0) - a.charAt(0);

        OptionalInt totalLetters = Stream.of("Paco", "Eddie", "Rosa", "Laura", "Ana", "Alvaro", "Diego")
                .filter(test)
                .sorted(compare)
                .peek(accept)
                .map(apply)
                .mapToInt(n -> n)
                .reduce((a, b) -> a + b);
        System.out.println(totalLetters);

        List<Character> ratings = Arrays.asList('U', 'R', 'A');

        ratings.stream()
                .filter(x -> x == 'A') //1     
                .peek(x -> System.out.println("Old Rating " + x)) //2         
                .map(x -> x == 'A' ? 'R' : x) //3         
                .forEach(x -> System.out.println("New Rating " + x)); //4
        new TicketTaker().performJob();

        List<User> users = new ArrayList<>(List.of(
                new User("Pepe", "Perez", 1.86), //1 // 2
                new User("Rodrigo", "Romero", 1.76), // 0 // 1
                new User("Gumersindo", "Gomez", 1.98),//3
                new User("Urraca", "López", 1.58),//2 // 1 // 0
                new User("Felipe", "Garcia", 1.98)//2 // 1 // 0
        ));

        users.sort((User u1, User u2) -> (int)(u1.getHeight()*100) - (int)(u2.getHeight()*100));
//        users.sort((User u1, User u2) -> (int)u1.getHeight()*100 - u2.getHeight()*100);
        users.forEach(System.out::println);
        // (u1)186.00 - (u2)176.00 = +10.00 -> +10 -> u1 se desplaza a la izquierda 
        // (u1)186.00 - (u2)198.00 = -12.00 -> -12 -> dejo los datos en su lugar
        // (u1)198.00 - (u2)158.00 = +30.00 -> +30 -> u1 se desplaza a la izquierda 
//        users.sort((User u1, User u2) -> (int)(u2.getHeight()*100 - u1.getHeight()*100));
//        users.forEach(System.out::println);
//        users.sort((User u1, User u2) -> u1.getPassword().length()-u2.getPassword().length());
//        System.out.println("-".repeat(40));
//        users.forEach(System.out::println);
//        List<Integer> numbers = new ArrayList<>(List.of(1,56,78,12));
//        System.out.println("-".repeat(40));
//        numbers.sort(Comparator.naturalOrder());
//        numbers.forEach(System.out::println);
//        System.out.println("-".repeat(40));
//        numbers.sort(Comparator.reverseOrder());
//        numbers.forEach(System.out::println);
          Stream<String> nombres = Stream.of("Mario","Ana","David","Belén");
          boolean resultado = nombres
                  .peek(System.out::println)
                  .anyMatch(nombre -> nombre.endsWith("d"));
          System.out.println(resultado);
        
    }

    // (var s, var i) -> !(s.length() > i)
    public boolean lengthDistinctNumber(String s, int n) {
        return !(s.length() > n);
    }

    public String m1(String t) {
        return t.concat(t).toUpperCase();
    }

    interface Doer {

        String doIt(int x, StringBuilder y);

    }

    public StringBuilder stringBuilderConvert(Function<? super CharSequence, ? super CharSequence> apply, String text) {
        return new StringBuilder((int) apply.apply(text));
    }

    public StringBuilder stringBuilderConvert(UnaryOperator<? super CharSequence> apply, String text) {
        return new StringBuilder((int) apply.apply(text));
    }

    public String repeatString(int n, String text) {
        return text.repeat(n);
    }

    public void miPrint(String text1, String text2) {

        System.out.printf("Nombre: %s Apellido %s",
                text1,
                text2);
    }

    public static void miPrint2(String text1, String text2) {
        System.out.printf("Nombre: %s Apellido %s",
                text1,
                text2);
    }
}
class User {

    private String userName, password;
    private double height;

    public User(String userName, String password, double height) {
        this.userName = userName;
        this.password = password;
        this.height = height;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", password=" + password + ", height=" + height + '}';
    }

}
class TicketTaker {

    long ticketsSold;
    final AtomicInteger ticketsTaken;

    public TicketTaker() {
        ticketsSold = 0;
        ticketsTaken = new AtomicInteger(0);
    }

    public void performJob() {
        IntStream.iterate(1, p -> p + 1)
                .parallel()
                .limit(100)
                .forEach(i -> ticketsTaken.getAndIncrement());
        IntStream.iterate(1, q -> q + 1)
                .parallel()
                .limit(500)
                .forEach(i -> ++ticketsSold);
        System.out.println(ticketsTaken + " " + ticketsSold);
    }

}
