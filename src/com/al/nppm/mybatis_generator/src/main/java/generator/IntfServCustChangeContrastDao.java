package generator;

import generator.IntfServCustChangeContrast;
import generator.IntfServCustChangeContrastExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntfServCustChangeContrastDao {
    long countByExample(IntfServCustChangeContrastExample example);

    int deleteByExample(IntfServCustChangeContrastExample example);

    int deleteByPrimaryKey(Long archGrpId);

    int insert(IntfServCustChangeContrast record);

    int insertSelective(IntfServCustChangeContrast record);

    List<IntfServCustChangeContrast> selectByExample(IntfServCustChangeContrastExample example);

    IntfServCustChangeContrast selectByPrimaryKey(Long archGrpId);

    int updateByExampleSelective(@Param("record") IntfServCustChangeContrast record, @Param("example") IntfServCustChangeContrastExample example);

    int updateByExample(@Param("record") IntfServCustChangeContrast record, @Param("example") IntfServCustChangeContrastExample example);

    int updateByPrimaryKeySelective(IntfServCustChangeContrast record);

    int updateByPrimaryKey(IntfServCustChangeContrast record);
}