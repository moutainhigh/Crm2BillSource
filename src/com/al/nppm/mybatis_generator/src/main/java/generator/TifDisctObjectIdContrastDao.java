package generator;

import generator.TifDisctObjectIdContrast;
import generator.TifDisctObjectIdContrastExample;
import generator.TifDisctObjectIdContrastKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TifDisctObjectIdContrastDao {
    long countByExample(TifDisctObjectIdContrastExample example);

    int deleteByExample(TifDisctObjectIdContrastExample example);

    int deleteByPrimaryKey(TifDisctObjectIdContrastKey key);

    int insert(TifDisctObjectIdContrast record);

    int insertSelective(TifDisctObjectIdContrast record);

    List<TifDisctObjectIdContrast> selectByExample(TifDisctObjectIdContrastExample example);

    TifDisctObjectIdContrast selectByPrimaryKey(TifDisctObjectIdContrastKey key);

    int updateByExampleSelective(@Param("record") TifDisctObjectIdContrast record, @Param("example") TifDisctObjectIdContrastExample example);

    int updateByExample(@Param("record") TifDisctObjectIdContrast record, @Param("example") TifDisctObjectIdContrastExample example);

    int updateByPrimaryKeySelective(TifDisctObjectIdContrast record);

    int updateByPrimaryKey(TifDisctObjectIdContrast record);
}