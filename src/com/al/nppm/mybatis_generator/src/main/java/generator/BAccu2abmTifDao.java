package generator;

import generator.BAccu2abmTif;
import generator.BAccu2abmTifExample;
import generator.BAccu2abmTifKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BAccu2abmTifDao {
    long countByExample(BAccu2abmTifExample example);

    int deleteByExample(BAccu2abmTifExample example);

    int deleteByPrimaryKey(BAccu2abmTifKey key);

    int insert(BAccu2abmTif record);

    int insertSelective(BAccu2abmTif record);

    List<BAccu2abmTif> selectByExample(BAccu2abmTifExample example);

    BAccu2abmTif selectByPrimaryKey(BAccu2abmTifKey key);

    int updateByExampleSelective(@Param("record") BAccu2abmTif record, @Param("example") BAccu2abmTifExample example);

    int updateByExample(@Param("record") BAccu2abmTif record, @Param("example") BAccu2abmTifExample example);

    int updateByPrimaryKeySelective(BAccu2abmTif record);

    int updateByPrimaryKey(BAccu2abmTif record);
}