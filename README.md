Entregando Mercadorias
======================

O projeto consiste em "novo sistema" de logística visa que obter sempre a rota com o menor custo para a entrega de mercadorias. 

## Arquitetura

Considerando-se que o cálculo da rota com o menor custo pode ser encarado como uma especialização do [Problema do caminho mínimo](http://pt.wikipedia.org/wiki/Problema_do_caminho_m%C3%ADnimo), cujo objetivo é determinar o menor custo para a travessia de um grafo entre dois vértices, e que as informações devem ser persistidas para que elas não se percam entre os deployments da aplicação, a utilização de um banco de dados orientado a grafos parece-me uma escolha natural. Neste caso optei pela utilização do [Neo4j](http://www.neo4j.org).

A aplicação foi dividida em diferentes camadas lógicas com papéis bem definidos e fracamente acopladas, onde cada uma depende estritamente das camadas inferiores, maximizando assim a manutenabilidade do código. Além disso, a utilização do [Spring Framework] (http://projects.spring.io/spring-framework/) para a inversão de controle (IoC) e injeção de dependência (DI) aumenta a flexibilidade e testabilidade da aplicação. 

As funcionalidades do sistema foram expostas como APIs REST através do framework [Spring MVC] (http://projects.spring.io/spring-framework/) como forma de promover sua interoperabilidade com outros sistemas. Além disso, uma aplicação web de exemplo utilizando o [Dojo Toolkit](http://dojotoolkit.org/) foi criada para exemplificar a utilização destas APIs. 

Veja o [Diagrama de Componentes](https://googledrive.com/host/0B_F_ziKVZ1BNTXFCdGR1WWdPajQ/shortest-path-component-diagram.png) da aplicação.

Podemos ainda utilizar [balanceadores de carga e clusters](https://googledrive.com/host/0B_F_ziKVZ1BNTXFCdGR1WWdPajQ/sortest-path-architecture.png) para prover a alta disponibilidade e a tolerância a falhas ao sistema. A computação em nuvem pode ainda trazer outros benefícios como provisionamento e elasticidade.

A aplicação de exemplo está configurada para utilizar o Neo4j 

## Pré-Requisitos

- [Java Runtime Edition 7+](http://www.oracle.com/technetwork/java/javase/downloads/index.html?ssSourceSiteId=otnjp)

	Um tutorial para a instalação do java está disponível em [Como eu instalo o Java?](http://www.java.com/pt_BR/download/help/download_options.xml)

- [Apache Tomcat 7+](http://tomcat.apache.org/)

	Um tutorial para a instalação do Tomcat está disponível em [Instalando TomCat em 10min](http://imasters.com.br/artigo/8639/java/instalando-tomcat-em-10min/)

## Instalação da aplicação

Para instalar a aplicação, basta baixar o WAR disponível em [shortest-path.war](https://googledrive.com/host/0B_F_ziKVZ1BNTXFCdGR1WWdPajQ/shortest-path-component-diagram.png) e copiá-lo para o diretório TOMCAT_HOME/webapps.

Para confirmar que a instalação foi bem sucedida, basta acessar a [aplicação web de exemplo](http://localhost:8080/shortest-path).

## APIs


