package com.doker.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DockerController {

    @Value("${application.demo}")
    private String demo;

    @GetMapping("/docker-demo")
    public String dockerDemo() {
        return "<H1>" + demo + "</H1>";
    }

/*    public static void main(String[] args) {
        List<Integer> question = List.of(8, 6, 2, 9, 4);
        List<Integer> question2 = List.of(17, 15, 21, 7, 27);


        Map<String, List<Integer>> response = question.stream()
                .collect(Collectors.groupingBy(integer -> integer % 2 == 0 ? "Even" : "Odd"));

        System.out.println(response);

        System.out.println(question.reversed());
        Set<Integer> collect = question2.stream()
                .filter(q -> q % 3 == 0)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(collect);

        List<Integer> combined5 = Stream.concat(
                        question.stream(),
                        question2.stream())
                .toList().reversed();
        System.out.println(combined5);

        List<List<Integer>> question7 = List.of(question2, question);

        List<Integer> collect1 = question7.stream().flatMap(Collection::stream)
                .sorted()
                .toList();
        System.out.println("single list : : " + collect1);

        Fib genFib = limit -> {
            List<Integer> genFi = new ArrayList<>();
            genFi.add(0);
            genFi.add(1);
            IntStream.range(2, limit)
                    .forEach(i -> genFi.add(genFi.getLast() + genFi.get((genFi.size() - 2))));
            return genFi;

        };

        System.out.println("Series : : " + genFib.genFib(10));

    }

    @FunctionalInterface
    interface Fib {
        List<Integer> genFib(Integer limit);
    }*/

}
