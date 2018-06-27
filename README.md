## Proyecto de ejemplo de Pipelines en Jenkins

Este proyecto contiene tres maquinas virtuales con el jenkins, el sonar y un agente con docker

Para obtener las maquinas virtuales ejecutar 
```
vagrant up
```

Aqui podemos consultar un manual de referencia de jenkins pipeline: [Quick Reference](https://www.cloudbees.com/sites/default/files/declarative-pipeline-refcard.pdf)

Pipelines de ejemplo:
* [Pipline de proyecto maven](https://github.com/francescjp/jenkinspiplines/blob/master/pipes/maven2.groovy)
* [Pipe con analisis de sonar](https://github.com/francescjp/jenkinspiplines/blob/master/pipes/sonar.groovy)
* [Pipe con credentials](https://github.com/francescjp/jenkinspiplines/blob/master/pipes/saucelabs.groovy)
* [APlicacion node con container](https://github.com/francescjp/jenkinspiplines/blob/master/pipes/docker-node.groovy)

[Ejemplos sacados de aqui](https://bitbucket.org/itnove/jenkinsdeclarativepipelines)
