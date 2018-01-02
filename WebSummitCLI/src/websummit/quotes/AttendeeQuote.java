package websummit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AttendeeQuote {
  private static int hc = 0;
  private static AttendeeQuote instance = null;

  public AttendeeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AttendeeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AttendeeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AttendeeQuote;
  }

  public String toString() {

    return "<Attendee>";
  }
}
