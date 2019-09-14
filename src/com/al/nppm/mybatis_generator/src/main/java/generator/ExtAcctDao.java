package generator;

import generator.ExtAcct;
import generator.ExtAcctExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExtAcctDao {
    long countByExample(ExtAcctExample example);

    int deleteByExample(ExtAcctExample example);

    int deleteByPrimaryKey(Long extAcctId);

    int insert(ExtAcct record);

    int insertSelective(ExtAcct record);

    List<ExtAcct> selectByExample(ExtAcctExample example);

    ExtAcct selectByPrimaryKey(Long extAcctId);

    int updateByExampleSelective(@Param("record") ExtAcct record, @Param("example") ExtAcctExample example);

    int updateByExample(@Param("record") ExtAcct record, @Param("example") ExtAcctExample example);

    int updateByPrimaryKeySelective(ExtAcct record);

    int updateByPrimaryKey(ExtAcct record);
}