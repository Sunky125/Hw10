package urfu.lesson9.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import urfu.lesson9.Annotations.ApiInvokeCountChecker;

@RestController
public class GetApiController implements ApiController {
    @Override
    @ApiInvokeCountChecker
    @GetMapping("api/get")
    public void ExecuteRequest() {
        System.out.println("Api request completed");
    }
}
