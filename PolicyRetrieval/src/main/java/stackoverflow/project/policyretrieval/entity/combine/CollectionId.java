package stackoverflow.project.policyretrieval.entity.combine;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class CollectionId implements Serializable {
    private String userName;
    private String policyId;
    public CollectionId() {
    }
}
