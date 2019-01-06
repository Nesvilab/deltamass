package com.dmtavt.deltamass.utils;

import com.github.chhh.utils.StringUtils;
import java.util.Arrays;
import java.util.List;

public class OsUtils {

  private OsUtils() {
  }

  public static String osInfo() {
    String os = OsUtils.getOsName();
    os = StringUtils.isNullOrWhitespace(os) ? "?" : os;
    String arch = OsUtils.getSystemArch();
    arch = StringUtils.isNullOrWhitespace(arch) ? "?" : arch;
    return "System OS: " + os + ", Architecture: " + arch;
  }

  public static String javaInfo() {
    List<String> propNames = Arrays.asList(
        "java.version",
        "java.vm.name",
        "java.vm.vendor"
    );
    StringBuilder sb = new StringBuilder("Java Info: ");
    for (int i = 0; i < propNames.size(); i++) {
      String p = propNames.get(i);
      String val = System.getProperty(p);
      if (!StringUtils.isNullOrWhitespace(val)) {
        sb.append(val);
      }
      if (i < propNames.size() - 1) {
        sb.append(", ");
      }
    }

    return sb.toString();
  }

  public static boolean isWindows() {
    String osName = System.getProperty("os.name");
    if (osName == null) {
      return true; // just the default
    }
    return osName.toLowerCase().startsWith("win");
  }

  /**
   * OS name. E.g. 'Linux' or 'Windows XP'.
   *
   * @return
   */
  public static String getOsName() {
    String osName = System.getProperty("os.name");
    return osName;
  }

  /**
   * Tries to determine processor architecture from system properties.
   *
   * @return null if could not determine.
   */
  public static String getSystemArch() {

    try {
      if (isWindows()) {
        String wow64 = System.getenv("PROCESSOR_ARCHITEW6432");
        if (wow64 != null) {
          return wow64;
        }
        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        if (arch != null) {
          return arch;
        }

      } else {
        String osArch = System.getProperty("os.arch");
        if (osArch != null) {
          return osArch;
        }
      }
    } catch (IllegalArgumentException e) {
      // could not map os.arch or whatever to our enum values, not a biggie
    }

    // if all else fails, at least try to get the bitness of the JRE
    // which might still be null
    String osArch = System.getProperty("os.arch");
    return osArch;
  }
}
