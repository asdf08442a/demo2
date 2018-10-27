package com.enterprise.demo.core.service;

import java.io.File;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MailServiceTest {

  @Resource
  private MailService mailService;

  @Test
  public void sendSimpleMail() {
    mailService.sendSimpleMail("18330068303@139.com", "简单文本", "hello");
  }

  @Test
  public void sendAttachmentsMail() {
    mailService.sendAttachmentsMail("18330068303@139.com", "附件", "hello",
        new File("D:\\picture\\1395797496930.jpg"));
  }
}