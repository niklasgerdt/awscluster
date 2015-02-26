package aws.cluster.order;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    String link;
}
