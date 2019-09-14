package generator;

import generator.OrdProdInstAcctRelAttr;
import generator.OrdProdInstAcctRelAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdProdInstAcctRelAttrDao {
    long countByExample(OrdProdInstAcctRelAttrExample example);

    int deleteByExample(OrdProdInstAcctRelAttrExample example);

    int deleteByPrimaryKey(Long rowId);

    int insert(OrdProdInstAcctRelAttr record);

    int insertSelective(OrdProdInstAcctRelAttr record);

    List<OrdProdInstAcctRelAttr> selectByExample(OrdProdInstAcctRelAttrExample example);

    OrdProdInstAcctRelAttr selectByPrimaryKey(Long rowId);

    int updateByExampleSelective(@Param("record") OrdProdInstAcctRelAttr record, @Param("example") OrdProdInstAcctRelAttrExample example);

    int updateByExample(@Param("record") OrdProdInstAcctRelAttr record, @Param("example") OrdProdInstAcctRelAttrExample example);

    int updateByPrimaryKeySelective(OrdProdInstAcctRelAttr record);

    int updateByPrimaryKey(OrdProdInstAcctRelAttr record);
}