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

Para instalar a aplicação, basta baixar o WAR disponível em [shortest-path.war](https://googledrive.com/host/0B_F_ziKVZ1BNTXFCdGR1WWdPajQ/shortest-path.war) e copiá-lo para o diretório TOMCAT_HOME/webapps.

Para confirmar que a instalação foi bem sucedida, basta acessar a [aplicação web de exemplo](http://localhost:8080/shortest-path).

## APIs REST

#### POST /shortest-path/services/shipping/logisticsNetwork/{network-name}

Cria ou atualiza as informações da malha logística especificada. O formato de malha logística adotado é bastante simples, onde cada linha representa uma rota no formato descrito abaixo:

**origem** **destino** **distância**

```
A B 10
B D 15
A C 20
C D 30
B E 50
D E 30
```

*Cada rota será considerada como de sentido único entre a origem e o destino. Desta forma, no exemplo acima embora exista uma rota de A para B não há nenhuma rota de B para A.*

##### Requisição

Na URI da requisição deve se informar o nome da malha logísica que será criada ou atualizada. No corpo da mensagem, os dados referentes a malha logística.

**Representações aceitáveis**

- text/plain

Exemplo

```
POST /shortest-path/services/shipping/logisticsNetwork/Sample HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:28.0) Gecko/20100101 Firefox/28.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-us,en;q=0.8,pt-br;q=0.5,pt;q=0.3
Accept-Encoding: gzip, deflate
Content-Type: text/plain;charset=UTF-8
X-Requested-With: XMLHttpRequest
Referer: http://localhost:8080/shortest-path/
Content-Length: 41
Connection: keep-alive
Pragma: no-cache
Cache-Control: no-cache

A B 10
B D 15
A C 20
C D 30
B E 50
D E 30
```

#### Resposta

- 200

	Caso a requisição tenha sido processada com sucesso.

- 400

	Caso algum parâmetro inválido tenha sido fornecido.

- 500

	Caso tenha ocorrido algum erro durante o processamento da requisição.

#### GET /shortest-path/services/shipping/shippingDetails/{origin}/{destination}?vehicleMileage={vehicleMileage}&fuelPrice={fuelPrice}

Calcula a rota com o menor custo entre um ponto de origem e um ponto de destino informado, levando em consideração a autonomia do veículo e o preço do combustível informado.

##### Requisição

Na URI da requisição deve se informar a origem o destino da rota em questão. A autonomia do veículo e o preço do combustível devem ser incluídos como parâmetros de consulta da requisição.

Exemplo

```
GET /shortest-path/services/shipping/shippingDetails/A/D?vehicleMileage=10&fuelPrice=2.5 HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:28.0) Gecko/20100101 Firefox/28.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-us,en;q=0.8,pt-br;q=0.5,pt;q=0.3
Accept-Encoding: gzip, deflate
Content-Type: application/x-www-form-urlencoded
X-Requested-With: XMLHttpRequest
Referer: http://localhost:8080/shortest-path/
Connection: keep-alive
```

#### Resposta

- 200 - application/json

	Caso a requisição tenha sido processada com sucesso. No corpo da mensagem será retornada uma representação em JSON da rota encontrada.

Exemplo

```
	{
		"shippingRate":6.25,
		"shippingRoute": {
			"origin":"A",
			"destination":"D",
			"legs":[
				{
					"origin":"A",
					"destination":"B",
					"distance":10.0
				},
				{
					"origin":"B",
					"destination":"D",
					"distance":15.0
				}
			],
			"length":25.0
		}
	}
```

- 204

	Caso não exista nenhuma rota entre a origem e o destino

- 400

	Caso algum parâmetro inválido tenha sido fornecido.

- 500

	Caso tenha ocorrido algum erro durante o processamento da requisição.

## Desenvolvedores

A documentação das APIs Java do projeto está disponível em [Shortest Path API](https://googledrive.com/host/0B_F_ziKVZ1BNTXFCdGR1WWdPajQ/apidocs/index.html).

