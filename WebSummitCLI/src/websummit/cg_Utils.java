package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class cg_Utils {
  public Number SumSeq(final VDMSeq s) {

    if (Utils.empty(s)) {
      return 0L;
    }

    return ((Number) s.get(0)).longValue() + SumSeq(SeqUtil.tail(Utils.copy(s))).longValue();
  }

  public cg_Utils() {}

  public String toString() {

    return "cg_Utils{}";
  }
}
