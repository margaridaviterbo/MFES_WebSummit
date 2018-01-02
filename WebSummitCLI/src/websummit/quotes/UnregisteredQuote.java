package websummit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UnregisteredQuote {
  private static int hc = 0;
  private static UnregisteredQuote instance = null;

  public UnregisteredQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static UnregisteredQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new UnregisteredQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof UnregisteredQuote;
  }

  public String toString() {

    return "<Unregistered>";
  }
}
