package generator;

import generator.AElecInvContact;
import generator.AElecInvContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AElecInvContactDao {
    long countByExample(AElecInvContactExample example);

    int deleteByExample(AElecInvContactExample example);

    int deleteByPrimaryKey(Integer contactId);

    int insert(AElecInvContact record);

    int insertSelective(AElecInvContact record);

    List<AElecInvContact> selectByExample(AElecInvContactExample example);

    AElecInvContact selectByPrimaryKey(Integer contactId);

    int updateByExampleSelective(@Param("record") AElecInvContact record, @Param("example") AElecInvContactExample example);

    int updateByExample(@Param("record") AElecInvContact record, @Param("example") AElecInvContactExample example);

    int updateByPrimaryKeySelective(AElecInvContact record);

    int updateByPrimaryKey(AElecInvContact record);
}