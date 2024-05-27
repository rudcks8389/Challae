package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.match.mapper.CreateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateServiceImpl implements CreateService {

    private final CreateMapper createMapper;

}
