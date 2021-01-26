package com.example.springtransactionaldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springtransactionaldemo.model.Score;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ScoreMapper extends BaseMapper<Score> {
}
