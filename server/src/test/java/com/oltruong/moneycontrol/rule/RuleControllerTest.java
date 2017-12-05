package com.oltruong.moneycontrol.rule;

import com.oltruong.moneycontrol.HttpUtils;
import com.oltruong.moneycontrol.exception.ResourceNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RuleControllerTest {

    @Mock
    private RuleRepository mockRuleRepository;


    private RuleController ruleController;

    @Before
    public void setup() throws Exception {
        ruleController = new RuleController(mockRuleRepository);
    }

    @Test
    public void findAll() throws Exception {

        List<Rule> ruleList = Collections.singletonList(new Rule());
        when(mockRuleRepository.findAll()).thenReturn(ruleList);

        Iterable<Rule> ruleListReturned = ruleController.findAll();

        assertThat(ruleList).isEqualTo(ruleListReturned);
        verify(mockRuleRepository).findAll();
    }

    @Test
    public void editRule() throws Exception {
        Rule rule = new Rule();
        when(mockRuleRepository.findById("id")).thenReturn(Optional.of(new Rule()));

        ruleController.editRule(rule,"id");

        verify(mockRuleRepository).findById(eq("id"));
        verify(mockRuleRepository).save(eq(rule));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void editRule_notfound() throws Exception {
        when(mockRuleRepository.findById("id")).thenReturn(Optional.empty());
        ruleController.editRule(null, "id");
    }

    @Test
    public void createRule() throws Exception {
        Rule rule = new Rule();
        HttpUtils.initRequestAttributes();

        Rule ruleSaved= new Rule();
        ruleSaved.setNameCondition("NAME");
        when(mockRuleRepository.save(rule)).thenReturn(ruleSaved);
        ResponseEntity<Rule> responseEntity = ruleController.createRule(rule);

        assertThat(responseEntity.getBody()).isEqualTo(ruleSaved);
        verify(mockRuleRepository).save(eq(rule));
    }

    @Test
    public void get() throws Exception {
        Rule rule = new Rule();
        when(mockRuleRepository.findById("id")).thenReturn(Optional.of(rule));
        Rule ruleFound = ruleController.get("id");

        assertThat(ruleFound).isEqualTo(rule);
        verify(mockRuleRepository).findById(eq("id"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_notfound() throws Exception {
        when(mockRuleRepository.findById("id")).thenReturn(Optional.empty());
        ruleController.get("id");
    }

    @Test
    public void delete() throws Exception {

        Rule rule = new Rule();
        when(mockRuleRepository.findById("id")).thenReturn(Optional.of(rule));
        ruleController.delete("id");

        verify(mockRuleRepository).delete(eq(rule));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_notfound() throws Exception {
        when(mockRuleRepository.findById("id")).thenReturn(Optional.empty());
        ruleController.delete("id");
    }

}