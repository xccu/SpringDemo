package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        employees.add(new Employee(1,"jack",11,1,1));
        employees.add(new Employee(2,"tom",16,1,1));
        employees.add(new Employee(3,"jerry",12,1,2));
        employees.add(new Employee(4,"smith",13,1,2));
        employees.add(new Employee(5,"mary",15,0,4));
        employees.add(new Employee(6,"Kobe",15,1,4));
        employees.add(new Employee(7,"Jordan",18,1,3));
        employees.add(new Employee(8,"James",17,1,3));
        employees.add(new Employee(9,"britney",19,0,2));
        employees.add(new Employee(10,"monroe",19,0,1));
        testGroupBy(employees);
    }

    /**
     * map
     * 类似于C#中employees.Select(e=>e.Age)
     * @param employees
     */
    public static void testMap(List<Employee> employees) {
        employees.stream().map(a -> a.getAge()).forEach(System.out::println);
        employees.stream().findFirst();
    }

    /**
     * filter
     * 类似于C#中employees.Where(e => e.Age > 21)
     * @param employees
     */
    public static void testFilter(List<Employee> employees) {
        Stream<Employee> filterPerson = employees.stream().filter(e -> e.getAge() > 12);
        for (Object item : filterPerson.toArray()) {
            System.out.println(((Employee) item).getName());
        }
    }

    /**
     * FindFirst OrElse
     * 类似于C#中employees.FirstOrDefault(e => e.Age == 10) ?? new Employee()的用法
     * @param employees
     */
    public static void testFindFirst(List<Employee> employees) {
        Employee person = employees.stream().filter(a -> a.getAge() == 10).findFirst().orElse(new Employee());
        System.out.println(person);
    }

    /**
     * 随机返回一个符合条件的数据
     * @param employees
     */
    public static void testFindAny(List<Employee> employees){
        Optional<Employee> findAny = employees.stream().filter(a->a.getAge()==1).findAny();
        System.out.println(findAny);
    }

    /**
     * count
     *
     * @param employees
     */
    public static void testCount(List<Employee> employees) {
        long count = employees.stream().filter(e -> e.getAge() > 12).count();
        System.out.println(count);
    }

    /**
     * Match
     *
     * anyMatch表示，判断的条件里，任意一个元素成功，返回true
     * allMatch表示，判断条件里的元素，所有的都是，返回true
     * noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
     * @param employees
     */
    public static void testMatch(List<Employee> employees) {
        boolean result1 = employees.stream().anyMatch(e -> e.getName().equals("jack"));
        System.out.println(result1);
        boolean result2 = employees.stream().allMatch(e -> e.getAge() == 13);
        System.out.println(result2);
        boolean result3 = employees.stream().noneMatch(e -> e.getName().equals("jack"));
        System.out.println(result3);
    }

    /**
     * limit限制输出个数
     * @param employees
     */
    public static void testLimit(List<Employee> employees) {
        employees.stream().filter(a -> a.getAge() > 12).limit(3).forEach(System.out::println);
    }

    /**
     * sorted排序
     * @param employees
     */
    public static void testSorted1(List<Employee> employees) {
        //employees.stream().sorted((a1, a2) -> (Integer.compare(a1.getAge(), a2.getAge()))).forEach(a -> System.out.println(a));
        //employees.stream().sorted((a1, a2) -> a2.getAge()-a1.getAge()).forEach(a -> System.out.println(a));
        //employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).forEach(a -> System.out.println(a));
        //多字段排序  年龄和部门id降序
        employees.stream().
                sorted(Comparator.comparing(Employee::getAge,Comparator.reverseOrder()).thenComparing(Employee::getDeptId,Comparator.reverseOrder())).
                forEach(System.out::println);
    }

    /**
     * collect集合转化
     * @param employees
     */
    public static void testCollect(List<Employee> employees) {
        List<Employee> list1 = employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).collect(Collectors.toList());
        //list转map
        Map<Integer, String> map = employees.stream().filter(e -> e.getAge() > 12).collect(Collectors.toMap(k -> k.getId(), v -> v.getName()));
        for (Map.Entry e : map.entrySet()){
            System.out.println("key = "+e.getKey()+" value = "+e.getValue());
        }
        //Map转list
        List<String> list2 = map.entrySet().stream().filter(e -> e.getKey() > 5).map(e -> e.getValue()).collect(Collectors.toList());
        list2.forEach(System.out::println);

    }
    // count max min sum avg
    public static void testSummaryStatistics(List<Employee> employees) {
        IntSummaryStatistics statistics = employees.stream().mapToInt(a -> a.getAge()).summaryStatistics();
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
    }


    // group by
    public static void testGroupBy(List<Employee> employees) {

        Map<Object,List<Employee>> map1 = employees.stream().collect(Collectors.groupingBy(a->a.getDeptId()));
        map1.forEach((k,v)->System.out.println("key = "+k+" value = "+v));

        Map<Object,Long> map2 = employees.stream().collect(Collectors.groupingBy(a->a.getDeptId(),Collectors.counting()));
        map2.forEach((k,v)->System.out.println(k+" : "+v));

        //多条件分组
        Map<Object,Long> map3 = employees.stream().collect(Collectors.groupingBy(a->"age:"+a.getAge()+"_deptId:"+a.getDeptId(),Collectors.counting()));
        map3.forEach((k,v)->System.out.println(k+" : "+v));
    }


    /**
     * min
     * @param employees
     */
    public static void testMin(List<Employee> employees) {
        Employee employee = employees.stream().min((a1, a2) -> (a1.getAge() - a2.getAge())).get();
        System.out.println(employee);
    }

    // max
    public static void testMax(List<Employee> employees) {
        Employee employee = employees.stream().max((a, b) -> (a.getAge() - b.getAge())).get();
        System.out.println(employee);
    }
}
