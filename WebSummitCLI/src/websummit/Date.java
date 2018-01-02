package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  public Number Day;
  public Number Month;
  public Number Year;

  public void cg_init_Date_1(final Number d, final Number m, final Number y) {

    Day = d;
    Month = m;
    Year = y;
  }

  public Date(final Number d, final Number m, final Number y) {

    cg_init_Date_1(d, m, y);
  }

  public Boolean IsEarlierThan(final Date other) {

    if (Year.longValue() < other.Year.longValue()) {
      return true;
    }

    Boolean andResult_4 = false;

    if (Utils.equals(Year, other.Year)) {
      if (Month.longValue() < other.Month.longValue()) {
        andResult_4 = true;
      }
    }

    if (andResult_4) {
      return true;
    }

    Boolean andResult_5 = false;

    if (Utils.equals(Year, other.Year)) {
      Boolean andResult_6 = false;

      if (Utils.equals(Month, other.Month)) {
        if (Day.longValue() <= other.Day.longValue()) {
          andResult_6 = true;
        }
      }

      if (andResult_6) {
        andResult_5 = true;
      }
    }

    if (andResult_5) {
      return true;
    }

    return false;
  }

  public Boolean IsLaterThan(final Date other) {

    if (Year.longValue() > other.Year.longValue()) {
      return true;
    }

    Boolean andResult_7 = false;

    if (Utils.equals(Year, other.Year)) {
      if (Month.longValue() > other.Month.longValue()) {
        andResult_7 = true;
      }
    }

    if (andResult_7) {
      return true;
    }

    Boolean andResult_8 = false;

    if (Utils.equals(Year, other.Year)) {
      Boolean andResult_9 = false;

      if (Utils.equals(Month, other.Month)) {
        if (Day.longValue() >= other.Day.longValue()) {
          andResult_9 = true;
        }
      }

      if (andResult_9) {
        andResult_8 = true;
      }
    }

    if (andResult_8) {
      return true;
    }

    return false;
  }

  public static Boolean DoOverlap(
      final Date start1, final Date end1, final Date start2, final Date end2) {

    Boolean orResult_1 = false;

    Boolean andResult_11 = false;

    if (start1.IsEarlierThan(start2)) {
      if (end1.IsLaterThan(start2)) {
        andResult_11 = true;
      }
    }

    if (andResult_11) {
      orResult_1 = true;
    } else {
      Boolean andResult_12 = false;

      if (start2.IsEarlierThan(start1)) {
        if (end2.IsLaterThan(start1)) {
          andResult_12 = true;
        }
      }

      orResult_1 = andResult_12;
    }

    return orResult_1;
  }

  public Boolean Equals(final Date other) {

    Boolean andResult_13 = false;

    if (Utils.equals(Year, other.Year)) {
      Boolean andResult_14 = false;

      if (Utils.equals(Month, other.Month)) {
        if (Utils.equals(Day, other.Day)) {
          andResult_14 = true;
        }
      }

      if (andResult_14) {
        andResult_13 = true;
      }
    }

    return andResult_13;
  }

  public Date() {}

  public String toString() {

    return "Date{"
        + "Day := "
        + Utils.toString(Day)
        + ", Month := "
        + Utils.toString(Month)
        + ", Year := "
        + Utils.toString(Year)
        + "}";
  }
}
