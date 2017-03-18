package modal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "threshold")
public class Task {
	
	private int taskId;
	private String taskName;
	private double taskThreshold;
	String taskType;
	private int slot;

	
	public Task(){}
	
	public Task(String taskName, double taskThreshold, String taskType, int slot) {
		super();
		this.taskName = taskName;
		this.taskThreshold = taskThreshold;
		this.taskType = taskType;
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", taskThreshold=" + taskThreshold + ", taskType=" + taskType + ", slot="
				+ slot + "]";
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public double getTaskThreshold() {
		return taskThreshold;
	}

	public void setTaskThreshold(double taskThreshold) {
		this.taskThreshold = taskThreshold;
	}

	public String getTaskType() {

		switch (this.taskType) {
		case "0":
			return "Numeric";
		case "1":
			return "Alpha-Numeric";
		case "2":
			return "Text";
		default:
			return "";
		}
	}
	
	public String getTaskType(boolean ord){
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof Task){
			return this.getSlot() == ((Task) obj).getSlot();
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return slot;
	}
}
