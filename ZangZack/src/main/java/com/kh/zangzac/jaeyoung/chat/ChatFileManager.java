package com.kh.zangzac.jaeyoung.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class ChatFileManager {

    public void saveChat(JSONObject obj) {
        // 파일 경로 설정 (C:/zangzacChat/chatlog/roomName.txt)
        String directoryPath = "C:/zangzacChat/chatlog/";
        String fileName = obj.get("roomName") + ".txt";
        String filePath = directoryPath + fileName;

        try {
            // 디렉토리가 존재하지 않으면 생성
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 파일이 없으면 생성, 있으면 기존 파일에 추가
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(obj.toJSONString());
                writer.newLine(); // JSON 값 사이에 개행 추가
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 애플리케이션 요구사항에 따라 예외 처리를 수행하세요
        }
    }
    
    @SuppressWarnings("unchecked")
	public void updateUnreadChatter(String roomName, String memberId) {
    	
    	String filePath = "C:/zangzacChat/chatlog/"+roomName+".txt";
    	
        try {
            // JSON 파일 읽기
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                return;
            }

            // 수정된 내용을 임시 리스트에 저장
            JSONArray updatedUnreadChatterList = new JSONArray();

            // JSON 데이터를 읽어옴
            JSONParser parser = new JSONParser();
            try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    JSONObject json = (JSONObject) parser.parse(line);

                    // unReadChatter 필드에 memberId가 있는지 확인
                    if (json.containsKey("unReadChatter")) {
                        JSONArray unReadChatterArray = (JSONArray) json.get("unReadChatter");
                        Iterator<?> iterator = unReadChatterArray.iterator();

                        while (iterator.hasNext()) {
                        	String chatterValue = (String) iterator.next();
                        	 if (chatterValue.equals(memberId)) {
                        	        iterator.remove();
                        	  }
                        }
                    }

                    // 수정된 JSON 객체를 임시 리스트에 추가
                    updatedUnreadChatterList.add(json);
                }
            }

            // 전체 파일을 다시 쓰기
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (Object updatedUnreadChatter : updatedUnreadChatterList) {
                    // JSON 데이터를 파일에 쓰기
                    writer.write(((JSONObject) updatedUnreadChatter).toJSONString());
                    writer.newLine();
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // 애플리케이션 요구사항에 따라 예외 처리를 수행하세요
        }
    }
} 