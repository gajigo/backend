package Api.Handlers;

import Api.Models.AttendanceRequest;
import Api.Models.Response;
import Api.Services.LectureService;
import Api.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceHandler implements IHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AttendanceHandler() {
    }

    @Override
    public void execute(HttpExchange exchange) throws IOException {
        byte[] response = new byte[0];
        System.out.println("metodo " + exchange.getRequestMethod());
        if (exchange.getRequestMethod().equals("POST")) {
            Response postResponse = this.post(exchange.getRequestBody());
            exchange.sendResponseHeaders(postResponse.getStatusCode().getCode(), 0);
            response = objectMapper.writeValueAsBytes(postResponse);
        }
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response);
        outputStream.close();
    }

    private Response post(InputStream input) throws IOException {
        try {
            AttendanceRequest request = objectMapper.readValue(input, AttendanceRequest.class);
            LectureService lectureService = new LectureService();
            lectureService.addLectureAttendee(request);

            System.out.println("vamos ver " + request.toString());
        } catch (IOException e) {
            // thorwn invalid response
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Response(StatusCode.OK);
    }
}
