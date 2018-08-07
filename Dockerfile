FROM ubuntu:16.04

MAINTAINER Jason Campos <jsncam07@gmail.com>

RUN apt-get update
RUN apt-get install -y \
openjdk-8-jdk \
maven \
build-essential \
chrpath \
libssl-dev \
libxft-dev \
libfreetype6-dev \
libfreetype6 \
libfontconfig1-dev \
libfontconfig1 \
wget \
libgconf-2-4 \
libhyphen-dev \
libxslt-dev \
python-pip \
zip \
unzip \
tzdata \
screen \
less \
vim



## Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update -qqy \
	&& apt-get -qqy install google-chrome-stable \
	&& rm /etc/apt/sources.list.d/google-chrome.list \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome


## Selenium and chrome driver
RUN cd /root && wget -N http://selenium-release.storage.googleapis.com/3.9/selenium-server-standalone-3.9.0.jar
RUN mv -f ~/selenium-server-standalone-3.9.0.jar /usr/local/bin/selenium-server-standalone-3.9.0.jar
RUN chmod 0755 /usr/local/bin/selenium-server-standalone-3.9.0.jar
##RUN wget -N http://chromedriver.storage.googleapis.com/2.40/chromedriver_linux64.zip -P ~/
##RUN unzip ~/chromedriver_linux64.zip -d ~/
##RUN rm ~/chromedriver_linux64.zip
##RUN mv -f ~/chromedriver /usr/local/bin/chromedriver
##RUN chmod 0755 /usr/local/bin/chromedriver

#=========
# Firefox
#=========
ARG FIREFOX_VERSION=latest
RUN FIREFOX_DOWNLOAD_URL=$(if [ $FIREFOX_VERSION = "latest" ] || [ $FIREFOX_VERSION = "nightly-latest" ] || [ $FIREFOX_VERSION = "devedition-latest" ]; then echo "https://download.mozilla.org/?product=firefox-$FIREFOX_VERSION-ssl&os=linux64&lang=en-US"; else echo "https://download-installer.cdn.mozilla.net/pub/firefox/releases/$FIREFOX_VERSION/linux-x86_64/en-US/firefox-$FIREFOX_VERSION.tar.bz2"; fi) \
  && apt-get update -qqy \
  && apt-get -qqy --no-install-recommends install firefox \
  && rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
  && wget --no-verbose -O /tmp/firefox.tar.bz2 $FIREFOX_DOWNLOAD_URL \
  && apt-get -y purge firefox \
  && rm -rf /opt/firefox \
  && tar -C /opt -xjf /tmp/firefox.tar.bz2 \
  && rm /tmp/firefox.tar.bz2 \
  && mv /opt/firefox /opt/firefox-$FIREFOX_VERSION \
  && chmod 777 /opt/firefox-$FIREFOX_VERSION \
  && ln -fs /opt/firefox-$FIREFOX_VERSION/firefox /usr/bin/firefox

#============
# GeckoDriver
#============
##ARG GECKODRIVER_VERSION=latest
##RUN GK_VERSION=$(if [ ${GECKODRIVER_VERSION:-latest} = "latest" ]; then echo $(wget -qO- "https://api.github.com/repos/mozilla/geckodriver/releases/latest" | grep '"tag_name":' | sed -E 's/.*"v([0-9.]+)".*/\1/'); else echo $GECKODRIVER_VERSION; fi) \
  ##&& echo "Using GeckoDriver version: "$GK_VERSION \
  ##&& wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v$GK_VERSION/geckodriver-v$GK_VERSION-linux64.tar.gz \
  ##&& rm -rf /opt/geckodriver \
  ##&& tar -C /opt -zxf /tmp/geckodriver.tar.gz \
  ##&& rm /tmp/geckodriver.tar.gz \
  ##&& mv /opt/geckodriver /opt/geckodriver-$GK_VERSION \
  ##&& chmod 755 /opt/geckodriver-$GK_VERSION \
  ##&& ln -fs /opt/geckodriver-$GK_VERSION /usr/local/bin/geckodriver

#We need to give permissions to /root because gecko driver needs to write over it a log file as well as the dconf folder needs to be created since there is where the profile is created
RUN mkdir root/.cache/dconf
RUN chmod 777 -R root/

# Folder to copy the selenum grid .json conf files.
RUN mkdir /usr/local/bin/grid

COPY ./automation/src/com/bluemoon/resources/grid/StartServers.sh /usr/local/bin/runSmokeTesting
#Copy the selenium grid conf files for node and hub
COPY ./automation/src/com/bluemoon/resources/grid/StartGrid.sh /usr/local/bin/grid/runGrid
COPY ./automation/src/com/bluemoon/resources/grid/GridHub.json /usr/local/bin/grid/GridHub.json
COPY ./automation/src/com/bluemoon/resources/grid/GridNode.json /usr/local/bin/grid/GridNode.json

COPY ./automation /opt/automation
COPY ./deploy/secrets-entrypoint.sh /usr/local/bin/
WORKDIR /opt/automation
RUN mvn install -DskipTests

ENTRYPOINT ["secrets-entrypoint.sh"]