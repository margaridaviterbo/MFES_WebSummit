package websummit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class VolunteerQuote {
  private static int hc = 0;
  private static VolunteerQuote instance = null;

  public VolunteerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static VolunteerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new VolunteerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof VolunteerQuote;
  }

  public String toString() {

    return "<Volunteer>";
  }
}
