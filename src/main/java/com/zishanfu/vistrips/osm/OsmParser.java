package com.zishanfu.vistrips.osm;

import org.jxmapviewer.viewer.GeoPosition;
import org.openstreetmap.osmosis.xml.v0_6.XmlDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zishanfu.vistrips.tools.HDFSUtil;


public class OsmParser {
	private static String tmpPath = "";
	private static final Logger LOG = LoggerFactory.getLogger(OsmParser.class);
	private HDFSUtil hdfs;
	
	public OsmParser(HDFSUtil hdfs) {
		this.hdfs = hdfs;
	}
	
	public String runInHDFS(GeoPosition geo1, GeoPosition geo2) {
		String destPath= "/vistrips";
		hdfs.deleteDir(destPath);
		hdfs.mkdir(destPath);
		tmpPath = hdfs.mkdirTemp(destPath);
		LOG.info(String.format("Created a folder in HDFS located in %s", tmpPath));
		
		String osmUrl = "http://overpass-api.de/api";
		XmlDownloader xmlDownloader = new XmlDownloader(geo1.getLongitude(), geo2.getLongitude(), geo1.getLatitude(), geo2.getLatitude(), osmUrl);
		xmlDownloader.setSink(new OsmParquetSink(tmpPath));
		xmlDownloader.run();
		LOG.info(String.format("Sinked openstreetmap data"));
		//return tmpPath.substring(tmpPath.indexOf("s/") + 1);
		return tmpPath;
	}
	
	public static String runInLocal(GeoPosition geo1, GeoPosition geo2) {
		String resources = System.getProperty("user.dir") + "/src/test/resources";
		String destPath= resources + "/vistrips";
		String osmUrl = "http://overpass-api.de/api";
		XmlDownloader xmlDownloader = new XmlDownloader(geo1.getLongitude(), geo2.getLongitude(), geo1.getLatitude(), geo2.getLatitude(), osmUrl);
		xmlDownloader.setSink(new OsmParquetSink(destPath));
		xmlDownloader.run();
		LOG.info(String.format("Sinked openstreetmap data"));
		return destPath;
	}
}