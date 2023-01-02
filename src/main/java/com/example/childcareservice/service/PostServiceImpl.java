package com.example.childcareservice.service;


import com.example.childcareservice.dto.PostRequestDTO;
import com.example.childcareservice.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public List<PostRequestDTO> getList() {
        return postDAO.findAll();
    }

    @Override
    public List<PostRequestDTO> getList(String[] category, String budget) {
        log.info("정렬 조건 " + Arrays.toString(category) + " , " + budget);
//        return   postDAO.findAllByCategory(category);
        boolean length = category.length == 0;
        boolean equal = budget.equals("999999999");
        if (length && !equal) {
            return postDAO.findAllByBudget(budget);
        } else if (!length && equal) {
            return postDAO.findAllByCategory(category);
        }
        HashMap<String, Object> categories = new HashMap<>();
        categories.put("category", category);
        categories.put("budget", budget);
        return postDAO.findAllByCategoryBudget(categories);
    }
}
