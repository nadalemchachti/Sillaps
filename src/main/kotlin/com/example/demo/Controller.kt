package com.example.demo

/**
 * Created by Nada on 24/07/2017.
 */

import com.twilio.Twilio
import com.twilio.type.PhoneNumber
import com.twilio.rest.api.v2010.account.Message
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid
import com.twilio.exception.TwilioException
import org.springframework.stereotype.Controller

@Controller
class Controller {

    val ACCOUNT_SID: String = "AC49782ed0e93365857d9c1e7fb75e56b0"
    val AUTH_TOKEN: String = "c626d3a1861025e5bfeee13a79702bb6"
    fun sendSms(Phone: String , Msg: String) {
    Twilio.init(ACCOUNT_SID,AUTH_TOKEN)
    val message: Message = Message.creator(PhoneNumber(Phone), PhoneNumber("+15202141507"), Msg).create()
    println(message.sid)
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun start(Message: Model) : String {
        Message.addAttribute("Sms" , Sms())
        return "MessageView"
    }

    @RequestMapping(value = "/" , method = arrayOf(RequestMethod.POST))
    fun check(@Valid  Message: Sms) : String {

        try {
            sendSms(Message.num , Message.msg)
        }
        catch (E: TwilioException)
        {
            return "Error"
        }

        return "Result"

    }


}