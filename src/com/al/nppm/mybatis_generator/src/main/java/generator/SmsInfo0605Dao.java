package generator;

import generator.SmsInfo0605;
import generator.SmsInfo0605Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsInfo0605Dao {
    long countByExample(SmsInfo0605Example example);

    int deleteByExample(SmsInfo0605Example example);

    int insert(SmsInfo0605 record);

    int insertSelective(SmsInfo0605 record);

    List<SmsInfo0605> selectByExample(SmsInfo0605Example example);

    int updateByExampleSelective(@Param("record") SmsInfo0605 record, @Param("example") SmsInfo0605Example example);

    int updateByExample(@Param("record") SmsInfo0605 record, @Param("example") SmsInfo0605Example example);
}