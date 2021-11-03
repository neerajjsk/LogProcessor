package log.process.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import log.process.model.LogModel;
import log.process.model.LogModel.StateEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogProcessor {
	
	public static String fileName = "log.txt";
	public static int alarmValue =4;

	public static void main(String[] args) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Map<String,LogModel> logMap = new HashMap<>();
		long startTime=0;
		long endTime=0;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(fileName);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		for (String line; (line = reader.readLine()) != null;) {
			LogModel logModel = mapper.readValue(line, LogModel.class);
			if(!logMap.containsKey(logModel.getId())) {
				logMap.put(logModel.getId(), logModel);
			}
			else {
				LogModel logModel1 = logMap.get(logModel.getId());
				if(logModel1.getState().getValue().equals(StateEnum.STARTED.toString())){
					startTime = logModel1.getTimestamp();
					endTime = logModel.getTimestamp();
				}
				else {
					startTime = logModel.getTimestamp();
					endTime = logModel1.getTimestamp();
				}
				if(endTime-startTime >= alarmValue) {
					log.warn("Please check the application {}",logModel1.getId());
				}
			}
		}

	}

}
