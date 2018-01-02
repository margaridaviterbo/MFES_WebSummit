package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Time {
  public Number Hours;
  public Number Minutes;

  public void cg_init_Time_1(final Number hours, final Number minutes) {

    Hours = hours;
    Minutes = minutes;
    return;
  }

  public Time(final Number hours, final Number minutes) {

    cg_init_Time_1(hours, minutes);
  }

  public Boolean IsEarlierThan(final Time other) {

    if (Hours.longValue() < other.Hours.longValue()) {
      return true;
    }

    Boolean andResult_22 = false;

    if (Utils.equals(Hours, other.Hours)) {
      if (Minutes.longValue() <= other.Minutes.longValue()) {
        andResult_22 = true;
      }
    }

    if (andResult_22) {
      return true;
    }

    return false;
  }

  public Boolean IsLaterThan(final Time other) {

    if (Hours.longValue() > other.Hours.longValue()) {
      return true;
    }

    Boolean andResult_23 = false;

    if (Utils.equals(Hours, other.Hours)) {
      if (Minutes.longValue() >= other.Minutes.longValue()) {
        andResult_23 = true;
      }
    }

    if (andResult_23) {
      return true;
    }

    return false;
  }

  public static Boolean DoOverlap(
      final Time start1, final Time end1, final Time start2, final Time end2) {

    Boolean orResult_2 = false;

    Boolean andResult_25 = false;

    if (start1.IsEarlierThan(start2)) {
      if (end1.IsLaterThan(start2)) {
        andResult_25 = true;
      }
    }

    if (andResult_25) {
      orResult_2 = true;
    } else {
      Boolean andResult_26 = false;

      if (start2.IsEarlierThan(start1)) {
        if (end2.IsLaterThan(start1)) {
          andResult_26 = true;
        }
      }

      orResult_2 = andResult_26;
    }

    return orResult_2;
  }

  public Time() {}

  public String toString() {

    return "Time{"
        + "Hours := "
        + Utils.toString(Hours)
        + ", Minutes := "
        + Utils.toString(Minutes)
        + "}";
  }
}
