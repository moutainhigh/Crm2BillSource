package generator;

import generator.OneItemResultHis;
import generator.OneItemResultHisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OneItemResultHisDao {
    long countByExample(OneItemResultHisExample example);

    int deleteByExample(OneItemResultHisExample example);

    int deleteByPrimaryKey(Long oneAcctItemId);

    int insert(OneItemResultHis record);

    int insertSelective(OneItemResultHis record);

    List<OneItemResultHis> selectByExample(OneItemResultHisExample example);

    OneItemResultHis selectByPrimaryKey(Long oneAcctItemId);

    int updateByExampleSelective(@Param("record") OneItemResultHis record, @Param("example") OneItemResultHisExample example);

    int updateByExample(@Param("record") OneItemResultHis record, @Param("example") OneItemResultHisExample example);

    int updateByPrimaryKeySelective(OneItemResultHis record);

    int updateByPrimaryKey(OneItemResultHis record);
}