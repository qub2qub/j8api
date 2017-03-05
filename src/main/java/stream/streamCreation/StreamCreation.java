package stream.streamCreation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Types how to declare streams.
 *
 * Created by dkorolev on 1/2/2017.
 */
public class StreamCreation {


    public static void main(String[] args) {

        //creation from Collection
        List<String> list = Arrays.asList("Bob", "Tom", "Mark");
        list.stream();
        System.out.println("Stream from Collection:"+list.stream().collect(Collectors.toList()));

        //from Stream
        Stream<String> stringStream = Stream.of("Bob", "Tom", "Mark");
        System.out.println("Stream with elements:"+stringStream.collect(Collectors.toList()));

        //from File
        File file = new File("1.tmp");
        file.deleteOnExit();
        PrintWriter out;
        try {
            out = new PrintWriter(file);
            out.println("a1");
            out.println("a2");
            out.println("a3");
            out.close();

            Stream<String> streamFromFiles = Files.lines(Paths.get(file.getAbsolutePath()));
            System.out.println("streamFromFiles = " + streamFromFiles.collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }



        //from String
        IntStream intStream = "123".chars();
        System.out.print("IntStream from String: ");
        intStream.forEach((e)-> System.out.print(e + ","));
        System.out.println();

        //Stream builder
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> builderStream = builder.add("Bob").add("Tom").add("Mark").build();
        System.out.println("Stream from builderStream:" + builderStream.collect(Collectors.toList()));
    }
}
