package com.jch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jch.async.CF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.github.fge.jsonpatch.*;
import com.jch.generics.AgeComparator;
import com.jch.generics.Person;

public class Main {

    public static void main(String[] args) {
        // Questions:
        //  Every method that calls CompletableFuture get() method with wait until it is completed? Doesn't it need to have dedicated code?

        // Check https://www.baeldung.com/java-completablefuture

        // Pas très bien compris, ça a l'air d'être synchrone
        // CF as a Future
        /*try {
            Future<String> f = CF.calculateAsync();
            System.out.println(f.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("within main, after call to CF.calculateAsync");*/

        // Async style
        // OK - le "within main" est bien affiché avant
        /*CompletableFuture<String> asyncCF = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "OK";
            } catch (Exception e) {
                return (e.getMessage());
            }
        });
        System.out.println("within main, after CompletableFuture.supplyAsync");
        try {
            System.out.println(asyncCF.get());
        } catch (Exception e) {
            System.out.println("asyncCF.get() issue: " + e.getMessage());
        }*/

        // Plusieurs façons de chaîner, selon que l'on veut:
        //  - effectuer un traitement sur la valeur et la retourner (thenApply())
        //  - simplement utiliser la valeur (thenAccept())
        //  - faire une opération n'impliquant pas la valeur (thenRun())
        /*CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> futureApply = completableFuture.thenApply(s -> s + " World");
        CompletableFuture<Void> futureAccept = futureApply.thenAccept(s -> {
            System.out.println(s);
            s += ", really";}
            );

        // This prints null, which is expected because futureAccept does not return anything
        CompletableFuture<Void> futureAcceptTwo = futureAccept.thenAccept(s -> System.out.println(s));
        CompletableFuture<Void> futureRun = futureAcceptTwo.thenRun(() -> System.out.println("Ouh yeah"));*/


        // Executing in parallel
        /*List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Beautiful");
        list.add("World");
        // 1- building the completable future list
        List<CompletableFuture<String>> completableFutures =
                list.stream().map(word -> CompletableFuture.supplyAsync(() -> word)).collect(Collectors.toList());
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {throw new CompletionException(new Exception("Failed"));});
        completableFutures.add(future3);
        // 2- combining
        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

        // 3- creating a CombinedFuture with a list of output
        CompletableFuture<List<String>> allCompletableFuture = allFutures.thenApply(future -> {
            return completableFutures.stream()
                    .map(completableFuture -> completableFuture.join())
                    .collect(Collectors.toList());
        });

        try {
            List<String> finale = allCompletableFuture.get();
            finale.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        // Trying foreach on an singleton
        /*List<String> tesSingletongStream = Collections.singletonList("a");
        List<String> target = new ArrayList<>();
        tesSingletongStream.stream().forEach(val -> target.add(val));*/

        /*final ObjectMapper mapper = new ObjectMapper();

        String patchCommand =
                "[{\"op\":\"add\", \"path\":\"/brand\", \"value\":[ \"Mercedes\", \"voiture\",  \"doors\"]}]";
        String target =
                "{ \"brand\" : [ \"Renault\", \"doors\" ] }";
        try {
            final JsonPatch patch = mapper.readValue(patchCommand, JsonPatch.class);
            JsonNode toBePatched = mapper.readValue(target, JsonNode.class);
            final JsonNode patched = patch.apply(toBePatched);
            System.out.println("Patched: " + patched.toString());
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        Person bob = new Person("Bob", 43);
        Person alice = new Person ("Alice", 37);

        List<Person> personList = new ArrayList<>();
        personList.add(bob);
        personList.add(alice);
        System.out.println(personList);

        Collections.sort(personList, new AgeComparator());
        System.out.println(personList);

        System.out.println(bob.compareTo(alice));
        System.out.println(bob.compareTo(new Integer(0)));

        // Use of an anonymous class as a comparator, if it is supposed to be used in only one place
        Collections.sort(personList, new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o2.getAge(), o1.getAge());
            }
        });
        System.out.println(personList);

        Collections.sort(personList, new AgeComparator());
        System.out.println(personList);
        Collections.sort(personList, Collections.reverseOrder(new AgeComparator()));
        System.out.println(personList);

        Collections.sort(personList, new AgeComparator());
        System.out.println(personList);
        Collections.sort(personList, new AgeComparator().reversed());
        System.out.println (personList);


    }
}
