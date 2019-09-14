package generator;

import generator.ServiceOfferContrast;
import generator.ServiceOfferContrastExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceOfferContrastDao {
    long countByExample(ServiceOfferContrastExample example);

    int deleteByExample(ServiceOfferContrastExample example);

    int deleteByPrimaryKey(Long servcieOfferId);

    int insert(ServiceOfferContrast record);

    int insertSelective(ServiceOfferContrast record);

    List<ServiceOfferContrast> selectByExample(ServiceOfferContrastExample example);

    ServiceOfferContrast selectByPrimaryKey(Long servcieOfferId);

    int updateByExampleSelective(@Param("record") ServiceOfferContrast record, @Param("example") ServiceOfferContrastExample example);

    int updateByExample(@Param("record") ServiceOfferContrast record, @Param("example") ServiceOfferContrastExample example);

    int updateByPrimaryKeySelective(ServiceOfferContrast record);

    int updateByPrimaryKey(ServiceOfferContrast record);
}