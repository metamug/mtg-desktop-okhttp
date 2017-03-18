package modal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.jmx.snmp.Timestamp;

@XmlRootElement(name = "task")
public class SearchTask {
//	private int taskId;
	
	private String taskName;
	private float threshold;
	private String taskDate;
	private int taskHour;

	public SearchTask(){}
	
	public SearchTask(String taskDate,String taskName, float threshold, int taskHour) {
		super();
		this.taskName = taskName;
		this.threshold = threshold;
		this.taskDate = taskDate;
		this.taskHour = taskHour;
	}

	@Override
	public String toString() {
		return "Task [taskDate="+taskDate+",taskName=" + taskName + ", threshold=" + threshold + ",  taskHour="
				+ taskHour + "]";
	}

//	public int getTaskId() {
//		return taskId;
//	}
//
//	public void setTaskId(int taskId) {
//		this.taskId = taskId;
//	}

	public String getTaskName() {
		return taskName;
	}

	@XmlElement
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public String getTaskDate() {

		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public int getTaskHour() {
		return taskHour;
	}

	public void setTaskHour(int taskHour) {
		this.taskHour = taskHour;
	}

}
