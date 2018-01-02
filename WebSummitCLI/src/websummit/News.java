package websummit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class News {
  public String Author;
  public String Title;
  public String Content;
  public String Conference = null;

  public void cg_init_News_2(
      final String author, final String title, final String content, final String conf) {

    Author = author;
    Title = title;
    Content = content;
    Conference = conf;
    return;
  }

  public void cg_init_News_1(final String author, final String title, final String content) {

    Author = author;
    Title = title;
    Content = content;
    return;
  }

  public News(final String author, final String title, final String content) {

    cg_init_News_1(author, title, content);
  }

  public News(final String author, final String title, final String content, final String conf) {

    cg_init_News_2(author, title, content, conf);
  }

  public News() {}

  public String toString() {

    return "News{"
        + "Author := "
        + Utils.toString(Author)
        + ", Title := "
        + Utils.toString(Title)
        + ", Content := "
        + Utils.toString(Content)
        + ", Conference := "
        + Utils.toString(Conference)
        + "}";
  }
}
