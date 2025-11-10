import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "<h1>WELCOME<h1>";
    }

    @GetMapping("/edit")
    public String edit(){
        return "<h1>EDIT PAGE<h1>";
    }
}
