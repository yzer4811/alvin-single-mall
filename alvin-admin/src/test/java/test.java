import cn.hutool.core.util.ReUtil;
import com.alvin.mall.StartApp;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryAttributeRelationDTO;
import com.alvin.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@SpringBootTest(classes = {StartApp.class})
public class test {

    @Autowired
    PmsProductCategoryAttributeRelationService relationService;

    @Test
    public void testOne() {
        List<PmsProductCategoryAttributeRelationDTO> productAttrInfo = relationService.getProductAttrInfo(0L);
        System.out.println(productAttrInfo);
    }

    @Test
    public void isMatch() {
        String str = " 11";
        boolean res = ReUtil.isMatch("\\d+", str);
        System.out.println(res);
    }
}
