# 🚀 ITAIPU - Sistema de Microsserviços

Sistema de microsserviços desenvolvido em **Java 17** com **Quarkus**, implementando arquitetura distribuída com comunicação entre serviços via **HTTP REST**. O projeto demonstra boas práticas de desenvolvimento de microsserviços, containerização com Docker e integração com APIs externas.

## 🏗️ Arquitetura do Sistema

<img width="711" height="558" alt="image" src="https://github.com/user-attachments/assets/b1c38482-b692-4901-acd0-40fe6f6718e5" />

### **Características da Arquitetura**

- **Microsserviços independentes** com responsabilidades bem definidas
- **Comunicação síncrona** via HTTP REST
- **Banco de dados separado** para cada contexto
- **Containerização completa** para facilitar deploy
- **Health checks** para monitoramento
- **Documentação automática** com OpenAPI/Swagger

## 🎯 Funcionalidades Implementadas

### **💰 API de Moedas (Porta 8080)**

- **Listar moedas disponíveis** - `GET /api/currency/available`
- **Obter cotação específica** - `GET /api/currency/quote/{from}/{to}`
- **Obter múltiplas cotações** - `GET /api/currency/quotes/{base}?currencies={moedas}`
- **Integração com AwesomeAPI** para cotações em tempo real

### **🛒 Sistema de Carrinho (Porta 8081)**

- **Gestão de Produtos:**
  - CRUD completo de produtos
  - Categorização e busca
  - Controle de estoque
- **Gestão de Carrinhos:**
  - Criação e gestão de carrinhos
  - Adição/remoção de itens
  - Cálculo de totais com conversão de moedas
  - Integração com API de moedas para conversões

## 🚀 Tecnologias Utilizadas

- **Backend:** Java 17, Quarkus 3.24.3
- **Build Tool:** Gradle
- **Banco de Dados:** PostgreSQL 15
- **Containerização:** Docker, Docker Compose
- **Comunicação:** HTTP REST, REST Client
- **Documentação:** OpenAPI/Swagger
- **Monitoramento:** Health Checks, Logs estruturados

## 📁 Estrutura do Projeto

```
microsservices/
├── projeto-1-api-moedas/          # API de Cotações de Moedas
│   ├── src/main/java/br/com/itaipu/
│   │   ├── api/                   # Controllers REST
│   │   ├── client/                # Cliente para API externa
│   │   ├── model/                 # Modelos de dados
│   │   └── service/               # Lógica de negócio
│   ├── src/main/resources/
│   │   └── application.properties # Configurações
│   └── src/main/docker/
│       └── Dockerfile.jvm         # Container JVM
├── projeto-2-system-cart/         # Sistema de Carrinho de Compras
│   ├── src/main/java/br/com/itaipu/
│   │   ├── api/                   # Controllers REST
│   │   ├── entity/                # Entidades JPA
│   │   ├── repository/            # Repositórios Panache
│   │   ├── service/               # Lógica de negócio
│   │   └── client/                # Cliente para API de moedas
│   ├── src/main/resources/
│   │   └── application.properties # Configurações
│   └── src/main/docker/
│       └── Dockerfile.jvm         # Container JVM
├── docker-compose.yml             # Orquestração dos containers
└── README.md                      # Esta documentação
```

## ⚠️ Pré-requisitos

- **Java 17** ou superior
- **Docker** e **Docker Compose**
- **Git** para controle de versão

## 🚀 Como Executar

### **Execução Rápida com Docker**

```bash
# 1. Clone o repositório
git clone https://github.com/matteusmanoel/microsservices.git
cd microsservices

# 2. Execute com Docker Compose
docker-compose up --build

# 3. Acesse as APIs:
# - API Moedas: http://localhost:8080
# - System Cart: http://localhost:8081
# - PostgreSQL: localhost:5432
```

### **Desenvolvimento Local**

```bash
# API Moedas
cd projeto-1-api-moedas
./gradlew quarkusDev

# System Cart (em outro terminal)
cd projeto-2-system-cart
./gradlew quarkusDev
```

## 🧪 Testes e Validação

### **Endpoints de Monitoramento**

- **Health Checks:**
  - API Moedas: `http://localhost:8080/q/health`
  - System Cart: `http://localhost:8081/health`
- **Documentação:**
  - API Moedas: `http://localhost:8080/q/swagger-ui`
  - System Cart: `http://localhost:8081/swagger-ui`

### **Testes via Postman**

Collection completa disponível para importação com todos os endpoints documentados e organizados por funcionalidade.

### **🧪 Estratégia de Testes**

#### **Testes Unitários (Fase 1)**

```bash
# Executar testes unitários
cd projeto-1-api-moedas
./gradlew test

cd ../projeto-2-system-cart
./gradlew test
```

#### **Testes de Integração (Fase 1)**

```bash
# Executar testes de integração
cd projeto-1-api-moedas
./gradlew integrationTest

cd ../projeto-2-system-cart
./gradlew integrationTest
```

#### **Cobertura de Código**

```bash
# Gerar relatório de cobertura
cd projeto-1-api-moedas
./gradlew jacocoTestReport

cd ../projeto-2-system-cart
./gradlew jacocoTestReport
```

### **📊 Métricas de Qualidade**

- **Cobertura de Código:** Meta > 80%
- **Testes Unitários:** Todos os serviços principais
- **Testes de Integração:** Fluxos end-to-end
- **Testes de Performance:** Latência < 200ms

## 🔮 Roadmap Estratégico - Plano de Evolução

### **Fase 1: Testes e Qualidade (Semana 1)**

- [ ] **Testes Unitários** com JUnit 5 e Mockito
  - [ ] Testes para API de Moedas
  - [ ] Testes para Sistema de Carrinho
  - [ ] Cobertura de código e qualidade
- [ ] **Testes de Integração** com Testcontainers
  - [ ] Testes end-to-end das APIs
  - [ ] Validação de comunicação entre serviços
  - [ ] Testes com banco de dados real

### **Fase 2: Mensageria e Eventos (Semana 2)**

- [ ] **Integração com Apache Kafka**
  - [ ] Configuração Kafka + Zookeeper no Docker
  - [ ] Eventos de carrinho (item adicionado, removido, carrinho limpo)
  - [ ] Eventos de cotação de moedas
  - [ ] Comunicação assíncrona entre serviços
- [ ] **Arquitetura Event-Driven**
  - [ ] Produtores e consumidores de eventos
  - [ ] Padrões de mensageria (Pub/Sub)
  - [ ] Resiliência e Dead Letter Queues

### **Fase 3: Segurança e Autenticação (Semana 3)**

- [ ] **Integração com Keycloak**
  - [ ] Configuração de realm e clientes
  - [ ] Usuários, roles e políticas de acesso
  - [ ] Single Sign-On entre serviços
- [ ] **Segurança nas APIs**
  - [ ] Autenticação JWT
  - [ ] Autorização baseada em roles (RBAC)
  - [ ] Endpoints protegidos e públicos
  - [ ] Auditoria de acessos

### **Fase 4: Performance e Escalabilidade (Semana 4)**

- [ ] **Testes de Carga e Performance**
  - [ ] Configuração JMeter ou K6
  - [ ] Cenários de teste (produtos, carrinhos, cotações)
  - [ ] Métricas de throughput, latência e error rate
  - [ ] Análise de gargalos e otimizações
- [ ] **Monitoramento e Observabilidade**
  - [ ] Métricas com Prometheus
  - [ ] Logs centralizados com ELK Stack
  - [ ] Health checks avançados
  - [ ] Alertas e dashboards

### **Fase 5: Infraestrutura Avançada (Futuro)**

- [ ] **Cache e Performance**
  - [ ] Cache distribuído com Redis
  - [ ] Otimizações de consultas
  - [ ] Connection pooling
- [ ] **Escalabilidade**
  - [ ] Load balancing
  - [ ] Auto-scaling com Kubernetes
  - [ ] Service mesh (Istio)
- [ ] **Resiliência**
  - [ ] Circuit breakers
  - [ ] Retry policies
  - [ ] Fallback strategies

### **📊 Cronograma de Implementação**

| Semana | Fase        | Foco                           | Entregáveis                          |
| ------ | ----------- | ------------------------------ | ------------------------------------ |
| **1**  | Testes      | Qualidade e Confiabilidade     | Testes unitários + integração        |
| **2**  | Kafka       | Mensageria Assíncrona          | Eventos + comunicação entre serviços |
| **3**  | Keycloak    | Segurança e Autenticação       | APIs protegidas + SSO                |
| **4**  | Performance | Escalabilidade e Monitoramento | Testes de carga + observabilidade    |

### **🎯 Objetivos de Aprendizado por Fase**

- **Fase 1:** Fundamentos de testing, TDD, qualidade de código
- **Fase 2:** Arquitetura de mensageria, padrões assíncronos, resiliência
- **Fase 3:** OAuth 2.0, JWT, segurança em microsserviços, RBAC
- **Fase 4:** Performance testing, métricas, monitoramento, análise de dados
- **Fase 5:** Infraestrutura cloud-native, escalabilidade, DevOps avançado

## 📊 Status Atual e Progresso

### **✅ Fase 0: MVP Completo (Concluído)**

- ✅ **API Moedas:** Funcionando perfeitamente
- ✅ **System Cart:** Funcionando perfeitamente
- ✅ **Comunicação:** Funcionando entre os serviços
- ✅ **Banco de Dados:** Funcionando com dados iniciais
- ✅ **Docker:** Funcionando perfeitamente
- ✅ **Documentação:** README completo e APIs documentadas

### **🔄 Fase 1: Testes e Qualidade (Em Planejamento)**

- [ ] **Testes Unitários:** JUnit 5 + Mockito
- [ ] **Testes de Integração:** Testcontainers + E2E
- [ ] **Cobertura de Código:** Relatórios de qualidade

### **⏳ Fase 2: Mensageria e Eventos (Pendente)**

- [ ] **Apache Kafka:** Configuração e integração
- [ ] **Eventos Assíncronos:** Comunicação entre serviços
- [ ] **Arquitetura Event-Driven:** Padrões de mensageria

### **⏳ Fase 3: Segurança e Autenticação (Pendente)**

- [ ] **Keycloak:** Configuração e integração
- [ ] **JWT:** Autenticação e autorização
- [ ] **RBAC:** Controle de acesso baseado em roles

### **⏳ Fase 4: Performance e Monitoramento (Pendente)**

- [ ] **Testes de Carga:** JMeter/K6 + métricas
- [ ] **Observabilidade:** Prometheus + ELK Stack
- [ ] **Dashboards:** Monitoramento em tempo real

### **📈 Próximos Passos**

1. **Implementar testes unitários** para ambas as APIs
2. **Configurar testes de integração** com Testcontainers
3. **Preparar base** para integração com Kafka

### **🚀 Próximas Fases de Desenvolvimento**

#### **Semana 1: Testes e Qualidade**

- Implementação de testes unitários com JUnit 5
- Configuração de testes de integração
- Relatórios de cobertura de código
- Validação de qualidade e confiabilidade

#### **Semana 2: Mensageria com Kafka**

- Configuração do cluster Kafka no Docker
- Implementação de eventos assíncronos
- Comunicação entre serviços via mensageria
- Padrões de resiliência e Dead Letter Queues

#### **Semana 3: Segurança com Keycloak**

- Configuração do servidor Keycloak
- Implementação de autenticação JWT
- Controle de acesso baseado em roles (RBAC)
- Proteção das APIs e auditoria

#### **Semana 4: Performance e Monitoramento**

- Testes de carga com JMeter/K6
- Métricas de performance e escalabilidade
- Dashboards de monitoramento
- Análise de gargalos e otimizações

## 🤝 Como Contribuir

### **1. Fork o projeto**

```bash
git clone https://github.com/seu-usuario/microsservices.git
cd microsservices
```

### **2. Crie uma branch para sua feature**

```bash
git checkout -b feature/AmazingFeature
```

### **3. Commit suas mudanças**

```bash
git commit -m 'Add some AmazingFeature'
```

### **4. Push para a branch**

```bash
git push origin feature/AmazingFeature
```

### **5. Abra um Pull Request**

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Matteus Manoel**

- **GitHub:** [@matteusmanoel](https://github.com/matteusmanoel)
- **LinkedIn:** [Perfil LinkedIn](https://linkedin.com/in/matteusmanoel)

## 🙏 Agradecimentos

- **Quarkus Team** pelo framework excepcional
- **AwesomeAPI** pela API de cotações gratuita
- **Comunidade Java** pelo suporte e conhecimento compartilhado

## 📞 Suporte

- **Issues:** [GitHub Issues](https://github.com/matteusmanoel/microsservices/issues)
- **Discussions:** [GitHub Discussions](https://github.com/matteusmanoel/microsservices/discussions)

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!**

---

## 🏷️ Tags/Topics

```
quarkus, java, microservices, docker, postgresql, rest-api,
gradle, quarkus-framework, java-17, microservices-architecture,
docker-compose, postgres, rest-client, openapi, swagger,
health-checks, panache, hibernate
```

## 📋 Comandos Úteis

### **Desenvolvimento**

```bash
# Build dos projetos
./gradlew build                    # Build individual
docker-compose up --build         # Build e execução completa

# Logs dos containers
docker-compose logs -f            # Todos os serviços
docker-compose logs -f system-cart # Apenas system-cart

# Parar serviços
docker-compose down               # Parar e remover containers
docker-compose down -v            # Parar e remover volumes
```

### **Testes e Qualidade**

```bash
# Testes unitários
./gradlew test                     # Executar testes unitários
./gradlew test --tests "*ServiceTest" # Testes específicos

# Testes de integração
./gradlew integrationTest          # Executar testes de integração

# Cobertura de código
./gradlew jacocoTestReport         # Gerar relatório de cobertura
./gradlew jacocoTestCoverageVerification # Verificar cobertura mínima
```

### **Manutenção**

```bash
# Limpar build
./gradlew clean                   # Limpar build local

# Verificar status
docker-compose ps                # Status dos containers
docker-compose logs              # Logs dos containers
```

---

**Desenvolvido com ❤️ usando Quarkus e Java 17**
