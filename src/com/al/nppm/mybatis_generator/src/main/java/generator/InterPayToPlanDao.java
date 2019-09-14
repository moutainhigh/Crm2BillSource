package generator;

import generator.InterPayToPlan;
import generator.InterPayToPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterPayToPlanDao {
    long countByExample(InterPayToPlanExample example);

    int deleteByExample(InterPayToPlanExample example);

    int deleteByPrimaryKey(Long interPlanId);

    int insert(InterPayToPlan record);

    int insertSelective(InterPayToPlan record);

    List<InterPayToPlan> selectByExample(InterPayToPlanExample example);

    InterPayToPlan selectByPrimaryKey(Long interPlanId);

    int updateByExampleSelective(@Param("record") InterPayToPlan record, @Param("example") InterPayToPlanExample example);

    int updateByExample(@Param("record") InterPayToPlan record, @Param("example") InterPayToPlanExample example);

    int updateByPrimaryKeySelective(InterPayToPlan record);

    int updateByPrimaryKey(InterPayToPlan record);
}