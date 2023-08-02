package com.example.demo_git.sorting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
@Slf4j
public class SortingTest {

UserSortTest getUserSortTest(){
    UserSortTest user = new UserSortTest("Иван", "Иванов", 106);
    user.setAdress(new Adress("Пушкина", "Москва", "10"));
    user.products.add(new Price("Горох", BigDecimal.TEN, 0));
    user.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
    user.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
    user.products.add(new Price("Арбуз", BigDecimal.TEN, 0));
    user.products.add(new Price("Булка", BigDecimal.valueOf(1.22), 0));
    user.products.add(new Price("Сок", BigDecimal.valueOf(10.00), 0));
    return user;
}
    List<UserSortTest> getTestDealers() {
        UserSortTest user = new UserSortTest("Иван", "Иванов", 106);
        user.setAdress(new Adress("Пушкина", "Москва", "10"));
        user.products.add(new Price("Горох", BigDecimal.TEN, 0));
        user.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
        user.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        user.products.add(new Price("Арбуз", BigDecimal.TEN, 0));
        user.products.add(new Price("Булка", BigDecimal.valueOf(1.22), 0));
        user.products.add(new Price("Сок", BigDecimal.valueOf(10.00), 0));

        UserSortTest user1 = new UserSortTest("Петр", "Петров", 107);
        user1.setAdress(new Adress("Горького", "Анапа", "101"));
        user1.products.add(new Price("Горох", BigDecimal.TEN, 100));
        user1.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
        user1.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        user1.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        user1.products.add(new Price("Арбуз", BigDecimal.TEN, 0));
        user1.products.add(new Price("Яблоко", BigDecimal.valueOf(1.22), 0));
        user1.products.add(new Price("Pickle", BigDecimal.valueOf(10.00), 0));
        UserSortTest user2 = new UserSortTest("Иван", "Петров", 101);
        user2.setAdress(new Adress("Маяковского", "Москва", "110"));
        user2.products.add(new Price("Горох", BigDecimal.TEN, 100));
        user2.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
        user2.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        user2.products.add(new Price("Банан", BigDecimal.valueOf(1.20), 0));
        UserSortTest user3 = new UserSortTest("Семен", "Семенов", 102);
        user3.setAdress(new Adress("Достоевского", "Анапа", "10"));
        user3.products.add(new Price("Горох", BigDecimal.TEN, 100));
        user3.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
        user3.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        user3.products.add(new Price("Банан", BigDecimal.valueOf(1.20), 0));
        UserSortTest user4 = new UserSortTest("Chuck", "Norris", 100);
        user4.setAdress(new Adress("Пушкина", "Москва", "10"));
        user4.products.add(new Price("Горох", BigDecimal.TEN, 100));
        user4.products.add(new Price("Горох", BigDecimal.TEN, 0));
        user4.products.add(new Price("Арбуз", BigDecimal.valueOf(11), 0));
        user4.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
        user4.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        user4.products.add(new Price("Pickle", BigDecimal.valueOf(10.00), 0));
        UserSortTest user5 = new UserSortTest("Иван", "Сидоров", 102);
        user5.setAdress(new Adress("Пушкина", "Москва", "1"));
        user5.products.add(new Price("Горох", BigDecimal.TEN, 100));
        user5.products.add(new Price("Яблоко", BigDecimal.valueOf(10.22), 100));
        user5.products.add(new Price("Горох", BigDecimal.TEN, 100));
        user5.products.add(new Price("Банан", BigDecimal.valueOf(1.20), 0));
        user5.products.add(new Price("Pickle", BigDecimal.valueOf(100.00), 100));
        return new ArrayList<>(asList(user, user1, user2, user3, user4, user5));

    }

    @Test
    public void testFilterAge() {
        List<UserSortTest> dealers = getTestDealers();
        log.info("Список продавцов {}", dealers);
        List<UserSortTest> result = dealers.stream().filter(v -> v.age > 104)
            .collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void testFilterName() {
        List<UserSortTest> dealers = getTestDealers();
        dealers = dealers.stream().filter(v -> v.age > 105)
            .collect(Collectors.toList());
        List<UserSortTest> sortedUserByName = dealers.stream().sorted(
                Comparator.comparing(UserSortTest::getUsername)
                    .thenComparing(UserSortTest::getLastName).reversed())
            .collect(
                Collectors.toList());
       // dealers.forEach(r-> log.info("Сортировка по продукту: {} ",r));
        log.info("===============Сорт продукт");

        dealers = sortedUserByName.stream().map(u -> new UserSortTest(u.username,
            u.lastName, u.getAge(), u.getAdress(),
           u.getProducts().stream()
                .sorted(Comparator.comparing(Price::getProductName))
                .collect(Collectors.toList()))).collect(Collectors.toList());
        dealers.forEach(r-> log.info("Сортировка по продукту: {} ",r));
        log.info("=============== сортировка количества");
        List<UserSortTest> result = dealers.stream()
            .map(u -> new UserSortTest(u.username,
                u.lastName, u.getAge(), u.getAdress(),
                u.getProducts().stream()
                           .sorted(Comparator.comparing(value -> value.getCount() == 0))
                           .collect(Collectors.toList())))
            .sorted(Comparator.comparing(UserSortTest::getUsername).thenComparing(UserSortTest::getLastName))
            .collect(Collectors.toList());

assertThat(result.get(0).getProducts().get(0).getProductName()).isEqualTo("Pickle");
assertThat(result.get(0).getProducts().get(2).getProductName()).isEqualTo("Арбуз");
    }

    @Test
    public void testFilterNameThenProductThenCount() {
        List<UserSortTest> dealers = getTestDealers();
        List<UserSortTest> sortedUserByName = dealers.stream().sorted(
                Comparator.comparing(UserSortTest::getUsername)
                    .thenComparing(UserSortTest::getUsername))
            .collect(
                Collectors.toList());
        sortedUserByName.forEach(r -> log.info("Сортировка по имени: {}", r));

        dealers = sortedUserByName.stream().map(u -> new UserSortTest(u.username,
            u.lastName, u.getAge(), u.getAdress(),
            u.getProducts().stream()
                .sorted(Comparator.comparing(Price::getProductName, Comparator.naturalOrder())
                    .thenComparing(Price::getCount, Comparator.reverseOrder())).collect(
                    Collectors.toList()))).collect(Collectors.toList());
        dealers.forEach(r -> log.info("Сортировка по продукту: {} ", r));

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Price {

        private String productName = "";
        private BigDecimal price = BigDecimal.ONE;
        private Integer count = 0;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Adress {

        private String street = "";
        private String city = "";
        private String dom = "";

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class UserSortTest {

        private String username = "";
        private String lastName = "";
        private Integer age = 10;
        private Adress adress = new Adress();
        private List<Price> products = new ArrayList<>();

        public UserSortTest(String username, String lastName, Integer age) {
            this.username = username;
            this.lastName = lastName;
            this.age = age;
        }
    }

}
