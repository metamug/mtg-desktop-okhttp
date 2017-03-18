package modal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class Data {

	@XmlElement(name = "threshold")
	private List<Task> taskList;

	public List<Task> getTasks() {
		
		return taskList;
	}

	public void setTasks(List<Task> tasks) {
		this.taskList = tasks;
	}

}
