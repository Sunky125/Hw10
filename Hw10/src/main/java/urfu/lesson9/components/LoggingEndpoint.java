package urfu.lesson9.components;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Endpoint(id = "custom")
public class LoggingEndpoint {
    private final SimpleDateFormat simpleDateFormat;

    public LoggingEndpoint() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    }

    @ReadOperation
    public String logTime() {
        var date = simpleDateFormat.format(new Date());
        System.out.println(date);
        return date;
    }
}
