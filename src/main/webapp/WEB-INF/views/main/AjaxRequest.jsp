<%@ page language="java" import="java.io.*,java.net.*" contentType="text/xml; charset=utf-8" pageEncoding="utf-8" %>
<%
	String ServiceKey = request.getParameter("ServiceKey");
	String numOfRows = request.getParameter("numOfRows");
	String pageNo = request.getParameter("pageNo");
	String addr = request.getParameter("addr");
	String wgs84Lat = request.getParameter("wgs84Lat");
	String wgs84Lon = request.getParameter("wgs84Lon");
	
	String sUrl = request.getParameter("url") + "ServiceKey=" + ServiceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo;
	if (addr != null && !addr.equals("")) {
		sUrl += "&addr=" + addr;
	}
	if (wgs84Lat != null && !wgs84Lat.equals("")) {
		sUrl += "&WGS84_LAT=" + wgs84Lat;
	}
	if (wgs84Lon != null && !wgs84Lon.equals("")) {
		sUrl += "&WGS84_LON=" + wgs84Lon;
	}
	
	//System.out.println("sUrl : " + sUrl);
	
	URL url = new URL(sUrl);
	
	URLConnection connection = url.openConnection();
	connection.setRequestProperty("CONTENT-TYPE","text/html"); 
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
    String inputLine;
    String buffer = "";
    while ((inputLine = in.readLine()) != null) {
     	buffer += inputLine.trim();
    }
    
    if (!buffer.equals("")) {
    	buffer = buffer.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
    }
    
    //System.out.println("buffer : " + buffer);
    
    in.close();
%>
<%=buffer%>