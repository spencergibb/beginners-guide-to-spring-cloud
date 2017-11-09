Simple app. Easy to understand. Concepts and tools.
App will produce a greeting. There will be three services, name, greeting and frontend that combines them together.

Service Discovery, Config Server and Zipkin, servers, infrastructure. Largely the same regardless of what you do. spring cloud cli spins up servers on laptop for a nice development environment. Extension of the spring boot cli.

spring cloud configserver eureka zipkin

Where is configserver getting config from. You can configure each one with a yaml file and specify configuration.

show application.yml, for all services, then each service

show config server and eurkeka services and zipkin.

Greeting Service. start.spring.io 'greeting', web, actuator, config client, eureka client, sleuth, zipkin
spring.application.name=greeting
used by config server, matches `greeting.yml`. name of app registered in eureka.
@RestController
@EnableDiscoveryClient
@Value("${greeting}")
Log

@RequestMapping
log.info greeting
return greeting
AlwaysSampler bean so sleuth knows when to send information to zipkin. Useful with high traffic systems.

Show fetching config from config server (/env) 9090
Show logging and sleuth information: trace id, span, sampled

Show eureka, that greeting is registered. Ziping to see tracing.

start.spring.io name, web, actuator, config, eureka, sleuth, zipkin
spring.application.name=name
same as above but ${name}

start.spring.io web: web, actuator, config, eureka, sleuth, zipkin, ribbon, feign, zuul

Explain hystrix used in zuul

spring.application.name=web

@EnableDiscoveryClient
@RestController
Log

@RequestMapping
RestTemplateBuilder, to localhost:9090
localhost:8080
Add @LoadBalanced to RestTemplate
change localhost:9090 -> greeting

Call name service, use feign

@EnableZuulProxy

