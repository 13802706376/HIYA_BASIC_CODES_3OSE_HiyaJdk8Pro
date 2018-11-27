package com.hiya.se;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class HiyaDate
{
    public static void main(String[] args)
    {
        /*
         * LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
         * LocalTime——它代表的是不含日期的时间 LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
         * ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
         */
        final Clock clock = Clock.systemUTC();
        long millis = clock.millis();
        Instant instant = clock.instant();
        System.out.println(instant);
        System.out.println(millis);

        final LocalDate date107 = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date107);
        System.out.println(dateFromClock);

        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);

        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);

        // 获取当天日期
        LocalDate today = LocalDate.now();
        System.out.println("Today's Local date : " + today);

        // 获取年月日
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);

        // 获取某个特定的日期
        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        System.out.println("Your Date of birth is : " + dateOfBirth);

        // 获取1周后的日期
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);

        // 一年前后日期
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year : " + nextYear);

        // 使用时钟，Java
        // 8中自带了一个Clock类，你可以用它来获取某个时区下当前的瞬时时间，日期或者时间。可以用Clock来替代System.currentTimeInMillis()与
        // TimeZone.getDefault()方法。
        Clock clock107 = Clock.systemUTC();
        System.out.println("Clock : " + clock107);
        Clock.systemDefaultZone();
        System.out.println("Clock : " + clock107);

        // 格式化
        LocalDateTime arrivalDate = LocalDateTime.now();
        try
        {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at : %s %n", landing);
        } catch (DateTimeException ex)
        {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        HiyaDate hd = new HiyaDate();
        hd.fn7();

        // 这个时间默认是东0区的时间，加8就是东八区（北京时间）
        System.out.println("当前时间为：" + clock.instant());

    }

    // 1.基本年月日,时分秒及当前时间的获取:人所读的
    public void fn1()
    {
        // 1.获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("1.获取当前时间==========" + now);
        // 2.设置任意时间
        LocalDateTime ldt = LocalDateTime.of(2018, 06, 03, 12, 11, 13);
        System.out.println("2.设置任意时间===============" + ldt);
        // 3.增加或减少年月日,时分秒
        LocalDateTime ldt2 = ldt.plusYears(2);
        System.out.println(ldt2);
        ldt.minusMonths(1);
        // 4.获取年月日,时分秒
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonth());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
        // 获取毫秒见fn2
        System.out.println(ldt.getNano());
        System.out.println(ldt.getDayOfWeek());
        System.out.println(ldt.getDayOfYear());
    }

    // 2.Instant:时间戳:计算机所读的时间（使用 Unix 元年 1970年1月1日 00:00:00 所经历的毫秒值）
    public void fn2()
    {
        Instant now = Instant.now();
        System.out.println(now);
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(now.toEpochMilli());
        System.out.println(now.getNano());
        Instant instant = Instant.ofEpochSecond(5);
        System.out.println(instant);
    }

    // 3.Duration : 用于计算两个“时间”间隔
    public void fn3() throws InterruptedException
    {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Duration===============" + duration.toMillis());
        LocalTime start1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime end1 = LocalTime.now();
        Duration duration1 = Duration.between(start1, end1);
        System.out.println("Duration===============" + duration1.toMillis());
    }

    // 4.Period : 用于计算两个“日期”间隔
    public void fn4() throws InterruptedException
    {
        LocalDate begin = LocalDate.of(2018, 6, 1);
        LocalDate end = LocalDate.now();
        Period period = Period.between(begin, end);
        System.out.println(period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "日");
    }

    // 5.TemporalAdjuster : 时间校正器
    public void fn5()
    {
        LocalDateTime now = LocalDateTime.now();
        // 修改至某月
        LocalDateTime ldt2 = now.withMonth(2);
        System.out.println(ldt2);

        // 获取下一个周日的日期
        LocalDateTime ldt3 = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime with = now.with((x) ->
        {
            LocalDateTime localDateTime = (LocalDateTime) x;
            DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY))
            {
                return localDateTime.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY))
            {
                return localDateTime.plusDays(2);
            } else
            {
                return localDateTime.plusDays(1);
            }
        });
        System.out.println(with);
    }

    // 6. DateTimeFormatter : 解析和格式化日期或时间
    public void fn6()
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String format = dateTimeFormatter.format(now);
        String format1 = now.format(dateTimeFormatter);
        System.out.println(format);
        System.out.println(format1);

        LocalDateTime parse = now.parse(format1, dateTimeFormatter);
        System.out.println(parse);

    }

    // 7.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
    public void fn7()
    {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    public void fn8()
    {
        LocalDateTime ldt = LocalDateTime.now((ZoneId.of("Asia/Hong_Kong")));
        System.out.println(ldt);
    }

}
