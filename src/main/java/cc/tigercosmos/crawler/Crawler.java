package cc.tigercosmos.crawler;

import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.google.gson.Gson;

/**
 * @author tigercosmos
 */
public class Crawler {
  private static final Logger log = LoggerFactory.getLogger(App.class);

  private static final Gson GSON = new Gson();

  /**
   * Initiate the crawler
   */
  public Crawler() {
    initiateFeeder();
  }

  public void initiateFeeder() {
    // Initiate some works
    log.trace("Initiate the setting resources...");
    // TODO add something here
  }

  /**
   * Parse the content from XML to object
   * 
   * @param url
   */
  private void xmlParser(String url) {

    log.trace("Running xmlParser() in '{}'.", url);
    // Read the XML file
    try (InputStream in = new URL(url).openStream()) {

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(in);

      // optional, but recommended
      // read this - http://stackoverflow.com/questions/13786607/
      doc.getDocumentElement().normalize();

      // get each item in XML

      // TODO Change this part
      // NodeList nList = doc.getElementsByTagName("item");
      //
      // for (int temp = 0; temp < nList.getLength(); temp++) {
      //
      // Node nNode = nList.item(temp);
      // if (nNode.getNodeType() == Node.ELEMENT_NODE) {
      //
      // Element eElement = (Element) nNode;
      //
      // try {
      // String titleOfElement = eElement.getElementsByTagName("title")
      // .item(0).getTextContent();
      // String urlLinkOfElement =
      // eElement.getElementsByTagName("link").item(0).getTextContent();
      //
      // log.trace("title: '{}'.", titleOfElement);
      // log.trace("url: '{}'.", urlLinkOfElement);
      //
      // } catch (Exception e) {
      // log.warn("Get Title & Url Failed.", e);
      // }
      //
      // }
      // }

    } catch (Exception e) {
      log.warn("Read XML Failed.", e);
    }
  }


  /**
   * Parse the content of the certain URL to object
   * 
   * @param url
   * @param selstedPattern: use this pattern to select the content we want
   */
  private void htmlParser(String url, String selectedPattern) {

    log.trace("Runing htmlParser() with '{}' pattern in '{}'", selectedPattern,
        url);

    org.jsoup.nodes.Document doc = null;
    // Parse HTML content from URL.
    try {

      // Solve Read Time Out
      doc = Jsoup.connect(url).timeout(10 * 1000).get();

      try {
        // find the part of something in the HTML

        // TODO Change this part
        // Elements newsHeadlines = doc.select(selectedPattern);
        // for (org.jsoup.nodes.Element lines : newsHeadlines) {
        //
        // try {
        // String urlOfElement =
        // lines.getElementsByTag("a").get(0).attr("href");
        //
        //
        // String titleOfElement =
        // lines.getElementsByTag("a").get(0).html();
        //
        // log.trace("title: '{}'.", titleOfElement);
        // log.trace("url: '{}'.", urlOfElement);
        //
        // } catch (Exception e) {
        // log.warn("Get Title & Url Failed.", e);
        // }
        //
        // }

      } catch (NullPointerException e) {
        log.warn("Content of {} is NULL", url, e);
      }

    } catch (IOException e) {
      log.warn("Cannot Get Content of {}", url, e);
    }
  }

  /**
   * Access and parse JSON through API.
   */
  private JsonObjectClass parseJson(String url) {
    try {
      // Ask Jsoup not to parse the response body.
      Response response = Jsoup.connect(url).ignoreContentType(true).execute();

      if (response.statusCode() == 200) {
        log.trace("Try to parse Json into object: {}", response.body());
        return GSON.fromJson(response.body(), JsonObjectClass.class);
      } else {
        log.warn("Cannot access URL '{}'.", url);
        return null;
      }

    } catch (Throwable t) {
      log.warn("Error occurred when accessing URL '{}'.", url, t);
      return null;
    }
  }

  private class JsonObjectClass {
    // For the JSON
  }

}
