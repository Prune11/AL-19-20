package fr.polytech.unice.credirama.transaction.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CrediramaDate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Transaction transaction;

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    private int sec;

    private int millis;


    public CrediramaDate(GregorianCalendar gregorianCalendar) {
        this.year = gregorianCalendar.get(Calendar.YEAR);
        this.month = gregorianCalendar.get(Calendar.MONTH);
        this.day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        this.hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregorianCalendar.get(Calendar.MINUTE);
        this.sec = gregorianCalendar.get(Calendar.SECOND);
        this.millis = gregorianCalendar.get(Calendar.MILLISECOND);
    }

    public CrediramaDate(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.sec = calendar.get(Calendar.SECOND);
        this.millis = calendar.get(Calendar.MILLISECOND);
    }

    public GregorianCalendar toGregorian(){
        return new GregorianCalendar(this.year, this.month, this.day, this.hour, this.minute, this.sec);
    }

    /*
     * remove Transaction
     * */
    @Override
    public String toString() {
        return "CrediramaDate{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", sec=" + sec +
                ", millis=" + millis +
                '}';
    }

    /*
    * remove Transaction
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrediramaDate that = (CrediramaDate) o;
        return id == that.id &&
                year == that.year &&
                month == that.month &&
                day == that.day &&
                hour == that.hour &&
                minute == that.minute &&
                sec == that.sec &&
                millis == that.millis;
    }

    /*
     * remove Transaction
     * */
    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, day, hour, minute, sec, millis);
    }
}
