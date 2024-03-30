
# bcb-backoffice

### Projeto proposto como desafio técnico

------------------------------------------------------------

#### Instruções para execução (necessário ter o docker/docker-compose instalado)

##### Ambiente de produção

1.  Alterar o arquivo `docker-compose.yaml` na raiz do projeto, realizando as configurações desejadas (acesso ao banco, filas, etc.). Caso o mesmo não seja alterado, a aplicação irá subir com os valores default. 
2.  executar o comando `docker-compose up`

##### Ambiente de testes
1.  Alterar o arquivo `application.yaml` e `application-test.yaml` na raiz do projeto, realizando as configurações desejadas. Caso o mesmo não seja alterado, a aplicação irá subir com os valores default. 
2.  executar o comando `mvn spring-boot:run -Dspring-boot.run.profiles=test`

------------------------------------------------------------

## Premissas

*   A sistemática de login utilizará Json Web Tokens (JWT), definindo os seguintes papéis: "customer", "employee"
*   Cada cliente deverá pertencer somente a uma empresa
*   O envio das mensagens será feito por um serviço a parte, sendo a comunicação ente ambos definida através de "filas"
*   Cada tipo de mensagem (sms, whatsapp) terá uma fila correspondente
*   A cobrança pela transação de envio das mensagens será feita no momento em que a solicitação de envio é realizada
*   Caso a mensagem não seja processada corretamente pelo serviço externo, o mesmo realizará a comunicação deste erro através de uma fila específica para este fim
*   Caso uma comunicação de erro no envio da mensagem seja realizada, uma transação de reembolso será criada incrementando o saldo do cliente com o mesmo valor cobrado pelo envio
*   A sistemática de cobrança pelo envio das mensagens será feita através da definição de um saldo e de um limite para cada cliente
*   Caso o custo de envio da mensagem seja superior ao saldo atual do cliente, e o mesmo não possuir limite suficiente para a efetivação da transação, então a mensagem não será enviada
*   Será possível realizar transação de depósitos para incrementar o saldo atual do cliente
*   Será possível alterar o limite do cliente a qualquer momento, via requisição

## Observações

*   Não foi possível, devido ao prazo, desenvolver todas as funcionalidades apresentadas nas premissas (como o login e todos os testes automatizados, por exemplo)
*   Criei uma implementação simples do receiver das confimações de envio das mensagens neste projeto apenas para exemplificar o funcionamento
*   Minha proposta de solução consiste em 3 microserviços: bcb-backoffice, bcb-message-sender e bcb-message-callback, onde:
    *   O bcb-backoffice teria basicamente as funcionalidades que tem hoje, porém fazendo o devido controle de autenticação e autorização
    *   O bcb-message-sender seria o responsável por consumir os comandos enviados via fila pelo bcb-backoffice, tentar realizar o envio das mensagens e, por fim, enviar um comando via fila para o bcb-message-callback
    *   O bcb-message-callback seria responsável por receber o status da mensagem e, caso este status fosse de erro, o mesmo deveria criar uma transação de reembolso para o cliente

