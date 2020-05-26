package de.ginisolutions.trader.domain.enumeration;

/**
 * The Interval enumeration.
 */
public enum INTERVAL {
    ONE_MINUTE(60000L),
    THREE_MINUTES(180000L),
    FIVE_MINUTES(300000L),
    FIFTEEN_MINUTES(900000L),
    HALF_HOURLY(1800000L),
    HOURLY(3600000L),
    TWO_HOURLY(7200000L),
    FOUR_HOURLY(14400000L),
    SIX_HOURLY(21600000L),
    EIGHT_HOURLY(28800000L),
    TWELVE_HOURLY(43200000L),
    DAILY(86400000L),
    THREE_DAILY(259200000L),
    WEEKLY(604800000L),
    MONTHLY(2592000000L);

    private final Long interval;

    INTERVAL(Long interval) {
        this.interval = interval;
    }

    public Long getInterval() {
        return interval;
    }

    public static INTERVAL of(Long millis) {
        switch (Math.toIntExact(millis / 1000)) {
            case 60: // 60000 milliseconds
                return INTERVAL.ONE_MINUTE;
            case 180: // 60000 * 3 = 180000 milliseconds
                return INTERVAL.THREE_MINUTES;
            case 300: // 60000 * 5 = 180000 milliseconds
                return INTERVAL.FIVE_MINUTES;
            case 900: // 60000 * 15 = 180000 milliseconds
                return INTERVAL.FIFTEEN_MINUTES;
            case 1800:// 60000 * 30 = 180000 milliseconds
                return INTERVAL.HALF_HOURLY;
            case 3600:// 60000 * 60 = 180000 milliseconds
                return INTERVAL.HOURLY;
            case 7200:// 60000 * 60 * 2 = 180000 milliseconds
                return INTERVAL.TWO_HOURLY;
            case 14400:// 60000 * 60 * 4 = 180000 milliseconds
                return INTERVAL.FOUR_HOURLY;
            case 21600:// 60000 * 60 * 6 = 180000 milliseconds
                return INTERVAL.SIX_HOURLY;
            case 28800:// 60000 * 60 * 8 = 180000 milliseconds
                return INTERVAL.EIGHT_HOURLY;
            case 43200:// 60000 * 60 * 12 = 180000 milliseconds
                return INTERVAL.TWELVE_HOURLY;
            case 86400:// 60000 * 60 * 24 = 180000 milliseconds
                return INTERVAL.DAILY;
            case 259200:// 60000 * 60 * 24 * 3 = 180000 milliseconds
                return INTERVAL.THREE_DAILY;
            case 604800:// 60000 * 60 * 24 * 7 = 180000 milliseconds
                return INTERVAL.WEEKLY;
            case 2592000:// 60000 * 60 * 24 * 30 = 86400000 * 30 milliseconds -> this would lead to integer overflow
                return INTERVAL.MONTHLY;
            default:
                throw new IllegalArgumentException("Time interval of " + millis + " is not implemented");
        }
    }

    @Override
    public String toString() {
        return "Interval{" +
            "interval=" + interval +
            '}';
    }
}
