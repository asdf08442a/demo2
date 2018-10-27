package com.enterprise.demo.web.controller;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerUser {

  @Resource
  private MockMvc mvc;

  @Test
  public void getLocalDateTime() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/test/getLocalDateTime"))
        .andExpect(MockMvcResultMatchers.status().isOk());
//        .andExpect(MockMvcResultMatchers.content().string("365"));
  }
}