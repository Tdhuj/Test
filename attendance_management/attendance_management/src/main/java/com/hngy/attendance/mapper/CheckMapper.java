package com.hngy.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hngy.attendance.entity.Check;
import com.hngy.attendance.entity.dto.CheckDateDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CheckMapper extends BaseMapper<Check> {

    @Select("SELECT  date_format( start_date, '%Y-%m-%d' ) by_date, count(*) count_num  FROM t_check GROUP BY date_format( start_date, '%Y-%m-%d' );")
    List<CheckDateDTO> selectLeaveNumByDate();

    Integer leave();
}
