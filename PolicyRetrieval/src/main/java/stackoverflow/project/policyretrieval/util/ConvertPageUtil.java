package stackoverflow.project.policyretrieval.util;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class ConvertPageUtil {
    public static <T> Page<T> convertPage(Page<?> page, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Object obj : page.getContent()) {
            T t = BeanUtils.instantiateClass(clazz);
            BeanUtils.copyProperties(obj, t);
            list.add(t);
        }
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }
}
