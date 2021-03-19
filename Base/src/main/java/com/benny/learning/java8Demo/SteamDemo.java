package com.benny.learning.java8Demo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 中间操作会返回一个新的steam——执行一个中间操作（例如filter）并不会执行实际的过滤操作，
 * 而是创建一个新的steam，并将原steam中符合条件的元素放入新创建的steam。
 * <p>
 * 晚期操作（例如forEach或者sum），会遍历steam并得出结果或者附带结果；在执行晚期操作之后，
 * steam处理线已经处理完毕，就不能使用了。在几乎所有情况下，晚期操作都是立刻对steam进行遍历。
 */
public class SteamDemo {
    private enum Status {
        OPEN, ClOSE
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        private Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }


    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.ClOSE, 8)
        );

        // 查找状态为OPEN的数据
        final long totalPointsOfOpenTasks = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)  // 将tasks流转换为Integer集合
                .sum();

        System.out.println("Total points: " + totalPointsOfOpenTasks);

        final long totalPointsOfOpenTasks2 = tasks.stream().parallel()  // 开启并行
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)  // 将tasks流转换为Integer集合
                .sum();

        final double totalPoints = tasks.stream()
                .map(task -> task.getPoints())  // 等效 Task::getPoints
                .reduce(0, Integer::sum);

        final double totalPoints2 = tasks.stream()
                .map(Task::getPoints)
                .reduce(0, Integer::sum);

        System.out.println("Total points (all tasks): " + totalPoints + " " + totalPoints2);

        // 分类
        final Map<Status, List<Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        final Collection<String> res = tasks.stream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(points -> points / totalPoints)
                .boxed()
                .mapToLong(weight -> (long) (weight * 100))
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());
        System.out.println("每个task的点数占总量的比例：" + res);


        /**
         * Stream的方法onClose 返回一个等价的有额外句柄的Stream，
         * 当Stream的close（）方法被调用的时候这个句柄会被执行。Stream API、Lambda表达式还有接口默认方法和静态方法支持的方法引用，是Java 8对软件开发的现代范式的响应。
         *
         */
        String filename = "src/main/java/com/benny/learning/java8Demo/node.md";
        final Path path = new File(filename).toPath();
        try (
                Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)
        ) {
            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
