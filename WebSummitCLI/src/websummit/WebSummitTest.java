package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WebSummitTest extends TestBase {
  private VDMMap TicketStock =
      MapUtil.map(
          new Maplet(websummit.quotes.CompanyQuote.getInstance(), 100L),
          new Maplet(websummit.quotes.AttendeeQuote.getInstance(), 1000L),
          new Maplet(websummit.quotes.VolunteerQuote.getInstance(), 200L));
  private VDMMap TicketPrice =
      MapUtil.map(
          new Maplet(websummit.quotes.CompanyQuote.getInstance(), 500L),
          new Maplet(websummit.quotes.AttendeeQuote.getInstance(), 250L));
  private WebSummit WebSummit = new WebSummit(Utils.copy(TicketStock), Utils.copy(TicketPrice));

  // DONE
  public void TestSellTicket() {

    WebSummit.SellTicket("João Silva", websummit.quotes.AttendeeQuote.getInstance());
    AssertTrue(Utils.equals(WebSummit.GetNumberOfAttendees(), 1L));
    AssertTrue(
        Utils.equals(
            WebSummit.GetAccountBalance(),
            ((Number) Utils.get(TicketPrice, websummit.quotes.AttendeeQuote.getInstance()))));
    WebSummit.SellTicket("Margarida Viterbo", websummit.quotes.CompanyQuote.getInstance());
    AssertTrue(Utils.equals(WebSummit.GetNumberOfAttendees(), 2L));
    AssertTrue(
        Utils.equals(
            WebSummit.GetAccountBalance(),
            ((Number) Utils.get(TicketPrice, websummit.quotes.AttendeeQuote.getInstance()))
                    .longValue()
                + ((Number) Utils.get(TicketPrice, websummit.quotes.CompanyQuote.getInstance()))
                    .longValue()));
  }

  // DONE
  public void TestRegisterAttendee() {

    WebSummit.RegisterAttendee("João Silva");
    AssertTrue(Utils.equals(WebSummit.GetNumberOfRegisteredAttendees(), 1L));
    AssertTrue(
        Utils.equals(
            ((User) Utils.get(WebSummit.Attendees, "João Silva")).State,
            websummit.quotes.RegisteredQuote.getInstance()));
  }

  // DONE
  public void TestAddToWaitingList() {

    WebSummit.AddToWaitingList("Joana Viterbo");
    AssertTrue(Utils.equals(WebSummit.WaitingList.size(), 1L));
  }

  // DONE
  public void TestAcceptVolunteer() {

    WebSummit.AcceptVolunteer("Joana Viterbo");
    AssertTrue(Utils.equals(WebSummit.GetNumberOfAttendees(), 3L));
  }

  // DONE
  public void TestScheduleVenue() {

    WebSummit.ScheduleVenue("FIL 1", new Date(4L, 11L, 2017L), new Date(9L, 11L, 2017L), 10000L);
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Venues).size(), 1L));
  }

  // DONE
  public void TestAddStage() {

    WebSummit.AddStage("FIL 1", "Main Stage");
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Stages).size(), 1L));
    AssertTrue(Utils.equals(((Stage) Utils.get(WebSummit.Stages, "Main Stage")).Venue, "FIL 1"));
  }

  // TODO
  public void TestGetVenueOfStage() {

    AssertTrue(Utils.equals(WebSummit.GetVenueOfStage("Main Stage").Name, "FIL 1"));
  }

  // DONE
  public void TestScheduleConference() {

    WebSummit.ScheduleConference(
        "Main Stage", "Future Societies", new Date(4L, 11L, 2017L), new Date(5L, 11L, 2017L));
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Conferences).size(), 1L));
    AssertTrue(
        Utils.equals(
            ((Conference) Utils.get(WebSummit.Conferences, "Future Societies")).Stage,
            "Main Stage"));
    WebSummit.ScheduleConference(
        "Main Stage", "Money Conf", new Date(6L, 11L, 2017L), new Date(7L, 11L, 2017L));
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Conferences).size(), 2L));
    AssertTrue(
        Utils.equals(
            ((Conference) Utils.get(WebSummit.Conferences, "Money Conf")).Stage, "Main Stage"));
  }

  // TODO
  public void TestGetConferencesOfDay() {

    AssertTrue(Utils.equals(WebSummit.GetConferencesOfDay(new Date(4L, 11L, 2017L)).size(), 1L));
    AssertTrue(Utils.equals(WebSummit.GetConferencesOfDay(new Date(6L, 11L, 2017L)).size(), 1L));
    AssertTrue(Utils.equals(WebSummit.GetConferencesOfDay(new Date(8L, 11L, 2017L)).size(), 0L));
  }

  // TODO
  public void TestGetConferenceStage() {

    AssertTrue(Utils.equals(WebSummit.GetConferenceStage("Future Societies").Name, "Main Stage"));
    AssertTrue(Utils.equals(WebSummit.GetConferenceStage("Money Conf").Name, "Main Stage"));
  }

  // TODO
  public void TestGetConferencesInStage() {

    AssertTrue(Utils.equals(WebSummit.GetConferencesInStage("Main Stage").size(), 2L));
  }

  // TODO
  public void TestGetConferencesInStageOfDay() {

    AssertTrue(
        Utils.equals(
            WebSummit.GetConferencesInStageOfDay("Main Stage", new Date(4L, 11L, 2017L)).size(),
            1L));
    AssertTrue(
        Utils.equals(
            WebSummit.GetConferencesInStageOfDay("Main Stage", new Date(8L, 11L, 2017L)).size(),
            0L));
  }

  // DONE
  public void TestScheduleTalk() {

    WebSummit.ScheduleTalk(
        "Future Societies",
        "The City of the Future",
        "João Silva",
        new Date(4L, 11L, 2017L),
        new Time(16L, 40L),
        new Time(17L, 0L));
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Talks).size(), 1L));
    AssertTrue(
        Utils.equals(
            ((Talk) Utils.get(WebSummit.Talks, "The City of the Future")).Conference,
            "Future Societies"));
    WebSummit.ScheduleTalk(
        "Future Societies",
        "Electric Cars: The Future",
        "Margarida Viterbo",
        new Date(4L, 11L, 2017L),
        new Time(17L, 5L),
        new Time(17L, 25L));
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Talks).size(), 2L));
    AssertTrue(
        Utils.equals(
            ((Talk) Utils.get(WebSummit.Talks, "Electric Cars: The Future")).Conference,
            "Future Societies"));
  }

  // TODO
  public void TestGetTalksOfDay() {

    AssertTrue(Utils.equals(WebSummit.GetTalksOfDay(new Date(4L, 11L, 2017L)).size(), 2L));
  }

  // TODO
  public void TestGetTalksAtTime() {

    AssertTrue(
        Utils.equals(
            WebSummit.GetTalksAtTime(new Date(4L, 11L, 2017L), new Time(17L, 10L)).size(), 1L));
    AssertTrue(
        Utils.equals(
            WebSummit.GetTalksAtTime(new Date(4L, 11L, 2017L), new Time(17L, 3L)).size(), 0L));
  }

  // TODO
  public void TestGetTalksOfSpeaker() {

    AssertTrue(Utils.equals(WebSummit.GetTalksOfSpeaker("João Silva").size(), 1L));
    AssertTrue(Utils.equals(WebSummit.GetTalksOfSpeaker("Margarida Viterbo").size(), 1L));
    AssertTrue(Utils.equals(WebSummit.GetTalksOfSpeaker("Joana Viterbo").size(), 0L));
  }

  // TODO
  public void TestGetConferenceTalksOfDay() {

    AssertTrue(
        Utils.equals(
            WebSummit.GetConferenceTalksOfDay("Future Societies", new Date(4L, 11L, 2017L)).size(),
            2L));
    AssertTrue(
        Utils.equals(
            WebSummit.GetConferenceTalksOfDay("Future Societies", new Date(5L, 11L, 2017L)).size(),
            0L));
  }

  // DONE
  public void TestScheduleWorkshop() {

    WebSummit.AddStage("FIL 1", "Secondary Stage");
    WebSummit.ScheduleWorkshop(
        "Managing DBs",
        "Databases",
        "Secondary Stage",
        "AWS",
        new Date(4L, 11L, 2017L),
        new Time(17L, 5L),
        new Time(17L, 35L),
        15L);
    AssertTrue(SetUtil.inSet("Managing DBs", MapUtil.dom(WebSummit.Workshops)));
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.Workshops).size(), 1L));
  }

  // TODO
  public void TestGetAllWorkshops() {

    VDMSet workshops = WebSummit.GetAllWorkshops();
    AssertTrue(Utils.equals(workshops.size(), 1L));
  }

  // TODO
  public void TestGetWorkshopsOfDay() {

    AssertTrue(Utils.equals(WebSummit.GetWorkshopsOfDay(new Date(4L, 11L, 2017L)).size(), 1L));
    AssertTrue(Utils.equals(WebSummit.GetWorkshopsOfDay(new Date(5L, 11L, 2018L)).size(), 0L));
  }

  // TODO
  public void TestGetWorkshopsAtTime() {

    AssertTrue(Utils.equals(WebSummit.GetWorkshopsAtTime(new Time(17L, 5L)).size(), 1L));
    AssertTrue(Utils.equals(WebSummit.GetWorkshopsAtTime(new Time(6L, 0L)).size(), 0L));
  }

  // DONE
  public void TestRegisterAttendeeToWorkshop() {

    WebSummit.RegisterAttendee("Margarida Viterbo");
    AssertTrue(
        Utils.equals(
            ((User) Utils.get(WebSummit.Attendees, "Margarida Viterbo")).State,
            websummit.quotes.RegisteredQuote.getInstance()));
    WebSummit.RegisterAttendeeToWorkshop("Margarida Viterbo", "Managing DBs");
    AssertTrue(
        Utils.equals(
            ((Workshop) Utils.get(WebSummit.Workshops, "Managing DBs")).Attendees.size(), 1L));
    AssertTrue(
        SetUtil.inSet(
            "Margarida Viterbo",
            ((Workshop) Utils.get(WebSummit.Workshops, "Managing DBs")).Attendees));
  }

  // TODO
  public void TestGetAttendeesOfWorkshop() {

    AssertTrue(Utils.equals(WebSummit.GetAttendeesOfWorkshop("Managing DBs").size(), 1L));
  }

  // TODO
  public void TestGetStageOfWorkshop() {

    AssertTrue(Utils.equals(WebSummit.GetStageOfWorkshop("Managing DBs").Name, "Secondary Stage"));
  }

  // TODO
  public void TestGetSubjectOfWorkshop() {

    AssertTrue(Utils.equals(WebSummit.GetSubjectOfWorkshop("Managing DBs"), "Databases"));
  }

  // TODO
  public void TestGetCompanyOfWorkshop() {

    AssertTrue(Utils.equals(WebSummit.GetCompanyOfWorkshop("Managing DBs"), "AWS"));
  }

  // TODO
  public void TestGetWorkshopsInStage() {

    AssertTrue(Utils.equals(WebSummit.GetWorkshopsInStage("Secondary Stage").size(), 1L));
  }

  // TODO
  public void TestGetWorkshopsInStageOfDay() {

    AssertTrue(
        Utils.equals(
            WebSummit.GetWorkshopsInStageOfDay("Secondary Stage", new Date(4L, 11L, 2017L)).size(),
            1L));
    AssertTrue(
        Utils.equals(
            WebSummit.GetWorkshopsInStageOfDay("Secondary Stage", new Date(5L, 11L, 2017L)).size(),
            0L));
  }

  // TODO
  public void TestPublishNewsArcticle() {

    WebSummit.PublishNewsArcticle("João Silva", "Title", "Content");
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.News).size(), 1L));
    AssertTrue(Utils.equals(((News) Utils.get(WebSummit.News, "Title")).Content, "Content"));
  }

  // TODO
  public void TestPublishNewsArcticleAboutConf() {

    WebSummit.PublishNewsArcticleAboutConf(
        "João Silva", "Title About Money Conf", "Content", "Money Conf");
    AssertTrue(Utils.equals(MapUtil.dom(WebSummit.News).size(), 2L));
    AssertTrue(
        Utils.equals(
            ((News) Utils.get(WebSummit.News, "Title About Money Conf")).Conference, "Money Conf"));
    AssertTrue(
        Utils.equals(
            ((News) Utils.get(WebSummit.News, "Title About Money Conf")).Content, "Content"));
  }

  // TODO
  public void TestGetAllNews() {

    AssertTrue(Utils.equals(WebSummit.GetAllNews().size(), 2L));
  }

  // TODO
  public void TestGetNewsAboutConf() {

    AssertTrue(Utils.equals(WebSummit.GetNewsAboutConf("Money Conf").size(), 1L));
  }

  // TODO
  public void TestGetNewsByAuthor() {

    AssertTrue(Utils.equals(WebSummit.GetNewsByAuthor("João Silva").size(), 2L));
  }

  public void Run() {

    TestSellTicket();
    TestRegisterAttendee();
    TestAddToWaitingList();
    TestAcceptVolunteer();
    TestScheduleVenue();
    TestAddStage();
    TestGetVenueOfStage();
    TestScheduleConference();
    TestGetConferencesOfDay();
    TestGetConferenceStage();
    TestGetConferencesInStage();
    TestGetConferencesInStageOfDay();
    TestScheduleTalk();
    TestGetTalksOfDay();
    TestGetTalksAtTime();
    TestGetTalksOfSpeaker();
    TestGetConferenceTalksOfDay();
    TestScheduleWorkshop();
    TestGetAllWorkshops();
    TestGetWorkshopsOfDay();
    TestGetWorkshopsAtTime();
    TestRegisterAttendeeToWorkshop();
    TestGetAttendeesOfWorkshop();
    TestGetStageOfWorkshop();
    TestGetSubjectOfWorkshop();
    TestGetCompanyOfWorkshop();
    TestGetWorkshopsInStage();
    TestGetWorkshopsInStageOfDay();
    TestPublishNewsArcticle();
    TestPublishNewsArcticleAboutConf();
    TestGetAllNews();
    TestGetNewsAboutConf();
    TestGetNewsByAuthor();
  }

  public void TestScheduleVenueBadDates() {

    WebSummit.ScheduleVenue(
        "MEO Arena", new Date(4L, 11L, 2017L), new Date(3L, 11L, 2017L), 10000L);
  }

  public void TestScheduleConferenceBadDates() {

    WebSummit.ScheduleVenue(
        "MEO Arena", new Date(4L, 11L, 2017L), new Date(9L, 11L, 2017L), 10000L);
    WebSummit.AddStage("MEO Arena", "Main Stage");
    WebSummit.ScheduleConference(
        "Main Stage", "Panda Conf", new Date(4L, 11L, 2016L), new Date(5L, 11L, 2016L));
  }

  public void TestScheduleOverlappingConferences() {

    WebSummit.ScheduleVenue(
        "MEO Arena", new Date(4L, 11L, 2017L), new Date(9L, 11L, 2017L), 10000L);
    WebSummit.AddStage("MEO Arena", "Main Stage");
    WebSummit.ScheduleConference(
        "Main Stage", "Future Societies", new Date(4L, 11L, 2017L), new Date(5L, 11L, 2017L));
    WebSummit.ScheduleConference(
        "Main Stage", "Panda Conf", new Date(5L, 11L, 2017L), new Date(6L, 11L, 2017L));
  }

  public void TestScheduleOverlappingTalks() {

    WebSummit.ScheduleVenue(
        "MEO Arena", new Date(4L, 11L, 2017L), new Date(9L, 11L, 2017L), 10000L);
    WebSummit.AddStage("MEO Arena", "Main Stage");
    WebSummit.ScheduleConference(
        "Main Stage", "Future Societies", new Date(4L, 11L, 2017L), new Date(5L, 11L, 2017L));
    AssertTrue(
        Utils.equals(
            ((Conference) Utils.get(WebSummit.Conferences, "Future Societies")).Stage,
            "Main Stage"));
    WebSummit.SellTicket("João Silva", websummit.quotes.AttendeeQuote.getInstance());
    WebSummit.SellTicket("Margarida Viterbo", websummit.quotes.AttendeeQuote.getInstance());
    WebSummit.ScheduleTalk(
        "Future Societies",
        "The City of the Future",
        "João Silva",
        new Date(4L, 11L, 2017L),
        new Time(16L, 40L),
        new Time(17L, 0L));
    WebSummit.ScheduleTalk(
        "Future Societies",
        "Electric Cars: The Future",
        "Margarida Viterbo",
        new Date(4L, 11L, 2017L),
        new Time(16L, 55L),
        new Time(17L, 15L));
  }

  public WebSummitTest() {}

  public String toString() {

    return "WebSummitTest{"
        + "TicketStock := "
        + Utils.toString(TicketStock)
        + ", TicketPrice := "
        + Utils.toString(TicketPrice)
        + ", WebSummit := "
        + Utils.toString(WebSummit)
        + "}";
  }
}
