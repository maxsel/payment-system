package com.tofi.shop.service.impl;

import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.CategoryService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private ArrayList<ItemCategory> list = new ArrayList<>();
    {
        list.add(new ItemCategory(1, "cat1"));
        list.add(new ItemCategory(2, "cat2"));
        list.add(new ItemCategory(3, "cat3"));
        list.add(new ItemCategory(4, "cat4"));
        list.add(new ItemCategory(5, "cat5"));
    }

    @Override
    public Long create(ItemService item) throws ServiceException {
        return null;
    }

    @Override
    public void update(ItemService item) throws ServiceException {

    }

    @Override
    public void delete(Integer id) throws ServiceException {

    }

    @Override
    public List<ItemCategory> findAll() throws ServiceException {
        return list;
    }

    @Override
    public ItemCategory findById(Integer id) throws ServiceException {
        Optional<ItemCategory> result = list.stream().filter(cat -> Objects.equals(cat.getId(), id)).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
}
