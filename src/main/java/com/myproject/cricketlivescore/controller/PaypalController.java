package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    private final PaypalService paypalService;

    @GetMapping("/")
    public String home()
    {
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment()
    {
        try {
            String cancelUrl = "https://localhost:6969/payment/cancel";
            String successUrl = "https://localhost:6969/payment/success";
            Payment payment = paypalService.createPayment(10.0
                    ,"USD"
            ,"paypal"
            ,"sale"
            ,"Payment Description"
            ,cancelUrl
            ,successUrl);

            for(Links link : payment.getLinks())
            {
                if(link.getRel().equals("approve_url"))
                {
                    return new RedirectView(link.getHref());
                }
            }
        }
        catch (PayPalRESTException e) {
            log.error("ERROR!!! : " + e.getMessage());
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerId") String payerId
    ){
        try {
            Payment payment = paypalService.executePayment(paymentId,payerId);
            if(payment.getState().equals("approved"))
            {
                return "PaymentSuccess";
            }
        }
        catch (Exception e) {
            log.error("ERROR!!! : " + e.getMessage());
        }
        return "paymentSuccess";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel()
    {
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError()
    {
        return "paymentError";
    }



}
