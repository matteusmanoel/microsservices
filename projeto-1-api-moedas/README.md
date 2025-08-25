# API de Cotações de Moedas

API REST desenvolvida com Quarkus para obter cotações de moedas em tempo real através da integração com a AwesomeAPI.

## 🚀 Tecnologias

- **Java 17**
- **Quarkus 3.24.3** - Framework Java nativo para cloud
- **Gradle** - Gerenciador de dependências
- **REST Client** - Cliente HTTP para integração com APIs externas
- **OpenAPI/Swagger** - Documentação da API
- **Health Check** - Monitoramento de saúde da aplicação

## 📋 Pré-requisitos

- Java 17 ou superior
- Gradle 8.x
- Docker (opcional, para containers)

## 🛠️ Configuração e Execução

### 1. Clone o repositório

```bash
git clone https://gitlab.com/matteusmanoel/projeto-1-api-moedas.git
cd projeto-1-api-moedas
```

### 2. Execute em modo desenvolvimento

```bash
./gradlew quarkusDev
```

A aplicação estará disponível em: http://localhost:8080

### 3. Build da aplicação

```bash
./gradlew build
```

### 4. Executar aplicação standalone

```bash
java -jar build/quarkus-app/quarkus-run.jar
```

## 📚 Endpoints da API

### 1. Listar Moedas Disponíveis

```http
GET /api/currency/available
```

**Resposta:**

```json
["USD", "EUR", "BRL", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "BTC", "ETH"]
```

### 2. Obter Cotação de Moeda

```http
GET /api/currency/quote/{from}/{to}
```

**Exemplo:**

```bash
curl -X GET "http://localhost:8080/api/currency/quote/USD/BRL"
```

**Resposta:**

```json
{
  "code": "USD",
  "codein": "BRL",
  "name": "Dólar Americano/Real Brasileiro",
  "high": 5.5916,
  "low": 5.54031,
  "varBid": 0.011,
  "pctChange": 0.197986,
  "bid": 5.5669,
  "ask": 5.5699,
  "timestamp": 1752705813,
  "createDate": null
}
```

### 3. Obter Múltiplas Cotações

```http
GET /api/currency/quotes/{base}?currencies={moedas}
```

**Exemplo:**

```bash
curl -X GET "http://localhost:8080/api/currency/quotes/USD?currencies=BRL,EUR,JPY"
```

## 🔧 Configurações

As configurações estão no arquivo `src/main/resources/application.properties`:

```properties
# API externa
br.com.itaipu.client.AwesomeApiClient/mp-rest/url=https://economia.awesomeapi.com.br

# Servidor
quarkus.http.port=8080
quarkus.http.host=0.0.0.0
```

## 📊 Monitoramento

- **Health Check:** http://localhost:8080/q/health
- **OpenAPI/Swagger UI:** http://localhost:8080/q/swagger-ui
- **Dev UI:** http://localhost:8080/q/dev (apenas em modo dev)

## 🏗️ Estrutura do Projeto

```
src/main/java/br/com/itaipu/
├── api/
│   └── CurrencyResource.java      # Endpoints REST
├── client/
│   └── AwesomeApiClient.java      # Cliente para API externa
├── model/
│   └── CurrencyQuote.java         # Modelo de dados
└── service/
    └── CurrencyService.java       # Lógica de negócio
```

## 📝 Modelo de Dados

### CurrencyQuote

| Campo        | Tipo       | Descrição                   |
| ------------ | ---------- | --------------------------- |
| `code`       | String     | Código da moeda base        |
| `codein`     | String     | Código da moeda de destino  |
| `name`       | String     | Nome descritivo do par      |
| `high`       | BigDecimal | Maior valor no período      |
| `low`        | BigDecimal | Menor valor no período      |
| `varBid`     | BigDecimal | Variação do valor de compra |
| `pctChange`  | BigDecimal | Variação percentual         |
| `bid`        | BigDecimal | Valor de compra             |
| `ask`        | BigDecimal | Valor de venda              |
| `timestamp`  | Long       | Timestamp da cotação        |
| `createDate` | Long       | Data de criação             |

## 🧪 Testes

```bash
# Executar testes
./gradlew test

# Executar testes com cobertura
./gradlew test jacocoTestReport
```

## 📦 Deploy

### Docker

```bash
# Build da imagem
./gradlew build -Dquarkus.package.type=docker

# Executar container
docker run -p 8080:8080 projeto-1-api-moedas:1.0.0-SNAPSHOT
```

### Native Image

```bash
# Build nativo
./gradlew build -Dquarkus.native.enabled=true

# Executar
./build/projeto-1-api-moedas-1.0.0-SNAPSHOT-runner
```

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Matteus Manoel**

- GitLab: [@matteusmanoel](https://gitlab.com/matteusmanoel)

---

**Desenvolvido como parte do roteiro de estudos em Quarkus, Kafka e Clean Architecture.**
