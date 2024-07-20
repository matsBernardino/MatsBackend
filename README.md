# Token Validator Application

## Sobre a Aplicação

Esta aplicação foi desenvolvida para validar tokens JWT (JSON Web Tokens). O serviço verifica a estrutura do token e suas claims, assegurando que atendem a critérios específicos de validação.
## Instruções

### Pré-requisitos

* Java 11 ou superior
* Maven 3.6 ou superior
* Git

### Passos para Execução

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/matsBernardino/MatsBackend.git
    cd MatsBackend
    ```

2. **Compile o projeto:**

    ```bash
    mvn clean install
    ```

3. **Execute a aplicação:**

    ```bash
    mvn spring-boot:run
    ```

4. **Testar a aplicação:**

   Para executar os testes unitários/integração, utilize:

    ```bash
    mvn test
    ```

## Descrição dos Métodos

### [`TokenService`](src/main/java/com/tokenvalidator/app/services/TokenService.java)

Esta classe centraliza as funcionalidades de validação de tokens e suas claims.

#### `validaEstruturaToken(Token token)`

Este método verifica se o token possui a estrutura correta, composta por três partes (header, payload, signature). Além disso, valida se cada parte contém apenas caracteres permitidos.

* **Parâmetros:**
    * [`Token`](src/main/java/com/tokenvalidator/app/model/Token.java): O token a ser validado.
* **Exceções:**
    * [`InvalidTokenException`](src/main/java/com/tokenvalidator/app/exceptions/InvalidTokenException.java): Lançada se o token não possui 3 partes ou contém caracteres inválidos.

#### `validaClaims(Token token)`

Este método extrai as claims do token e realiza várias validações:

* O número de claims deve ser exatamente 3.
* A claim `Name` não deve conter caracteres numéricos.
* O comprimento da claim `Name` não deve exceder 256 caracteres.
* A claim `Role` deve ser um dos valores permitidos: `Admin`, `Member`, `External`.
* A claim `Seed` deve ser um número primo.

* **Parâmetros:**
    * [`Token`](src/main/java/com/tokenvalidator/app/model/Token.java): O token a ser validado.
* **Exceções:**
    * [`InvalidClaimsException`](src/main/java/com/tokenvalidator/app/exceptions/InvalidClaimsException.java): Lançada se alguma das validações falhar.

#### `decodeToken(String token)`

Este método decodifica o token JWT e retorna suas claims.

* **Parâmetros:**
    * `String token`: O token JWT a ser decodificado.
* **Retorno:**
    * `Claims`: As claims extraídas do token.

### Exceções

* **[InvalidTokenException](src/main/java/com/tokenvalidator/app/exceptions/InvalidTokenException.java):** Lançada quando a estrutura do token é inválida.
* **[InvalidClaimsException](src/main/java/com/tokenvalidator/app/exceptions/InvalidClaimsException.java):** Lançada quando as claims do token são inválidas.

**Tratamento de Exceções:**
* Por razões de segurança, mensagens de erro detalhadas para falhas de autenticação são evitadas para mitigar possíveis ataques. Em vez disso, é retornada uma mensagem padrão fixa com um código de erro variável.
* Para obter mais detalhes sobre os erros, consulte os logs da aplicação.
