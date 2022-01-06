package br.com.smtech.controlegastos.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smtech.controlegastos.api.dto.MonthDTO;
import br.com.smtech.controlegastos.api.dto.MonthNewDTO;
import br.com.smtech.controlegastos.api.service.MonthService;
import br.com.smtech.controlegastos.library.dto.FilterBasicDto;

@RestController
@RequestMapping("/api/month")
public class MonthController {

    @Autowired
    private MonthService service;

    @GetMapping("/filter")
    public ResponseEntity<Page<MonthDTO>> findByFilter(HttpServletRequest request,
            FilterBasicDto filter) {
        return ResponseEntity.ok(service.findByFilter(filter));
    }

    @PostMapping
    public ResponseEntity<Long> newObject(HttpServletRequest request,
            @RequestBody MonthNewDTO dto) {
        return ResponseEntity.ok(service.newObject(dto));
    }
}
