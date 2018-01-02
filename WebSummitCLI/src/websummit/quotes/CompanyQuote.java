package websummit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CompanyQuote {
  private static int hc = 0;
  private static CompanyQuote instance = null;

  public CompanyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CompanyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CompanyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CompanyQuote;
  }

  public String toString() {

    return "<Company>";
  }
}
