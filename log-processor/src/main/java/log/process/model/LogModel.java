package log.process.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class LogModel {
	@JsonProperty("id")
	private String id;

	@JsonProperty("timestamp")
	private long timestamp;

	@JsonProperty("type")
	private String type;

	@JsonProperty("host")
	private String host;

	@JsonProperty("state")
	private StateEnum state;
	
	 public enum StateEnum {
		 STARTED("STARTED"),
		    
		 FINISHED("FINISHED");

		    private String value;

		    StateEnum(String value) {
		      this.value = value;
		    }

		    @JsonValue
		    public String getValue() {
		      return value;
		    }

		    @Override
		    public String toString() {
		      return String.valueOf(value);
		    }

		    @JsonCreator
		    public static StateEnum fromValue(String value) {
		      for (StateEnum b : StateEnum.values()) {
		        if (b.value.equals(value)) {
		          return b;
		        }
		      }
		      throw new IllegalArgumentException("Unexpected value '" + value + "'");
		    }
		  }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

}
