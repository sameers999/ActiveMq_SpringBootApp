package com.imi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.imi.reciver.MQConsumer;

@Service
public class MqService {

//	@Value("${file.location.json}")
//	private String path;

	@Autowired
	private JmsTemplate jmsTemplat;

	@Autowired
	private Queue queue;

	@Autowired
	private MQConsumer mQConsumer;

	public String sendMassageToMq() {
		System.out.println("message come to the service");
			
		String messageSend = fileRead();
		
		System.out.println(messageSend);
		
		
		System.out.println(messageSend + "message send to Active MQ ......" );
		jmsTemplat.convertAndSend(queue, messageSend);
  
		return "published successfully";

	}

	public String reciveMessageFromMq() {

		return mQConsumer.getMqReceverMessage();
	}
	public String fileRead() {
        String response = "";
        try {

           InputStream inputStream = this.getClass().getResourceAsStream("/obdjson.txt");
            System.out.println(inputStream.toString());
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
           String curLine;
            while ((curLine = bufferedReader.readLine()) != null) {
                if (curLine.contains("\"dnis\"")) {
                    long theRandomNum = (long) (Math.random() * Math.pow(10, 10));
                    response += "\"dnis\":\"" + theRandomNum + "\",";
                } else {
                    response += curLine;
                }
            }
            bufferedReader.close();



       } catch (Exception e) {
        }
        System.out.println(response);



       return response;
    }
}
	
//	public String fileRead() {
//		String response = "";
//		System.out.println("path -->"+path);
//		try {
//			String file = path;
//			Path pathobj = Paths.get(file);
//			BufferedReader bufferedReader = Files.newBufferedReader(pathobj);
//			String currentLine;
//			while ((currentLine = bufferedReader.readLine()) != null) {
//				if (currentLine.contains("\"dnis\"")) {
//					long theRandomNum = (long) (Math.random() * Math.pow(10, 10));
//					response += "\"dnis\":\"" + theRandomNum + "\",";
//				} else {
//					response += currentLine;
//				}
//			}
//			bufferedReader.close();
//
//		} catch (Exception e) {
//		}
//		return response;
//	}
//
//}
