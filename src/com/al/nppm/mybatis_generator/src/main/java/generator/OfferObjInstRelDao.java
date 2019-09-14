package generator;

import generator.OfferObjInstRel;
import generator.OfferObjInstRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfferObjInstRelDao {
    long countByExample(OfferObjInstRelExample example);

    int deleteByExample(OfferObjInstRelExample example);

    int deleteByPrimaryKey(Long offerObjInstRelId);

    int insert(OfferObjInstRel record);

    int insertSelective(OfferObjInstRel record);

    List<OfferObjInstRel> selectByExample(OfferObjInstRelExample example);

    OfferObjInstRel selectByPrimaryKey(Long offerObjInstRelId);

    int updateByExampleSelective(@Param("record") OfferObjInstRel record, @Param("example") OfferObjInstRelExample example);

    int updateByExample(@Param("record") OfferObjInstRel record, @Param("example") OfferObjInstRelExample example);

    int updateByPrimaryKeySelective(OfferObjInstRel record);

    int updateByPrimaryKey(OfferObjInstRel record);
}