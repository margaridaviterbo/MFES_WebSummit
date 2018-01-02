package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Workshop {
  public String Name;
  public String Subject;
  public String Stage;
  public String Company;
  public Date Date;
  public Time Start;
  public Time End;
  public Number Vacancies;
  public VDMSet Attendees = SetUtil.set();

  public void cg_init_Workshop_1(
      final String name,
      final String subject,
      final String stage,
      final String company,
      final Date date,
      final Time startTime,
      final Time endTime,
      final Number vacancies) {

    Name = name;
    Subject = subject;
    Stage = stage;
    Company = company;
    Date = date;
    Start = startTime;
    End = endTime;
    Vacancies = vacancies;
    return;
  }

  public Workshop(
      final String name,
      final String subject,
      final String stage,
      final String company,
      final Date date,
      final Time startTime,
      final Time endTime,
      final Number vacancies) {

    cg_init_Workshop_1(name, subject, stage, company, date, startTime, endTime, vacancies);
  }

  public Workshop() {}

  public String toString() {

    return "Workshop{"
        + "Name := "
        + Utils.toString(Name)
        + ", Subject := "
        + Utils.toString(Subject)
        + ", Stage := "
        + Utils.toString(Stage)
        + ", Company := "
        + Utils.toString(Company)
        + ", Date := "
        + Utils.toString(Date)
        + ", Start := "
        + Utils.toString(Start)
        + ", End := "
        + Utils.toString(End)
        + ", Vacancies := "
        + Utils.toString(Vacancies)
        + ", Attendees := "
        + Utils.toString(Attendees)
        + "}";
  }
}
