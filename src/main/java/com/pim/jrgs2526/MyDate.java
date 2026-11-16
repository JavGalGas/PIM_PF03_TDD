package com.pim.jrgs2526;

public class MyDate {

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    private int day;
    private Months month;
    private int year;

    public MyDate(int day, Months month,  int year) {
        if (isDateInvalid(day, month, year))
            throw new IllegalArgumentException(ERR_INVALID_DATE);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate(){
        this.year = 2000;
        this.month = Months.JANUARY;
        this.day = 1;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (isDateInvalid(day, this.getMonth(), this.getYear()))
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        this.day = day;
    }

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months month) {
        if (isDateInvalid(this.day, month, this.getYear()))
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (isDateInvalid(this.getDay(), this.getMonth(), year))
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        this.year = year;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private int maxNumberOfDays(Months month, int year) {
        switch (month) {
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;

            default: return 31;
        }
    }

    private boolean isDateInvalid(int day, Months month, int year) {
        return month == null
                || day < 1
                || day > maxNumberOfDays(month, year);
    }
}
