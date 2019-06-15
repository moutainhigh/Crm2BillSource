package generator;

import generator.CrmRent;
import generator.CrmRentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmRentDao {
    long countByExample(CrmRentExample example);

    int deleteByExample(CrmRentExample example);

    int deleteByPrimaryKey(Long rowId);

    int insert(CrmRent record);

    int insertSelective(CrmRent record);

    List<CrmRent> selectByExample(CrmRentExample example);

    CrmRent selectByPrimaryKey(Long rowId);

    int updateByExampleSelective(@Param("record") CrmRent record, @Param("example") CrmRentExample example);

    int updateByExample(@Param("record") CrmRent record, @Param("example") CrmRentExample example);

    int updateByPrimaryKeySelective(CrmRent record);

    int updateByPrimaryKey(CrmRent record);
}