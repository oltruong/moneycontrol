package com.oltruong.moneycontrol.controller;

import com.oltruong.moneycontrol.exception.ResourceNotFoundException;
import com.oltruong.moneycontrol.model.Rule;
import com.oltruong.moneycontrol.repository.RuleRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Olivier Truong
 */
@RestController
public class RuleController {

    private final RuleRepository ruleRepository;

    public RuleController(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @RequestMapping(value = "/rest/rules", method = RequestMethod.GET)
    Iterable<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @RequestMapping(value = "/rest/rules/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void editRule(@RequestBody Rule rule, @PathVariable String id) {

        if (ruleRepository.findById(id) == null) {
            throw new ResourceNotFoundException();
        }
        rule.setId(id);
        ruleRepository.save(rule);

    }

    @RequestMapping(value = "/rest/rules", method = RequestMethod.POST)
    ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        rule.setId(null);

        Rule ruleSaved = ruleRepository.save(rule);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(ruleSaved.getId()).toUri());

        return new ResponseEntity<>(ruleSaved, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/rules/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    Rule get(@PathVariable String id) {
        return getRuleOrThrowException(id);
    }

    private Rule getRuleOrThrowException(@PathVariable String id) {
        return ruleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/rest/rules/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        Rule rule = getRuleOrThrowException(id);
        ruleRepository.delete(rule);
    }
}
