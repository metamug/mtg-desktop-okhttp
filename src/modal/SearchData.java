package modal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class SearchData {

	@XmlElement(name = "task")
	private List<SearchTask> searchList;

	public List<SearchTask> getTasks() {
		
		return searchList;
	}

	public void setSearch(List<SearchTask> tasks) {
		this.searchList = tasks;
	}
}
