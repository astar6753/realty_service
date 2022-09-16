package com.astar.realty.infra.file.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RestController @RequestMapping("/api/office")
public class OfficeAPIController {

    @GetMapping("/parse")
    public String getOfficeInfoParse() throws IOException {
        // 중구, 동구, 서구, 남구, 북구, 수성구, 달서구, 달성군
        String[] IdCodeList = {"27110", "27140", "27170", "27200", "27230", "27260", "27290", "27710"};
        // String[] IdMaxCount = {"1367","1766","1311","1352","1807","2253","2196","1561"};
        String[] IdMaxCount = {"10","1766","1311","1352","1807","2253","2196","1561"};
        String jsonString = "";

        for(int i=0; i<8; i++){
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1611000/nsdi/StatsIndicatorService/attr/getAreaOfLandCategory"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=L058QZUTe%2FdThSn5A3CDCqX%2BNLfrIVJVf0MD6dGDZOKjGW00HXp3jE4ut5aNlved%2BTO8M9yIi14Vqc2f%2F0H9Gw%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("ldCode","UTF-8") + "=" + URLEncoder.encode(IdCodeList[i], "UTF-8")); /*법정동코드(reqLvl값이 1일 경우: 해당 없음, 2일 경우: 2~5자리, 3일 경우: 2~10자리)*/
            urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*응답결과 형식(xml 또는 json)*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*검색건수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            jsonString += sb.toString();
        }
        return jsonString;
    }
    

}
