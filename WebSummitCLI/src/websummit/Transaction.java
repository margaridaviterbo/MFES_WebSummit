package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Transaction {
  public Number Amount;

  public void cg_init_Transaction_1(final Number amount) {

    Amount = amount;
    return;
  }

  public Transaction(final Number amount) {

    cg_init_Transaction_1(amount);
  }

  public Transaction() {}

  public String toString() {

    return "Transaction{" + "Amount := " + Utils.toString(Amount) + "}";
  }
}
