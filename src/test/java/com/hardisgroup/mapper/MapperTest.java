package com.hardisgroup.mapper;

import com.hardisgroup.common.model.Reference;
import com.hardisgroup.common.model.Report;
import com.hardisgroup.parser.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MapperTest {

    @InjectMocks
    Mapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void map() throws Exception {

    }
}