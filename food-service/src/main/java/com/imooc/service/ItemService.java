package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.bo.UserBO;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品id查询详情
     * @param
     * @return
     */
    public Items queryItemById( String itemId);

    /**
     *
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemList(String itemId);

    public List<ItemsSpec> queryItemSpecList( String itemId);

    public ItemsParam queryItemsParam( String itemId);
}
