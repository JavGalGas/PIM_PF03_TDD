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

    //
    private int day = 1;
    private Months month = Months.JANUARY;
    private int year = 2000;

    public MyDate(int day, Months month,  int year) {
        try {
            setYear(year);
            setMonth(month);
            setDay(day);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }
    }

    public MyDate(){
        setYear(2000);
        setMonth(Months.JANUARY);
        setDay(1);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day < 1 || day > 31)
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        if (month == Months.FEBRUARY) {
            if (day > 29 ||(day == 29 && isNotLeapYear(year))) {
                throw new IllegalArgumentException(ERR_INVALID_DAY);
            }
        }
        else if (day > maxNumberOfDays(month.monthNumber)) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this.day = day;
    }

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months month) {
        if (month == null)
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        if (day > maxNumberOfDays(month.monthNumber)) {
            if (month != Months.FEBRUARY || day != 29 || isNotLeapYear(year))
                throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        this.month = month;
    }

    public boolean validateMonth(Months month) {
        if (month == null)
            return false;
        if (day > maxNumberOfDays(month.monthNumber)) {
            return month == Months.FEBRUARY && day == 29 && !isNotLeapYear(year);
        }
        return true;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (month == Months.FEBRUARY && day == 29 && isNotLeapYear(year))
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        this.year = year;
    }

    private boolean isNotLeapYear(int year) {
        return (year % 4 != 0 || year % 100 == 0) && (year % 400 != 0);
    }

    private int maxNumberOfDays(int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return 28;
            default: return 30;
        }
    }

    private boolean validateDate(int day, Months month, int year) {
        if (month == null)
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        if (day < 1 || day > 31)
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        if (month == Months.FEBRUARY) {
            if (day > 29 ||(day == 29 && isNotLeapYear(year))) {
                throw new IllegalArgumentException(ERR_INVALID_DAY);
            }
        }
        else if (day > maxNumberOfDays(month.monthNumber)) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        return true;
    }
}
