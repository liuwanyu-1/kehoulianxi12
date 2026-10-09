package lwy.study.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import lwy.study.mybatis.dao.ClazzMapper;
import lwy.study.mybatis.pojo.Clazz;
import lwy.study.mybatis.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clazzService")
public class ClazzServiceImpl implements IClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    public ClazzMapper getClazzMapper() {
        return clazzMapper;
    }

    public void setClazzMapper(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public List<Clazz> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Clazz> clazzes = clazzMapper.selectAll();
        return clazzes;
    }

    @Override
    public void add(Clazz clazz) {
        clazzMapper.insert(clazz);
    }

    /** 手写 limit 版：pageStart=(pageNum-1)*pageSize，公式必须在 Service 层算 */
    @Override
    public List<Clazz> selectByPageManual(int pageNum, int pageSize) {
        Integer pageStart = (pageNum - 1) * pageSize;
        return clazzMapper.selectByPage(pageStart, pageSize);
    }
}
