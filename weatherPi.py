#import

import time
import feedparser
import os

while True:
	rss_link = 'http://weather.yahooapis.com/forecastrss?w=1132599&u=c'
	d = feedparser.parse(rss_link)

	location = d.feed.yweather_location
	atmosphere = d.feed.yweather_atmosphere
	astronomy = d.feed.yweather_astronomy
	city = location['city']
	humidity = atmosphere['humidity']
	sunrise = astronomy['sunrise']
	sunset = astronomy['sunset']

	summary = d.entries[0].summary
	temp = summary.split("High: ")
	temp = temp[1].split("Low : ")
	high = temp[0]
	low = temp[1].split("<br />")[0]

	print ("Wheather for " + city)
	print ("Low Temp : " + low)
	print ("High Temp : " + high)
	print ("Humidity : " + humidity)
	print ("Sunrise : " + sunrise)
	print ("Sunset : " + sunset)

	time.sleep(300)

