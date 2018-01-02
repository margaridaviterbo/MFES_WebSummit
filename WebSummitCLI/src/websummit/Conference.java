package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Conference {
  public String Name;
  public String Stage;
  public Date Start;
  public Date End;

  public void cg_init_Conference_1(
      final String stage, final String name, final Date startDate, final Date endDate) {

    Stage = stage;
    Name = name;
    Start = startDate;
    End = endDate;
    return;
  }

  public Conference(
      final String stage, final String name, final Date startDate, final Date endDate) {

    cg_init_Conference_1(stage, name, startDate, endDate);
  }

  public Boolean IsFree(
      final Date date, final Time fromTime, final Time toTime, final VDMMap talks) {

    for (Iterator iterator_25 = MapUtil.rng(Utils.copy(talks)).iterator();
        iterator_25.hasNext();
        ) {
      Talk talk = (Talk) iterator_25.next();
      Boolean andResult_2 = false;

      if (Utils.equals(talk.Conference, Name)) {
        Boolean andResult_3 = false;

        if (date.Equals(talk.Date)) {
          if (Time.DoOverlap(fromTime, toTime, talk.Start, talk.End)) {
            andResult_3 = true;
          }
        }

        if (andResult_3) {
          andResult_2 = true;
        }
      }

      if (andResult_2) {
        return false;
      }
    }
    return true;
  }

  public Conference() {}

  public String toString() {

    return "Conference{"
        + "Name := "
        + Utils.toString(Name)
        + ", Stage := "
        + Utils.toString(Stage)
        + ", Start := "
        + Utils.toString(Start)
        + ", End := "
        + Utils.toString(End)
        + "}";
  }
}
