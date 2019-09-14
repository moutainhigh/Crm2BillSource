package generator;

import generator.ProdInstAcctRelAttr;
import generator.ProdInstAcctRelAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdInstAcctRelAttrDao {
    long countByExample(ProdInstAcctRelAttrExample example);

    int deleteByExample(ProdInstAcctRelAttrExample example);

    int deleteByPrimaryKey(Long prodInstAcctRelId);

    int insert(ProdInstAcctRelAttr record);

    int insertSelective(ProdInstAcctRelAttr record);

    List<ProdInstAcctRelAttr> selectByExample(ProdInstAcctRelAttrExample example);

    ProdInstAcctRelAttr selectByPrimaryKey(Long prodInstAcctRelId);

    int updateByExampleSelective(@Param("record") ProdInstAcctRelAttr record, @Param("example") ProdInstAcctRelAttrExample example);

    int updateByExample(@Param("record") ProdInstAcctRelAttr record, @Param("example") ProdInstAcctRelAttrExample example);

    int updateByPrimaryKeySelective(ProdInstAcctRelAttr record);

    int updateByPrimaryKey(ProdInstAcctRelAttr record);
}