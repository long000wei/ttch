package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {


    public List<UserAddress> queryAll(String userId);

    public void addNewUserAddress(AddressBO addressBO);

    public void updateUserAddress(AddressBO addressBO);

    public void deleteUserAddress(String userId,String addressId);

    public void updateUserAddressToDefault(String userId,String addressId);

    public UserAddress queryUserAddress(String userId,String addressId);

}
