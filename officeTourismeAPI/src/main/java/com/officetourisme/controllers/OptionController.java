package com.officetourisme.controllers;

import com.officetourisme.dtos.OptionDto;
import com.officetourisme.services.impl.OptionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {
    private final OptionServiceImpl optionService;

    public OptionController(OptionServiceImpl optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public List<OptionDto> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/{id}")
    public OptionDto getOption(@PathVariable Long id) {
        return optionService.getOptionById(id);
    }

    @PostMapping
    public OptionDto saveOption(final @RequestBody OptionDto optionDto) {
        return optionService.saveOption(optionDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteOption(@PathVariable Long id) {
        return optionService.deleteOption(id);
    }
}
