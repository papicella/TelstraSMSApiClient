package ibm.org.bluemix.telstra.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pas on 20/07/15.
 */
@Controller
public class WelcomeController
{
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model)
    {
        Sms sms = new Sms();
        model.addAttribute("sms", sms);
        model.addAttribute("vcapservices", System.getenv("VCAP_SERVICES"));
        return "welcome";
    }

    @RequestMapping(value="/sendsms", method = RequestMethod.POST)
    public String addAlbum
            (@RequestParam(value="to") String to,
             Model model)
    {

        RestTemplate restTemplate = new RestTemplate();
        model.addAttribute("vcapservices", System.getenv("VCAP_SERVICES"));

        Sms sms = new Sms();
        sms.setTo(to);
        model.addAttribute("sms", sms);

        String url = String.format("http://pas-telstrasmsapi.mybluemix.net/telstrasms?to=%s", to);
        String response = restTemplate.getForObject(url, String.class);

        model.addAttribute("actionStr", "Sent message to " + to + " with a a response as follows " + response);
        return "welcome";
    }
}
