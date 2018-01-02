package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WebSummit {
  private VDMSeq Account = SeqUtil.seq();
  public VDMSet WaitingList = SetUtil.set();
  public VDMMap Attendees = MapUtil.map();
  private VDMMap TicketStock;
  private VDMMap TicketPrice;
  public VDMMap Conferences = MapUtil.map();
  public VDMMap Workshops = MapUtil.map();
  public VDMMap Venues = MapUtil.map();
  public VDMMap Stages = MapUtil.map();
  public VDMMap Talks = MapUtil.map();
  public VDMMap News = MapUtil.map();

  public void cg_init_WebSummit_1(final VDMMap tickets, final VDMMap price) {

    TicketStock = Utils.copy(tickets);
    TicketPrice = Utils.copy(price);
    return;
  }

  public WebSummit(final VDMMap tickets, final VDMMap price) {

    cg_init_WebSummit_1(Utils.copy(tickets), Utils.copy(price));
  }

  public void SellTicket(final String name, final Object ticket) {

    Utils.mapSeqUpdate(
        TicketStock, ticket, ((Number) Utils.get(TicketStock, ticket)).longValue() - 1L);
    Account =
        SeqUtil.conc(
            SeqUtil.seq(new Transaction(((Number) Utils.get(TicketPrice, ticket)))),
            Utils.copy(Account));
    Attendees =
        MapUtil.override(
            Utils.copy(Attendees),
            MapUtil.map(new Maplet(name, new User(name, ((Object) ticket)))));
  }

  public Number GetAccountBalance() {

    VDMSeq seqCompResult_1 = SeqUtil.seq();
    VDMSeq set_1 = Utils.copy(Account);
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Transaction t = ((Transaction) iterator_1.next());
      seqCompResult_1.add(t.Amount);
    }
    return new cg_Utils().SumSeq(Utils.copy(seqCompResult_1));
  }

  public void RegisterAttendee(final String name) {

    ((User) Utils.get(Attendees, name)).State = websummit.quotes.RegisteredQuote.getInstance();
  }

  public void AddToWaitingList(final String name) {

    WaitingList = SetUtil.union(Utils.copy(WaitingList), SetUtil.set(name));
  }

  public void AcceptVolunteer(final String name) {

    Attendees =
        MapUtil.override(
            Utils.copy(Attendees),
            MapUtil.map(
                new Maplet(name, new User(name, websummit.quotes.VolunteerQuote.getInstance()))));
  }

  public Number GetNumberOfAttendees() {

    return MapUtil.dom(Utils.copy(Attendees)).size();
  }

  public Number GetNumberOfRegisteredAttendees() {

    VDMSet setCompResult_2 = SetUtil.set();
    VDMSet set_3 = MapUtil.rng(Utils.copy(Attendees));
    for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext(); ) {
      User user = ((User) iterator_3.next());
      if (Utils.equals(user.State, websummit.quotes.RegisteredQuote.getInstance())) {
        setCompResult_2.add(user);
      }
    }
    return setCompResult_2.size();
  }

  public void ScheduleVenue(
      final String name, final Date startDate, final Date endDate, final Number rent) {

    Account = SeqUtil.conc(SeqUtil.seq(new Transaction(-rent.longValue())), Utils.copy(Account));
    Venues =
        MapUtil.override(
            Utils.copy(Venues), MapUtil.map(new Maplet(name, new Venue(name, startDate, endDate))));
  }

  public void AddStage(final String venue, final String name) {

    Stages =
        MapUtil.override(
            Utils.copy(Stages),
            MapUtil.map(
                new Maplet(
                    name,
                    new Stage(
                        venue,
                        name,
                        ((Venue) Utils.get(Venues, venue)).Start,
                        ((Venue) Utils.get(Venues, venue)).End))));
  }

  public void ScheduleConference(
      final String stage, final String conf, final Date startDate, final Date endDate) {

    Conferences =
        MapUtil.override(
            Utils.copy(Conferences),
            MapUtil.map(new Maplet(conf, new Conference(stage, conf, startDate, endDate))));
  }

  public void ScheduleTalk(
      final String conf,
      final String title,
      final String speaker,
      final Date date,
      final Time startTime,
      final Time endTime) {

    Talks =
        MapUtil.override(
            Utils.copy(Talks),
            MapUtil.map(
                new Maplet(title, new Talk(title, conf, speaker, date, startTime, endTime))));
  }

  public VDMSet GetTalksOfDay(final Date date) {

    VDMSet setCompResult_3 = SetUtil.set();
    VDMSet set_4 = MapUtil.rng(Utils.copy(Talks));
    for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext(); ) {
      Talk talk = ((Talk) iterator_4.next());
      if (talk.Date.Equals(date)) {
        setCompResult_3.add(talk);
      }
    }
    return Utils.copy(setCompResult_3);
  }

  public VDMSet GetTalksAtTime(final Date date, final Time time) {

    VDMSet setCompResult_4 = SetUtil.set();
    VDMSet set_5 = MapUtil.rng(Utils.copy(Talks));
    for (Iterator iterator_5 = set_5.iterator(); iterator_5.hasNext(); ) {
      Talk talk = ((Talk) iterator_5.next());
      Boolean andResult_43 = false;

      if (talk.Date.Equals(date)) {
        Boolean andResult_44 = false;

        if (time.IsLaterThan(talk.Start)) {
          if (time.IsEarlierThan(talk.End)) {
            andResult_44 = true;
          }
        }

        if (andResult_44) {
          andResult_43 = true;
        }
      }

      if (andResult_43) {
        setCompResult_4.add(talk);
      }
    }
    return Utils.copy(setCompResult_4);
  }

  public VDMSet GetTalksOfSpeaker(final String speaker) {

    VDMSet setCompResult_5 = SetUtil.set();
    VDMSet set_6 = MapUtil.rng(Utils.copy(Talks));
    for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext(); ) {
      Talk talk = ((Talk) iterator_6.next());
      if (Utils.equals(talk.Speaker, speaker)) {
        setCompResult_5.add(talk);
      }
    }
    return Utils.copy(setCompResult_5);
  }

  public VDMSet GetConferenceTalksOfDay(final String conference, final Date date) {

    VDMSet setCompResult_6 = SetUtil.set();
    VDMSet set_7 = MapUtil.rng(Utils.copy(Talks));
    for (Iterator iterator_7 = set_7.iterator(); iterator_7.hasNext(); ) {
      Talk talk = ((Talk) iterator_7.next());
      Boolean andResult_45 = false;

      if (Utils.equals(talk.Conference, conference)) {
        if (talk.Date.Equals(date)) {
          andResult_45 = true;
        }
      }

      if (andResult_45) {
        setCompResult_6.add(talk);
      }
    }
    return Utils.copy(setCompResult_6);
  }

  public VDMSet GetConferencesOfDay(final Date date) {

    VDMSet setCompResult_7 = SetUtil.set();
    VDMSet set_8 = MapUtil.rng(Utils.copy(Conferences));
    for (Iterator iterator_8 = set_8.iterator(); iterator_8.hasNext(); ) {
      Conference conf = ((Conference) iterator_8.next());
      Boolean andResult_46 = false;

      if (date.IsLaterThan(conf.Start)) {
        if (date.IsEarlierThan(conf.End)) {
          andResult_46 = true;
        }
      }

      if (andResult_46) {
        setCompResult_7.add(conf);
      }
    }
    return Utils.copy(setCompResult_7);
  }

  public Stage GetConferenceStage(final String conf) {

    return ((Stage) Utils.get(Stages, ((Conference) Utils.get(Conferences, conf)).Stage));
  }

  public void ScheduleWorkshop(
      final String name,
      final String subject,
      final String stage,
      final String company,
      final Date date,
      final Time startTime,
      final Time endTime,
      final Number vacancies) {

    Workshops =
        MapUtil.override(
            Utils.copy(Workshops),
            MapUtil.map(
                new Maplet(
                    name,
                    new Workshop(
                        name, subject, stage, company, date, startTime, endTime, vacancies))));
  }

  public void RegisterAttendeeToWorkshop(final String user, final String workshop) {

    ((Workshop) Utils.get(Workshops, workshop)).Attendees =
        SetUtil.union(((Workshop) Utils.get(Workshops, workshop)).Attendees, SetUtil.set(user));
  }

  public VDMSet GetAllWorkshops() {

    return MapUtil.rng(Utils.copy(Workshops));
  }

  public VDMSet GetWorkshopsOfDay(final Date date) {

    VDMSet setCompResult_9 = SetUtil.set();
    VDMSet set_10 = MapUtil.dom(Utils.copy(Workshops));
    for (Iterator iterator_10 = set_10.iterator(); iterator_10.hasNext(); ) {
      String workshop = ((String) iterator_10.next());
      if (date.Equals(((Workshop) Utils.get(Workshops, workshop)).Date)) {
        setCompResult_9.add(workshop);
      }
    }
    return Utils.copy(setCompResult_9);
  }

  public VDMSet GetWorkshopsAtTime(final Time time) {

    VDMSet setCompResult_11 = SetUtil.set();
    VDMSet set_12 = MapUtil.dom(Utils.copy(Workshops));
    for (Iterator iterator_12 = set_12.iterator(); iterator_12.hasNext(); ) {
      String workshop = ((String) iterator_12.next());
      Boolean andResult_60 = false;

      if (time.IsLaterThan(((Workshop) Utils.get(Workshops, workshop)).Start)) {
        if (time.IsEarlierThan(((Workshop) Utils.get(Workshops, workshop)).End)) {
          andResult_60 = true;
        }
      }

      if (andResult_60) {
        setCompResult_11.add(workshop);
      }
    }
    return Utils.copy(setCompResult_11);
  }

  public VDMSet GetAttendeesOfWorkshop(final String workshop) {

    return ((Workshop) Utils.get(Workshops, workshop)).Attendees;
  }

  public Stage GetStageOfWorkshop(final String workshop) {

    return ((Stage) Utils.get(Stages, ((Workshop) Utils.get(Workshops, workshop)).Stage));
  }

  public String GetSubjectOfWorkshop(final String workshop) {

    return ((Workshop) Utils.get(Workshops, workshop)).Subject;
  }

  public String GetCompanyOfWorkshop(final String workshop) {

    return ((Workshop) Utils.get(Workshops, workshop)).Company;
  }

  public void PublishNewsArcticle(final String author, final String title, final String content) {

    News =
        MapUtil.override(
            Utils.copy(News), MapUtil.map(new Maplet(title, new News(author, title, content))));
  }

  public void PublishNewsArcticleAboutConf(
      final String author, final String title, final String content, final String conf) {

    News =
        MapUtil.override(
            Utils.copy(News),
            MapUtil.map(new Maplet(title, new News(author, title, content, conf))));
  }

  public VDMSet GetAllNews() {

    return MapUtil.rng(Utils.copy(News));
  }

  public VDMSet GetNewsAboutConf(final String conf) {

    VDMSet setCompResult_13 = SetUtil.set();
    VDMSet set_14 = MapUtil.rng(Utils.copy(News));
    for (Iterator iterator_14 = set_14.iterator(); iterator_14.hasNext(); ) {
      News news = ((News) iterator_14.next());
      if (Utils.equals(news.Conference, conf)) {
        setCompResult_13.add(news);
      }
    }
    return Utils.copy(setCompResult_13);
  }

  public VDMSet GetNewsByAuthor(final String author) {

    VDMSet setCompResult_15 = SetUtil.set();
    VDMSet set_16 = MapUtil.rng(Utils.copy(News));
    for (Iterator iterator_16 = set_16.iterator(); iterator_16.hasNext(); ) {
      News news = ((News) iterator_16.next());
      if (Utils.equals(news.Author, author)) {
        setCompResult_15.add(news);
      }
    }
    return Utils.copy(setCompResult_15);
  }

  public VDMSet GetWorkshopsInStage(final String stage) {

    VDMSet setCompResult_17 = SetUtil.set();
    VDMSet set_18 = MapUtil.rng(Utils.copy(Workshops));
    for (Iterator iterator_18 = set_18.iterator(); iterator_18.hasNext(); ) {
      Workshop workshop = ((Workshop) iterator_18.next());
      if (Utils.equals(workshop.Stage, stage)) {
        setCompResult_17.add(workshop);
      }
    }
    return Utils.copy(setCompResult_17);
  }

  public VDMSet GetConferencesInStage(final String stage) {

    VDMSet setCompResult_19 = SetUtil.set();
    VDMSet set_20 = MapUtil.rng(Utils.copy(Conferences));
    for (Iterator iterator_20 = set_20.iterator(); iterator_20.hasNext(); ) {
      Conference conf = ((Conference) iterator_20.next());
      if (Utils.equals(conf.Stage, stage)) {
        setCompResult_19.add(conf);
      }
    }
    return Utils.copy(setCompResult_19);
  }

  public VDMSet GetWorkshopsInStageOfDay(final String stage, final Date date) {

    VDMSet setCompResult_21 = SetUtil.set();
    VDMSet set_22 = MapUtil.rng(Utils.copy(Workshops));
    for (Iterator iterator_22 = set_22.iterator(); iterator_22.hasNext(); ) {
      Workshop workshop = ((Workshop) iterator_22.next());
      Boolean andResult_77 = false;

      if (Utils.equals(workshop.Stage, stage)) {
        if (workshop.Date.Equals(date)) {
          andResult_77 = true;
        }
      }

      if (andResult_77) {
        setCompResult_21.add(workshop);
      }
    }
    return Utils.copy(setCompResult_21);
  }

  public VDMSet GetConferencesInStageOfDay(final String stage, final Date date) {

    VDMSet setCompResult_23 = SetUtil.set();
    VDMSet set_24 = MapUtil.rng(Utils.copy(Conferences));
    for (Iterator iterator_24 = set_24.iterator(); iterator_24.hasNext(); ) {
      Conference conf = ((Conference) iterator_24.next());
      Boolean andResult_81 = false;

      if (Utils.equals(conf.Stage, stage)) {
        Boolean andResult_82 = false;

        if (date.IsLaterThan(conf.Start)) {
          if (date.IsEarlierThan(conf.End)) {
            andResult_82 = true;
          }
        }

        if (andResult_82) {
          andResult_81 = true;
        }
      }

      if (andResult_81) {
        setCompResult_23.add(conf);
      }
    }
    return Utils.copy(setCompResult_23);
  }

  public Venue GetVenueOfStage(final String stage) {

    return ((Venue) Utils.get(Venues, ((Stage) Utils.get(Stages, stage)).Venue));
  }

  public WebSummit() {}

  public String toString() {

    return "WebSummit{"
        + "Account := "
        + Utils.toString(Account)
        + ", WaitingList := "
        + Utils.toString(WaitingList)
        + ", Attendees := "
        + Utils.toString(Attendees)
        + ", TicketStock := "
        + Utils.toString(TicketStock)
        + ", TicketPrice := "
        + Utils.toString(TicketPrice)
        + ", Conferences := "
        + Utils.toString(Conferences)
        + ", Workshops := "
        + Utils.toString(Workshops)
        + ", Venues := "
        + Utils.toString(Venues)
        + ", Stages := "
        + Utils.toString(Stages)
        + ", Talks := "
        + Utils.toString(Talks)
        + ", News := "
        + Utils.toString(News)
        + "}";
  }
}
