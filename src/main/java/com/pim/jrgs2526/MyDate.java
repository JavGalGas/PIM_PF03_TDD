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
        if (validateDate(day, month, year)) {
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }
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
        if (validateDate(day, this.getMonth(), this.getYear()))
            this.day = day;
        else
            throw new IllegalArgumentException(ERR_INVALID_DAY);
    }

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months month) {
        if (validateDate(this.day, month, this.getYear()))
            this.month = month;
        else
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (validateDate(this.getDay(), this.getMonth(), year))
            this.year = year;
        else
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private int maxNumberOfDays(Months month, int year) {
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31;
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default: return 30;
        }
    }

    private boolean validateDate(int day, Months month, int year) {
        return month != null
                && day >= 1
                && day <= maxNumberOfDays(month, year);
    }
}
