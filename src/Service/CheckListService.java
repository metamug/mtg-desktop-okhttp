package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import main.AppMain;
import modal.Data;
import modal.SearchData;
import modal.SearchTask;
import modal.Task;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.MapQuery;

public class CheckListService {

	OkHttpClient client;
	private String url, url1;

	public CheckListService() {
		url = AppMain.HOST + "/checklist/v1.0/threshold";
		url1 = AppMain.HOST + "/checklist/v1.0/task";
		client = new OkHttpClient().newBuilder().connectTimeout(3000, TimeUnit.SECONDS)
				.writeTimeout(3000, TimeUnit.SECONDS).readTimeout(3000, TimeUnit.SECONDS).build();
	}

	public List<Task> getTasks() {
		try {
			Request request = new Request.Builder().url(url).addHeader("accept", "application/xml").build();
			Response response = client.newCall(request).execute();

			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// System.out.println(response.body().string());
			Data data = (Data) jaxbUnmarshaller.unmarshal(response.body().byteStream());
			// System.out.println(data.getTasks());
			return data.getTasks();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Task>();
		}
	}

	public void postTask(Task task) {
		try {

			RequestBody formBody = new FormBody.Builder().add("taskName", task.getTaskName())
					.add("taskThrVal", "" + task.getTaskThreshold()).add("taskType", task.getTaskType(true))
					.add("slotNo", "" + task.getSlot()).build();

			Request request = new Request.Builder().url(url).post(formBody).build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTask(Task task) {
		try {

			RequestBody formBody = new FormBody.Builder().add("taskName", task.getTaskName())
					.add("taskThrVal", "" + task.getTaskThreshold()).add("taskType", task.getTaskType(true))
					.add("slotNo", "" + task.getSlot()).build();

			Request request = new Request.Builder().url(url + "/" + task.getTaskId()).post(formBody).build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTask(int taskId) {
		try {

			Request request = new Request.Builder().url(url + "/" + taskId).delete().build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<SearchTask> getSearch() {
		try {
			Request request = new Request.Builder().url(url1 + "?q=4").addHeader("accept", "application/xml").build();
			Response response = client.newCall(request).execute();
			JAXBContext jaxbContext = JAXBContext.newInstance(SearchData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SearchData data = (SearchData) jaxbUnmarshaller.unmarshal(response.body().byteStream());

			System.out.println(data.getTasks());
			return data.getTasks();

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<SearchTask>();
		}
	}

	public List<SearchTask> getSearch(String fromDate, String toDate) {
		try {
			Map<String, String> m = new HashMap<String, String>();
			m.put("fromDate", fromDate);
			m.put("toDate", toDate);
			m.put("q", "3");
			System.out.println(MapQuery.urlEncodeUTF8(m));
			Request request = new Request.Builder().url(url1 + "?" + MapQuery.urlEncodeUTF8(m))
					.addHeader("Accept", "application/xml")
					.addHeader("Accept-Language","en-GB").build();
			Response response = client.newCall(request).execute();
			JAXBContext jaxbContext = JAXBContext.newInstance(SearchData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SearchData data = (SearchData) jaxbUnmarshaller.unmarshal(response.body().byteStream());

			System.out.println(data.getTasks());
			return data.getTasks();

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<SearchTask>();
		}
	}
}
