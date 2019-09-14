package generator;

import generator.Offer;
import generator.OfferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfferDao {
    long countByExample(OfferExample example);

    int deleteByExample(OfferExample example);

    int deleteByPrimaryKey(Long offerId);

    int insert(Offer record);

    int insertSelective(Offer record);

    List<Offer> selectByExample(OfferExample example);

    Offer selectByPrimaryKey(Long offerId);

    int updateByExampleSelective(@Param("record") Offer record, @Param("example") OfferExample example);

    int updateByExample(@Param("record") Offer record, @Param("example") OfferExample example);

    int updateByPrimaryKeySelective(Offer record);

    int updateByPrimaryKey(Offer record);
}