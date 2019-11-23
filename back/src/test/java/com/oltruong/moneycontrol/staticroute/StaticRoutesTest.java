package com.oltruong.moneycontrol.staticroute;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StaticRoutesTest {
    @Test
    public void index() throws Exception {
        final StaticRoutes staticRoutes = new StaticRoutes();
        assertThat(staticRoutes.index()).isEqualTo("forward:/index2.html");
    }

}