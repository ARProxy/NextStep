package webserver;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

import org.junit.Test;
import org.mockito.Mockito;

public class RequestHandlerTest {
	private static String testDirectory = "src/test/resources/";

	@Test
	public void request_GET() throws Exception {
		InputStream in = new FileInputStream(new File(testDirectory + "Http_GET.txt"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Socket mockSocket = Mockito.mock(Socket.class);
		Mockito.when(mockSocket.getInputStream()).thenReturn(in);
		Mockito.when(mockSocket.getOutputStream()).thenReturn(out);

		RequestHandler requestHandler = new RequestHandler(mockSocket);
		requestHandler.run();

		String response = out.toString();
		assertTrue(response.contains("HTTP/1.1 200 OK"));
	}
}
