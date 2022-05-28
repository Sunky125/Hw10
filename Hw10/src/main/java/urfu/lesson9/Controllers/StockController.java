package urfu.lesson9.Controllers;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.*;
import urfu.lesson9.StockManager;

import java.util.List;

@RestController
@RequestMapping(path = "stocks")
public class StockController {
    private final StockManager stockManager;

    public StockController(StockManager stockManager)
    {
        this.stockManager = stockManager;
    }

    @GetMapping("/get")
    @ResponseBody
    public List<String> getItems(@RequestParam int size) {
        return stockManager.getItem(size);
    }

    @GetMapping("/add")
    @ResponseBody
    @Timed
    @Counted
    public int addItems(@RequestParam List<String> items) {
        System.out.println(items.toString());
        stockManager.addItems(items);
        return stockManager.getNumberOfItems();
    }
}
