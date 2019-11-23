package com.oltruong.moneycontrol.rule;

import com.oltruong.moneycontrol.HttpUtils;
import com.oltruong.moneycontrol.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleControllerTest {

    @Mock
    private RuleRepository mockRuleRepository;

    private RuleController ruleController;

    @BeforeEach
    public void setup() {
        ruleController = new RuleController(mockRuleRepository);
    }

    @Test
    public void findAll() {

        List<Rule> ruleList = Collections.singletonList(new Rule());
        when(mockRuleRepository.findAll()).thenReturn(ruleList);

        Iterable<Rule> ruleListReturned = ruleController.findAll();

        assertThat(ruleList).isEqualTo(ruleListReturned);
        verify(mockRuleRepository).findAll();
    }

    @Test
    public void editRule() {
        Rule rule = new Rule();
        when(mockRuleRepository.findById("id")).thenReturn(Optional.of(new Rule()));

        ruleController.editRule(rule, "id");

        verify(mockRuleRepository).findById(eq("id"));
        verify(mockRuleRepository).save(eq(rule));
    }

    @Test
    public void editRule_notfound() {
        when(mockRuleRepository.findById("id")).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                ruleController.editRule(null, "id"));
    }

    @Test
    public void createRule() {
        Rule rule = new Rule();
        HttpUtils.initRequestAttributes();

        Rule ruleSaved = new Rule();
        ruleSaved.setNameCondition("NAME");
        when(mockRuleRepository.save(rule)).thenReturn(ruleSaved);
        ResponseEntity<Rule> responseEntity = ruleController.createRule(rule);

        assertThat(responseEntity.getBody()).isEqualTo(ruleSaved);
        verify(mockRuleRepository).save(eq(rule));
    }

    @Test
    public void get() {
        Rule rule = new Rule();
        when(mockRuleRepository.findById("id")).thenReturn(Optional.of(rule));
        Rule ruleFound = ruleController.get("id");

        assertThat(ruleFound).isEqualTo(rule);
        verify(mockRuleRepository).findById(eq("id"));
    }

    @Test
    public void get_notfound() {
        when(mockRuleRepository.findById("id")).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                ruleController.get("id"));
    }


    @Test
    public void delete() {

        Rule rule = new Rule();
        when(mockRuleRepository.findById("id")).thenReturn(Optional.of(rule));
        ruleController.delete("id");

        verify(mockRuleRepository).delete(eq(rule));
    }

    @Test
    public void delete_notfound() {
        when(mockRuleRepository.findById("id")).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                ruleController.get("id"));
    }

}