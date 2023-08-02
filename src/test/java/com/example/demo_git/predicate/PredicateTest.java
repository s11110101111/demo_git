package com.example.demo_git.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */

public class PredicateTest {

    List<String> names;

    @BeforeEach
    void stetUp() {
        names = Arrays.asList("Chuck", "Pedro", "Petro", "Alex", "Dracula", "Alexander");
    }

    @Test
    public void whenFilterListThenSuccess() {

        List<String> result = names.stream().filter(name -> name.startsWith("A"))
            .collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains("Alex", "Alexander");
    }

    @Test
    public void whenFilterListWithMultipleFilterThenSuccess() {
        List<String> result = names.stream()
            .filter(name -> name.startsWith("A"))
            .filter(name -> name.length() < 5)
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");

    }

    @Test
    public void whenFilterListWithComplexPredicateThenSuccess() {
        List<String> result = names.stream()
            .filter(name -> name.startsWith("A") && name.length() < 5)
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");
    }

    @Test
    public void whenFilterListWithCombinedPredicatesUsingAndThenSuccess() {
        Predicate<String> predicate1 = str -> str.startsWith("A");
        Predicate<String> predicate2 = str -> str.length() < 5;
        List<String> result = names.stream()
            .filter(predicate1.and(predicate2))
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");

    }

    @Test
    public void whenFilterListWithCombinedPredicatesUsingOrThenSuccess() {
        Predicate<String> predicate1 = str -> str.startsWith("T");
        Predicate<String> predicate2 = str -> str.length() < 5;
        List<String> result = names.stream()
            .filter(predicate1.or(predicate2))
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");
    }

    @Test
    public void whenFilterListWithCombinedPredicatesUsingOrAndNegateThenSuccess() {
        Predicate<String> predicate1 = str -> str.startsWith("T");
        Predicate<String> predicate2 = str -> str.length() >= 5;
        List<String> result = names.stream()
            .filter(predicate1.or(predicate2.negate()))
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");
    }

    @Test
    public void whenFilterListCombinedPredicatesInLineThenSuccess() {
        List<String> result = names.stream()
            .filter(((Predicate<String>) name -> name.startsWith("A"))
                .and(name -> name.length() < 5))
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");
    }

    @Test
    public void whenFilterListWithCollectionOfPredicatesUsingAndThenSuccess() {
        List<Predicate<String>> allPredicates = new ArrayList<>();
        allPredicates.add(str -> str.startsWith("A"));
        allPredicates.add(str -> str.length() < 5);
        allPredicates.add(str -> str.contains("l"));
        List<String> result = names.stream()
            .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
            .collect(Collectors.toList());
        assertThat(result).contains("Alex");
    }

    @Test
    public void xorPredicate() {
        boolean op1;
        boolean op2;
        op1 = true;
        op2 = false;
        assertThat(op1 ^ op2).isTrue();
        assertThat((op1 | op2) & !(op1 & op2)).isTrue();
        assertThat((op1 != op2)).isTrue();
        op1 = false;
        op2 = false;
        assertThat(op1 ^ op2).isFalse();
        assertThat((op1 | op2) & !(op1 & op2)).isFalse();
        assertThat((op1 != op2)).isFalse();
        op1 = true;
        op2 = true;
        assertThat(op1 ^ op2).isFalse();
        assertThat((op1 | op2) & !(op1 & op2)).isFalse();
        assertThat((op1 != op2)).isFalse();
    }
    public static <T> Predicate<T> xor(Predicate<T> predicate1, Predicate<T> predicate2) {
return t -> predicate1.or(predicate2).and(predicate1.and(predicate2).negate()).test(t);
      //  return predicate1.or(predicate2).and(predicate1.and(predicate2).negate()) ; // write your code here
    }
}
