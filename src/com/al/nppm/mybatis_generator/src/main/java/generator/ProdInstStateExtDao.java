package generator;

import generator.ProdInstStateExt;
import generator.ProdInstStateExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdInstStateExtDao {
    long countByExample(ProdInstStateExtExample example);

    int deleteByExample(ProdInstStateExtExample example);

    int deleteByPrimaryKey(Long prodInstStateId);

    int insert(ProdInstStateExt record);

    int insertSelective(ProdInstStateExt record);

    List<ProdInstStateExt> selectByExample(ProdInstStateExtExample example);

    ProdInstStateExt selectByPrimaryKey(Long prodInstStateId);

    int updateByExampleSelective(@Param("record") ProdInstStateExt record, @Param("example") ProdInstStateExtExample example);

    int updateByExample(@Param("record") ProdInstStateExt record, @Param("example") ProdInstStateExtExample example);

    int updateByPrimaryKeySelective(ProdInstStateExt record);

    int updateByPrimaryKey(ProdInstStateExt record);
}