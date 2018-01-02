package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Talk {
  public String Title;
  public String Conference;
  public String Speaker;
  public Date Date;
  public Time Start;
  public Time End;

  public void cg_init_Talk_1(
      final String title,
      final String conf,
      final String speaker,
      final Date date,
      final Time startTime,
      final Time endTime) {

    Title = title;
    Conference = conf;
    Speaker = speaker;
    Date = date;
    Start = startTime;
    End = endTime;
    return;
  }

  public Talk(
      final String title,
      final String conf,
      final String speaker,
      final Date date,
      final Time startTime,
      final Time endTime) {

    cg_init_Talk_1(title, conf, speaker, date, startTime, endTime);
  }

  public Talk() {}

  public String toString() {

    return "Talk{"
        + "Title := "
        + Utils.toString(Title)
        + ", Conference := "
        + Utils.toString(Conference)
        + ", Speaker := "
        + Utils.toString(Speaker)
        + ", Date := "
        + Utils.toString(Date)
        + ", Start := "
        + Utils.toString(Start)
        + ", End := "
        + Utils.toString(End)
        + "}";
  }
}
