package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Venue {
  public String Name;
  public Date Start;
  public Date End;

  public void cg_init_Venue_1(final String name, final Date startDate, final Date endDate) {

    Name = name;
    Start = startDate;
    End = endDate;
    return;
  }

  public Venue(final String name, final Date startDate, final Date endDate) {

    cg_init_Venue_1(name, startDate, endDate);
  }

  public Boolean IsFree(final Date fromDate, final Date toDate, final VDMMap conferences) {

    for (Iterator iterator_30 = MapUtil.rng(Utils.copy(conferences)).iterator();
        iterator_30.hasNext();
        ) {
      Conference conference = (Conference) iterator_30.next();
      Boolean andResult_28 = false;

      if (Utils.equals(conference.Name, Name)) {
        if (Date.DoOverlap(fromDate, toDate, conference.Start, conference.End)) {
          andResult_28 = true;
        }
      }

      if (andResult_28) {
        return false;
      }
    }
    return true;
  }

  public Venue() {}

  public String toString() {

    return "Venue{"
        + "Name := "
        + Utils.toString(Name)
        + ", Start := "
        + Utils.toString(Start)
        + ", End := "
        + Utils.toString(End)
        + "}";
  }
}
