package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Stage {
  public String Venue;
  public String Name;
  public Date Start;
  public Date End;

  public void cg_init_Stage_1(
      final String venue, final String name, final Date startDate, final Date endDate) {

    Venue = venue;
    Name = name;
    Start = startDate;
    End = endDate;
    return;
  }

  public Stage(final String venue, final String name, final Date startDate, final Date endDate) {

    cg_init_Stage_1(venue, name, startDate, endDate);
  }

  public Boolean IsFree(
      final Date fromDate, final Date toDate, final VDMMap conferences, final VDMMap workshops) {

    for (Iterator iterator_26 = MapUtil.rng(Utils.copy(conferences)).iterator();
        iterator_26.hasNext();
        ) {
      Conference conf = (Conference) iterator_26.next();
      Boolean andResult_15 = false;

      if (Utils.equals(conf.Stage, Name)) {
        if (Date.DoOverlap(fromDate, toDate, conf.Start, conf.End)) {
          andResult_15 = true;
        }
      }

      if (andResult_15) {
        return false;
      }
    }
    for (Iterator iterator_27 = MapUtil.rng(Utils.copy(workshops)).iterator();
        iterator_27.hasNext();
        ) {
      Workshop workshop = (Workshop) iterator_27.next();
      Boolean andResult_16 = false;

      if (Utils.equals(workshop.Stage, Name)) {
        Boolean andResult_17 = false;

        if (workshop.Date.IsLaterThan(fromDate)) {
          if (workshop.Date.IsEarlierThan(toDate)) {
            andResult_17 = true;
          }
        }

        if (andResult_17) {
          andResult_16 = true;
        }
      }

      if (andResult_16) {
        return false;
      }
    }
    return true;
  }

  public Boolean IsFree(
      final Date date,
      final Time fromTime,
      final Time toTime,
      final VDMMap conferences,
      final VDMMap workshops) {

    for (Iterator iterator_28 = MapUtil.rng(Utils.copy(conferences)).iterator();
        iterator_28.hasNext();
        ) {
      Conference conf = (Conference) iterator_28.next();
      Boolean andResult_18 = false;

      if (Utils.equals(conf.Stage, Name)) {
        Boolean andResult_19 = false;

        if (date.IsLaterThan(conf.Start)) {
          if (date.IsEarlierThan(conf.End)) {
            andResult_19 = true;
          }
        }

        if (andResult_19) {
          andResult_18 = true;
        }
      }

      if (andResult_18) {
        return false;
      }
    }
    for (Iterator iterator_29 = MapUtil.rng(Utils.copy(workshops)).iterator();
        iterator_29.hasNext();
        ) {
      Workshop workshop = (Workshop) iterator_29.next();
      Boolean andResult_20 = false;

      if (Utils.equals(workshop.Stage, Name)) {
        Boolean andResult_21 = false;

        if (date.Equals(workshop.Date)) {
          if (Time.DoOverlap(fromTime, toTime, workshop.Start, workshop.End)) {
            andResult_21 = true;
          }
        }

        if (andResult_21) {
          andResult_20 = true;
        }
      }

      if (andResult_20) {
        return false;
      }
    }
    return true;
  }

  public Stage() {}

  public String toString() {

    return "Stage{"
        + "Venue := "
        + Utils.toString(Venue)
        + ", Name := "
        + Utils.toString(Name)
        + ", Start := "
        + Utils.toString(Start)
        + ", End := "
        + Utils.toString(End)
        + "}";
  }
}
