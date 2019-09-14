package generator;

import generator.OfferInst;
import generator.OfferInstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfferInstDao {
    long countByExample(OfferInstExample example);

    int deleteByExample(OfferInstExample example);

    int deleteByPrimaryKey(Long offerInstId);

    int insert(OfferInst record);

    int insertSelective(OfferInst record);

    List<OfferInst> selectByExample(OfferInstExample example);

    OfferInst selectByPrimaryKey(Long offerInstId);

    int updateByExampleSelective(@Param("record") OfferInst record, @Param("example") OfferInstExample example);

    int updateByExample(@Param("record") OfferInst record, @Param("example") OfferInstExample example);

    int updateByPrimaryKeySelective(OfferInst record);

    int updateByPrimaryKey(OfferInst record);
}