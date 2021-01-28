# aprobador-pedidos
Demo arquitectura microservicios: backend flujo aprobador de pedidos

Este ejemplo demuestra el uso de los siguientes patrones de la arquitectura microservicios:

- Config server (Spring Cloud Config)
- Service discovery (Spring Cloud Netflix EurekaServer)
- Reverse Proxy, Filtering, Routing (Spring Cloud Netflix ZuulProxy)
- Security (ZuulProxy, Spring Security)
- LoadBalancing (ZuulProxy, Ribbon)
- Circuit Breaker (ZuulProxy, Hystrix)
- Event-driven:
     * Streams/ Messaging (Spring Cloud Streams)
     * Streams processor (Apache Kafka)
- Datasource aislado dentro de cada servicio
- Log centralizado (Elasticsearch, Logstash, Kibana)

Despliegues:

- Despliegue en contenedores (docker, docker-compose)
- Despliegue en Amazon Web Services EC2
- Despliegue en Amazon Web Services ECS (Elastic Container Service

Mas detalles en el PDF de la documentación: https://github.com/rod-ag/aprobador-pedidos/blob/main/Documentacion.pdf

