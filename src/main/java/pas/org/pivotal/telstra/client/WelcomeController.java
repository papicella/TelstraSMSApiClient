package pas.org.pivotal.telstra.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class WelcomeController
{
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model)
    {
        Sms sms = new Sms();
        SmsWithBody smswithbody = new SmsWithBody();

        model.addAttribute("sms", sms);
        model.addAttribute("smswithbody", smswithbody);

        model.addAttribute("vcapservices", System.getenv("VCAP_SERVICES"));
        return "welcome";
    }

    @RequestMapping(value="/sendsms", method = RequestMethod.POST)
    public String sendsms
            (@RequestParam(value="to") String to,
             Model model)
    {

        RestTemplate restTemplate = new RestTemplate();
        model.addAttribute("vcapservices", System.getenv("VCAP_SERVICES"));

        Sms sms = new Sms();
        sms.setTo(to);
        model.addAttribute("sms", sms);
        model.addAttribute("smswithbody", new SmsWithBody());

        String url = String.format("http://apples-springboot-telstrasms.pcfdemo.net/telstra/sms?to=%s&appkey=7OdTnmeVbBAEFZLRnti2DrLtg2PPgf4E&appsecret=anaafk104rlzrZx7", to);
        String response = restTemplate.getForObject(url, String.class);

        logger.info("HTTP GET sent");

        model.addAttribute("actionStr", "Sent message to " + to + " with a a response as follows " + response);
        return "welcome";
    }

    @RequestMapping(value="/sendsmswithbody", method = RequestMethod.POST)
    public String sendsmswithbody
            (@RequestParam(value="to") String to,
             @RequestParam(value="body") String body,
             Model model)
    {

        RestTemplate restTemplate = new RestTemplate();


        SmsWithBody sms = new SmsWithBody();
        sms.setTo(to);
        sms.setBody(body);
        model.addAttribute("sms", new Sms());
        model.addAttribute("smswithbody", sms);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String url = String.format("http://apples-springboot-telstrasms.pcfdemo.net/telstra/sms?to=%s&body=%s&appkey=7OdTnmeVbBAEFZLRnti2DrLtg2PPgf4E&appsecret=anaafk104rlzrZx7", sms.getTo(), sms.getBody());

        String response = restTemplate.postForObject(url, headers, String.class);

        logger.info("HTTP POST sent");

        model.addAttribute("actionStr", "Sent message to " + to + " with a a response as follows " + response);
        return "welcome";
    }
}
