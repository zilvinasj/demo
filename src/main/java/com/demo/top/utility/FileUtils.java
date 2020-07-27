package com.demo.top.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

public class FileUtils {

  private FileUtils() {}

  public static String resourceAsString(Resource resource) {
    try(InputStream is = resource.getInputStream()) {
      return IOUtils.toString(is, Charset.defaultCharset());
    } catch (IOException ex) {
      throw new RuntimeException("Resource not found");
    }
  }

}
