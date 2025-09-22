Cloudstream Extension - server8022
=================================
Package: com.cloudstream.server8022

What this is
------------
A ready-to-upload Cloudstream-style extension skeleton with a simple scraper
for http://84.54.185.70:8022/. Files are provided as a ZIP. The extension
contains Gradle config, Android manifest, and a Kotlin source class that
demonstrates HTTP requests using OkHttp and HTML parsing using Jsoup.

How to use
----------
1. Unzip the archive.
2. Open the folder in Android Studio or IntelliJ with Kotlin support.
3. Update dependencies or Cloudstream API versions as required by your target Cloudstream build.
4. Build and install the resulting .apk or place the compiled extension in Cloudstream's extensions folder.

Notes
-----
- The scraper is intentionally simple and generic. You may need to adapt CSS selectors
  depending on the real HTML structure of the target site.
- No external network calls are made when creating this archive. The Kotlin code uses OkHttp and Jsoup,
  which you should include in your build if your Cloudstream host requires them.
- This project is provided "as-is" for educational/automation convenience.
