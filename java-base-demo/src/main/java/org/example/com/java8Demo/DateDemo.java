package org.example.com.java8Demo;

import java.time.*;

public class DateDemo {
    public static void main(String[] args) {
        methodTest();
    }

    static void methodTest() {
        // Clock
        // 替代了 System.currentTimeMillis() TimeZone.getDefault()
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());    // 当前时区的时间
        System.out.println(clock.millis()); // 纳米时间

        // LocalDate 获取时间的日期部分
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dateFromClock);

        final LocalTime time = LocalTime.now();
        final LocalTime timeFromCock = LocalTime.now( clock);
        System.out.println(time);
        System.out.println(timeFromCock);

        // LocalDateTime 同时包含了LocalDate LocalTime的信息，但不包含ISO-8601日历系统中的时区信息
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        // ZoneDateTime: 保存有ISO-8601日期系统的日期和时间，而且有时区信息
        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );

        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );

        // Duration类，它持有的时间精确到秒和纳秒,可以很容易得计算两个日期之间的不同
        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        final LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );

        final Duration duration = Duration.between( from, to );
        System.out.println( "Duration in days: " + duration.toDays() );
        System.out.println( "Duration in hours: " + duration.toHours() );
    }
}
