package generator;

import generator.TifDisctParamIdContrast;
import generator.TifDisctParamIdContrastExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TifDisctParamIdContrastDao {
    long countByExample(TifDisctParamIdContrastExample example);

    int deleteByExample(TifDisctParamIdContrastExample example);

    int deleteByPrimaryKey(Long idNo);

    int insert(TifDisctParamIdContrast record);

    int insertSelective(TifDisctParamIdContrast record);

    List<TifDisctParamIdContrast> selectByExample(TifDisctParamIdContrastExample example);

    TifDisctParamIdContrast selectByPrimaryKey(Long idNo);

    int updateByExampleSelective(@Param("record") TifDisctParamIdContrast record, @Param("example") TifDisctParamIdContrastExample example);

    int updateByExample(@Param("record") TifDisctParamIdContrast record, @Param("example") TifDisctParamIdContrastExample example);

    int updateByPrimaryKeySelective(TifDisctParamIdContrast record);

    int updateByPrimaryKey(TifDisctParamIdContrast record);
}