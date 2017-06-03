package com.oltruong.moneycontrol.exception;

import com.oltruong.moneycontrol.exception.CustomErrorController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomErrorControllerTest {

    @Mock
    private HttpServletResponse mockResponse;

    private CustomErrorController customErrorController;

    @Before
    public void setup() {
        customErrorController = new CustomErrorController();
    }

    @Test
    public void error() throws Exception {
        customErrorController.error(mockResponse);
        verify(mockResponse).sendRedirect("/index.html");
    }

    @Test
    public void getErrorPath() throws Exception {

        assertThat(customErrorController.getErrorPath()).isEqualTo("/error");
    }

}