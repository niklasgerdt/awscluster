package aws.cluster.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderEntity {
    private int id;
    private int customerId;
    private String description;
}
