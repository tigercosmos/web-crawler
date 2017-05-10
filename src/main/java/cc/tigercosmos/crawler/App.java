package cc.tigercosmos.crawler;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  
  private static final Logger log = LoggerFactory.getLogger(App.class);
  
  public static void main (String[] args) throws IOException {
    log.trace("Start running..");
    
    // TODO add something here
    Crawler crawler = new Crawler();
  }
  
}