package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String Name;
  public Object Type;
  public Object State = websummit.quotes.UnregisteredQuote.getInstance();

  public void cg_init_User_1(final String un, final Object ut) {

    Name = un;
    Type = ut;
    return;
  }

  public User(final String un, final Object ut) {

    cg_init_User_1(un, ut);
  }

  public User() {}

  public String toString() {

    return "User{"
        + "Name := "
        + Utils.toString(Name)
        + ", Type := "
        + Utils.toString(Type)
        + ", State := "
        + Utils.toString(State)
        + "}";
  }
}
