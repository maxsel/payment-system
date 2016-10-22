package com.tofi.shop.service.impl;

import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.CategoryService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private ArrayList<ItemCategory> list = new ArrayList<>();
    {
        list.add(new ItemCategory(1L, "cat1"));
        list.add(new ItemCategory(2L, "cat2"));
        list.add(new ItemCategory(3L, "cat3"));
        list.add(new ItemCategory(4L, "cat4"));
        list.add(new ItemCategory(5L, "cat5"));
    }

    @Override
    public Long create(ItemService item) throws ServiceException {
        return null;
    }

    @Override
    public void update(ItemService item) throws ServiceException {

    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    @Override
    public List<ItemCategory> findAll() throws ServiceException {
        System.out.println(list);
        return list;
    }

    @Override
    public ItemCategory findById(Long id) throws ServiceException {
        Optional<ItemCategory> result = list.stream().filter(cat -> cat.getId() == id).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
}
