package com.mahedi.reactivedemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@CrossOrigin("/*")
@RequiredArgsConstructor
public class PaymentLogController {

}
