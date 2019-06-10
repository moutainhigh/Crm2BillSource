package generator;

import generator.ProdInstRel;
import generator.ProdInstRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdInstRelDao {
    long countByExample(ProdInstRelExample example);

    int deleteByExample(ProdInstRelExample example);

    int deleteByPrimaryKey(Long prodInstRelId);

    int insert(ProdInstRel record);

    int insertSelective(ProdInstRel record);

    List<ProdInstRel> selectByExample(ProdInstRelExample example);

    ProdInstRel selectByPrimaryKey(Long prodInstRelId);

    int updateByExampleSelective(@Param("record") ProdInstRel record, @Param("example") ProdInstRelExample example);

    int updateByExample(@Param("record") ProdInstRel record, @Param("example") ProdInstRelExample example);

    int updateByPrimaryKeySelective(ProdInstRel record);

    int updateByPrimaryKey(ProdInstRel record);
}