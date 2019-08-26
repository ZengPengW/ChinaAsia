package com.bootdo.api.service;

import com.bootdo.api.domain.ApiDo;

import java.util.List;

public interface ApiService {
    /**
     * 保存
     * @return
     */
    public void apiSave(ApiDo apiDo);

    /**
     * 更新
     */
    public void apiUpdate(ApiDo apiDo);
    /**
     * 查询所有
     * @return
     */
    public List<ApiDo> selectAll();

    /**
     * 根据id删除 api
     * @param id
     */
    public void deleteById(Long id);

    /**
     * 保存通用图片接口
     * @param apiDo
     */
    public void saveImagesApi(ApiDo apiDo);

    /**
     * 根据id 查询接口
     * @param id
     * @return
     */
    public ApiDo selectById(Long id);

    /**
     * 通用图片更新接口
     * @param apiDo
     */
    public void updateImagesApi(ApiDo apiDo);

    /**
     * 模糊搜索
     * @param likeName
     */
    public List<ApiDo> selectLikeName(String likeName);

    /**
     * 更新字段通用接口
     * @param apiDo
     */
    public  void updateFieldApi(ApiDo apiDo);

    /**
     * 保存通用字段接口
     * @param apiDo
     */
    public  void saveFieldApi(ApiDo apiDo);
}
