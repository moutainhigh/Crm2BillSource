package generator;

import generator.OfferObjRel;
import generator.OfferObjRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfferObjRelDao {
    long countByExample(OfferObjRelExample example);

    int deleteByExample(OfferObjRelExample example);

    int deleteByPrimaryKey(Long offerObjRelId);

    int insert(OfferObjRel record);

    int insertSelective(OfferObjRel record);

    List<OfferObjRel> selectByExample(OfferObjRelExample example);

    OfferObjRel selectByPrimaryKey(Long offerObjRelId);

    int updateByExampleSelective(@Param("record") OfferObjRel record, @Param("example") OfferObjRelExample example);

    int updateByExample(@Param("record") OfferObjRel record, @Param("example") OfferObjRelExample example);

    int updateByPrimaryKeySelective(OfferObjRel record);

    int updateByPrimaryKey(OfferObjRel record);
}