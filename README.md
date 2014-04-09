shortest-path
=============

## Descrição

Entregando Mercadorias

O Walmart esta desenvolvendo um novo sistema de logistica e sua ajuda é muito
importante neste momento. Sua tarefa será desenvolver o novo sistema de
entregas visando sempre o menor custo. Para popular sua base de dados o sistema
precisa expor um Webservices que aceite o formato de malha logÃ­stica (exemplo
abaixo), nesta mesma requisição o requisitante deverá informar um nome para este
mapa. É importante que os mapas sejam persistidos para evitar que a cada novo
deploy todas as informações desapareçam. O formato de malha logística é bastante
simples, cada linha mostra uma rota: ponto de origem, ponto de destino e
distância entre os pontos em quilômetros.

A B 10
B D 15
A C 20
C D 30
B E 50
D E 30

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e
seu caminho, para isso ele passará o nome do ponto de origem, nome do ponto de
destino, autonomia do caminhão (km/l) e o valor do litro do combustivel, agora
sua tarefa é criar este Webservice. Um exemplo de entrada seria, origem A,
destino D, autonomia 10, valor do litro 2,50; a resposta seria a rota A B D com
custo de 6,75.

Voce está livre para definir a melhor arquitetura e tecnologias para solucionar
este desafio, mas não se esqueçaa de contar sua motivação no arquivo README que
deve acompanhar sua solução, junto com os detalhes de como executar seu programa.
Documentação e testes serão avaliados também =) Lembre-se de que iremos executar
seu código com malhas beeemm mais complexas, por isso é importante pensar em
requisitos não funcionais também!!

Também gostariamos de acompanhar o desenvolvimento da sua aplicação através do
código fonte. Por isso, solicitamos a criação de um repositório que seja
compartilhado com a gente. Para o desenvolvimento desse sistema, nós solicitamos
que você utilize a sua (ou as suas) linguagem de programação principal. Pode ser
Java, JavaScript ou Ruby.

## Motivação

O problema acima descrito assemelha-se ao [Problema do caminho mínimo]
(http://pt.wikipedia.org/wiki/Problema_do_caminho_m%C3%ADnimo) cujo objetivo é
determinar o menor custo para a travessia de um grafo entre dois vértices.
Considerando-se a necessidade de persistência dos mapas, a utilização de um
banco de dados orientado a grafos torna-se natural. Optei pelo banco de dados
[Neo4j](http://www.neo4j.org) devido a sua alta performance, robustez e,
obviamente, a sua natureza orientada a grafos.
Visando ainda a simplicidade do código, o baixo acoplamento e melhoria da
testabilidade da aplicação será utilizado o framework [Spring Framework]
(http://projects.spring.io/spring-framework/) para a injeção de dependências.
