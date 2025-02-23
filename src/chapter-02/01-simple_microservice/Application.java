package org.lyuucik;

@SpringBootApplication
@RestController
@RequestMapping(value="hello")
@EnableEurikaClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    public String helloRemoteServiceCall(String firstName, String lastName) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://logical-service-id/name/" + "{firstName}/{lastName}",
                        HttpMethod.GET, null, String.class,
                        firstName, lastName);
        return restExchange.getBody();
    }

    @RequestMapping(value="/{firstName}/{lastName}",
                    method = RequestMethod.GET)
    public String hello(@PathVariable("firstName") String firstName,
                        @PathVariable("lastName") String lastName) {
        return helloRemoteServiceCall(firstName, lastName);
    }
}