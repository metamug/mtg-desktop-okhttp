## mtg-desktop-okhttp

This project is implemented using Metamug REST API.
Checklist API performs CRUD operations.

Technologies used to build this application.

- Metamug API
- OK-HTTP
- JAXB

The content header is set to application/xml to retrieve xml.

```JAVA
Request request = new Request.Builder().url(url1 + "?q=4")
		.addHeader("accept", "application/xml").build();
Response response = client.newCall(request).execute();
JAXBContext jaxbContext = JAXBContext.newInstance(SearchData.class);
Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
SearchData data = (SearchData) jaxbUnmarshaller.unmarshal(response.body().byteStream());

```

These are the endpoints used.

https://api.metamug.com/checklist/v1.0/threshold

https://api.metamug.com/checklist/v1.0/task