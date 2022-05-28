package urfu.lesson9;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StockManager {
    private final MeterRegistry meterRegistry;
    private final List<String> orders;
    private Counter counter;
    private Gauge gauge;

    public StockManager(MeterRegistry meterRegistry)
    {
        this.meterRegistry = meterRegistry;
        orders = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        counter =
                Counter.builder("order_created")
                        .description("Number of orders created")
                        .register(meterRegistry);
        gauge =
                Gauge.builder("stock.size", this, StockManager::getNumberOfItems)
                        .description("Number of items in stocks")
                        .register(meterRegistry);
    }

    public int getNumberOfItems() {
        return orders.size();
    }

    public void addItems(List<String> items) {
        orders.addAll(items);
        gauge.measure();
    }

    public List<String> getItem(int count) {
        List<String> items = new ArrayList<>(count);
        for (var i = 0; i < count; i++) {
            try {
                items.add(orders.remove(i));
            }
            catch (IndexOutOfBoundsException e) {
                break;
            }
        }

        counter.increment();
        gauge.measure();
        return items;
    }
}
