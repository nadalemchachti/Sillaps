/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Nada
 */
@Controller 
public class MController extends WebMvcConfigurerAdapter{

     public static final String ACCOUNT_SID = "AC49782ed0e93365857d9c1e7fb75e56b0";
    public static final String AUTH_TOKEN = "c626d3a1861025e5bfeee13a79702bb6";

  public static void SendMessage(String Phone , String Msg) throws TwilioException
  {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(new PhoneNumber(Phone),new PhoneNumber("+15202141507"),Msg).create();
    System.out.println(message.getSid());
  }
  
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model Message) {
                     
        Message.addAttribute("MessageM", new MessageM());
        return "MessageView";
    }
  

    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String validateUser(@Valid MessageM Message, BindingResult bindingResult ,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
           
            return "MessageView";
        }
        redirectAttributes.addAttribute("Num", Message.getNum());
        redirectAttributes.addAttribute("message", Message.getMsg());
        try{
            SendMessage(Message.getNum(), Message.getMsg());
        }
        catch(TwilioException E)
        {
            return "Error";
        }
        
        return "Result";
    }
   

     
   
}
