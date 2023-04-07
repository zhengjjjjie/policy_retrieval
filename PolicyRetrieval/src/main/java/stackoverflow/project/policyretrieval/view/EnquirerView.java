package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonView;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;


public class EnquirerView {
    @JsonView(EnquirerView.Public.class)
    public String name;

    @JsonView(EnquirerView.Internal.class)
    public String password;

    public static class Public {}
    public static class Internal extends Public {}
}
