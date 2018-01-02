package websummit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RegisteredQuote {
  private static int hc = 0;
  private static RegisteredQuote instance = null;

  public RegisteredQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static RegisteredQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new RegisteredQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof RegisteredQuote;
  }

  public String toString() {

    return "<Registered>";
  }
}
